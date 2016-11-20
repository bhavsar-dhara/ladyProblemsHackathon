package com.angelhack.ladyproblems.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.affectiva.android.affdex.sdk.Frame;
import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;
import com.affectiva.android.affdex.sdk.detector.Face;
import com.angelhack.ladyproblems.R;

import java.util.List;

public class CamDetectActivity
        extends AppCompatActivity
        implements Detector.ImageListener, CameraDetector.CameraEventListener,
            Detector.FaceListener{

    private static final String TAG = CamDetectActivity.class.getSimpleName();
    private final static int CAMERA_PERMISSIONS_REQUEST_CODE = 0;
    private final static String[] CAMERA_PERMISSIONS_REQUEST = new String[]{Manifest.permission.CAMERA};
    private boolean handleCameraPermissionGrant = false;

    private TextView smileTextView;
    private TextView ageTextView;
    private TextView ethnicityTextView;
    ToggleButton toggleButton;

    private boolean isCameraBack = false;
    private boolean isSDKStarted = false;

    private RelativeLayout mainLayout;
    SurfaceView cameraPreview;

    private int previewWidth = 0;
    private int previewHeight = 0;

    private CameraDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_detect);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestPermission();

        smileTextView = (TextView) findViewById(R.id.smile_textview);
        ageTextView = (TextView) findViewById(R.id.age_textview);
        ethnicityTextView = (TextView) findViewById(R.id.ethnicity_textview);

        toggleButton = (ToggleButton) findViewById(R.id.front_back_toggle_button);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCameraBack = isChecked;
                switchCamera(isCameraBack
                        ? CameraDetector.CameraType.CAMERA_BACK
                        : CameraDetector.CameraType.CAMERA_FRONT);
            }
        });

        mainLayout = (RelativeLayout) findViewById(R.id.content_cam_detect);

        cameraPreview = new SurfaceView(this) {
            @Override
            public void onMeasure(int widthSpec, int heightSpec) {
                int measureWidth = MeasureSpec.getSize(widthSpec);
                int measureHeight = MeasureSpec.getSize(heightSpec);
                int width;
                int height;
                if (previewHeight == 0 || previewWidth == 0) {
                    width = measureWidth;
                    height = measureHeight;
                } else {
                    float viewAspectRatio = (float)measureWidth/measureHeight;
                    float cameraPreviewAspectRatio = (float) previewWidth/previewHeight;

                    if (cameraPreviewAspectRatio > viewAspectRatio) {
                        width = measureWidth;
                        height =(int) (measureWidth / cameraPreviewAspectRatio);
                    } else {
                        width = (int) (measureHeight * cameraPreviewAspectRatio);
                        height = measureHeight;
                    }
                }
                setMeasuredDimension(width,height);
            }
        };
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        cameraPreview.setLayoutParams(params);

        mainLayout.addView(cameraPreview, 0);

        detector = new CameraDetector(this, CameraDetector.CameraType.CAMERA_FRONT, cameraPreview);
        detector.setDetectSmile(true);
        detector.setDetectAge(true);
        detector.setDetectEthnicity(true);
        detector.setImageListener(this);
        detector.setOnCameraEventListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSDKStarted) {
            startDetector();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopDetector();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSIONS_REQUEST_CODE) {
            for (String permission : permissions) {
                if (permission.equals(Manifest.permission.CAMERA)) {
                    // next time through onResume, handle the grant result
                    handleCameraPermissionGrant = true;
                    break;
                } else {
                    showRationale();
                }
            }
        }
    }

    @Override
    public void onCameraSizeSelected(int cameraWidth, int cameraHeight, Frame.ROTATE rotation) {
        int cameraPreviewWidth;
        int cameraPreviewHeight;

        //cameraWidth and cameraHeight report the unrotated dimensions of the camera frames,
        // so switch the width and height if necessary

        if (rotation == Frame.ROTATE.BY_90_CCW || rotation == Frame.ROTATE.BY_90_CW) {
            cameraPreviewWidth = cameraHeight;
            cameraPreviewHeight = cameraWidth;
        } else {
            cameraPreviewWidth = cameraWidth;
            cameraPreviewHeight = cameraHeight;
        }

        //retrieve the width and height of the ViewGroup object containing our SurfaceView (in an
        // actual application, we would want to consider the possibility that the mainLayout object
        // may not have been sized yet)

        int layoutWidth = mainLayout.getWidth();
        int layoutHeight = mainLayout.getHeight();

        //compute the aspect Ratio of the ViewGroup object and the cameraPreview

        float layoutAspectRatio = (float)layoutWidth/layoutHeight;
        float cameraPreviewAspectRatio = (float)cameraWidth/cameraHeight;

        int newWidth;
        int newHeight;

        if (cameraPreviewAspectRatio > layoutAspectRatio) {
            newWidth = layoutWidth;
            newHeight =(int) (layoutWidth / cameraPreviewAspectRatio);
        } else {
            newWidth = (int) (layoutHeight * cameraPreviewAspectRatio);
            newHeight = layoutHeight;
        }

        //size the SurfaceView

        ViewGroup.LayoutParams params = cameraPreview.getLayoutParams();
        params.height = newHeight;
        params.width = newWidth;
        cameraPreview.setLayoutParams(params);

//        cameraPreview.requestLayout();
    }

    @Override
    public void onImageResults(List<Face> list, Frame frame, float v) {

    }

    private void requestPermission() {
        if (!handleCameraPermissionGrant
                && !checkPermission()
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(CAMERA_PERMISSIONS_REQUEST, CAMERA_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void showRationale() {
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
                .setTitle("")
                .setMessage("This app needs camera permission to use this feature for quick emotion recognition.")
                .setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        requestPermission();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userCancelled(null);
                    }
                })
                .create();

        dialog.setCancelable(false);
        dialog.show();
    }

    private void userCancelled(View v) {
        // TODO
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkPermission() {
        return this.checkPermission(Manifest.permission.CAMERA, Process.myPid(), Process.myUid())
                == PackageManager.PERMISSION_GRANTED;
    }

    private void startDetector() {
        if (!detector.isRunning()) {
            detector.start();
        }
    }

    private void stopDetector() {
        if (detector.isRunning()) {
            detector.stop();
        }
    }

    private void switchCamera(CameraDetector.CameraType type) {
        detector.setCameraType(type);
    }

    @Override
    public void onFaceDetectionStarted() {

    }

    @Override
    public void onFaceDetectionStopped() {

    }
}
