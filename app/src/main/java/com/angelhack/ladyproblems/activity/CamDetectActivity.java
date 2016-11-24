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
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.affectiva.android.affdex.sdk.Frame;
import com.affectiva.android.affdex.sdk.detector.CameraDetector;
import com.affectiva.android.affdex.sdk.detector.Detector;
import com.affectiva.android.affdex.sdk.detector.Face;
import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.age;
import com.angelhack.ladyproblems.dataModel.emotion;
import com.angelhack.ladyproblems.dataModel.finalResult;

import java.util.List;

public class CamDetectActivity
        extends AppCompatActivity
        implements Detector.ImageListener, CameraDetector.CameraEventListener {

    private static final String TAG = CamDetectActivity.class.getSimpleName();

    private final static int CAMERA_PERMISSIONS_REQUEST_CODE = 0;
    private final static String[] CAMERA_PERMISSIONS_REQUEST = new String[]{Manifest.permission.CAMERA};
    private boolean handleCameraPermissionGrant = false;

    private TextView smileTextView;
    private TextView ageTextView;
    private TextView ethnicityTextView;
    private TextView emotionTextView;
    private ToggleButton toggleButton;
    private Button captureButton;

    private boolean isCameraBack = false;
    private boolean isSDKStarted = false;

    private RelativeLayout mainLayout;
    private SurfaceView cameraPreview;

    private int previewWidth = 0;
    private int previewHeight = 0;

    private CameraDetector detector;

    private String emotions = "";
    //private String age;
    private age ageValue;

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
        emotionTextView = (TextView) findViewById(R.id.emotion_textview);

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

        captureButton = (Button) findViewById(R.id.capture_button);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSDKStarted) {
                    isSDKStarted = false;
                    stopDetector();
                    captureButton.setText("Start Camera");
                    saveDataCaptured();
                    startActivity(new Intent(getBaseContext(), R7Activity.class));
                    finish();
                } else {
                    isSDKStarted = true;
                    startDetector();
                    captureButton.setText("Stop Camera");
                }
            }
        });
        captureButton.setText("Start Camera");

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
        detector.setDetectAllEmotions(true);
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
    public void onCameraSizeSelected(int width, int height, Frame.ROTATE rotate) {
//        if (rotate == Frame.ROTATE.BY_90_CCW || rotate == Frame.ROTATE.BY_90_CW) {
//            previewWidth = height;
//            previewHeight = width;
//        } else {
//            previewHeight = height;
//            previewWidth = width;
//        }

        previewHeight = width;
        previewWidth = height;
        cameraPreview.requestLayout();
    }

    @Override
    public void onImageResults(List<Face> list, Frame frame, float v) {

        if (list == null)
            return;
        if (list.size() == 0) {
            smileTextView.setText("NO FACE");
            ageTextView.setText("");
            ethnicityTextView.setText("");
            emotionTextView.setText("");
        } else {
            Face face = list.get(0);
            float joy = face.emotions.getJoy();
            float anger = face.emotions.getAnger();
            float disgust = face.emotions.getDisgust();
            float contempt = face.emotions.getContempt();
            float fear = face.emotions.getFear();
            float surprise = face.emotions.getSurprise();
            float sadness = face.emotions.getSadness();

            int tag = 0;
            if (joy > 10.0) {
                emotions = emotion.emotions.e_joy.getText();
                tag = emotion.emotions.e_joy.getValue();
            } else if (anger > 10.0) {
                emotions = emotion.emotions.e_anger.getText();
                tag = emotion.emotions.e_anger.getValue();
            } else if (disgust > 10.0) {
                emotions = emotion.emotions.e_disgust.getText();
                tag = emotion.emotions.e_disgust.getValue();
            } else if (contempt > 10.0) {
                emotions = emotion.emotions.e_contempt.getText();
                tag = emotion.emotions.e_contempt.getValue();
            } else if (fear > 10.0) {
                emotions = emotion.emotions.e_fear.getText();
                tag = emotion.emotions.e_fear.getValue();
            } else if (surprise > 10.0) {
                emotions = emotion.emotions.e_surprise.getText();
                tag = emotion.emotions.e_surprise.getValue();
            } else if (sadness > 10.0) {
                emotions = emotion.emotions.e_sadness.getText();
                tag = emotion.emotions.e_sadness.getValue();
            }
            emotionTextView.setText(emotions);
            emotionTextView.setTag(tag);

            smileTextView.setText(String.format("SMILE\n%.2f",face.expressions.getSmile()));
            switch (face.appearance.getAge()) {
                case AGE_UNKNOWN:
                    //age = "";
                    ageValue = new age(age.ages.a_unknown);
                    ageTextView.setText("");
                    break;
                case AGE_UNDER_18:
                    //age = getString(R.string.age_under_18);
                    ageValue = new age(age.ages.a_under18);
                    ageTextView.setText(R.string.age_under_18);
                    break;
                case AGE_18_24:
                    //age = getString(R.string.age_18_24);
                    ageValue = new age(age.ages.a_18t024);
                    ageTextView.setText(R.string.age_18_24);
                    break;
                case AGE_25_34:
                    //age = getString(R.string.age_25_34);
                    ageValue = new age(age.ages.a_25to34);
                    ageTextView.setText(R.string.age_25_34);
                    break;
                case AGE_35_44:
                    //age = getString(R.string.age_35_44);
                    ageValue = new age(age.ages.a_35to44);
                    ageTextView.setText(R.string.age_35_44);
                    break;
                case AGE_45_54:
                    //age = getString(R.string.age_45_54);
                    ageValue = new age(age.ages.a_45to54);
                    ageTextView.setText(R.string.age_45_54);
                    break;
                case AGE_55_64:
                    //age = getString(R.string.age_55_64);
                    ageValue = new age(age.ages.a_55to64);
                    ageTextView.setText(R.string.age_55_64);
                    break;
                case AGE_65_PLUS:
                    //age = getString(R.string.age_over_64);
                    ageValue = new age(age.ages.a_65plus);
                    ageTextView.setText(R.string.age_over_64);
                    break;
            }

            switch (face.appearance.getEthnicity()) {
                case UNKNOWN:
                    ethnicityTextView.setText("");
                    break;
                case CAUCASIAN:
                    ethnicityTextView.setText(R.string.ethnicity_caucasian);
                    break;
                case BLACK_AFRICAN:
                    ethnicityTextView.setText(R.string.ethnicity_black_african);
                    break;
                case EAST_ASIAN:
                    ethnicityTextView.setText(R.string.ethnicity_east_asian);
                    break;
                case SOUTH_ASIAN:
                    ethnicityTextView.setText(R.string.ethnicity_south_asian);
                    break;
                case HISPANIC:
                    ethnicityTextView.setText(R.string.ethnicity_hispanic);
                    break;
            }
        }
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

    private void saveDataCaptured() {
      //  finalResult.getInstance().setAge(age);
        finalResult.getInstance().setAge(ageValue);
        if (emotions != null) {
            if(emotions.equals(emotion.emotions.e_anger.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_anger));
            } else if(emotions.equals(emotion.emotions.e_disgust.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_disgust));
            } else if(emotions.equals(emotion.emotions.e_contempt.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_contempt));
            } else if(emotions.equals(emotion.emotions.e_surprise.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_surprise));
            } else if(emotions.equals(emotion.emotions.e_sadness.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_sadness));
            } else if(emotions.equals(emotion.emotions.e_fear.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_fear));
            } else if(emotions.equals(emotion.emotions.e_joy.getText())) {
                finalResult.getInstance().setEmotion(new emotion(emotion.emotions.e_joy));
            } else {
                Log.e(TAG, "Oops something went wrong");
            }
        } else {
            Log.e(TAG, "Got null value of emotion");
        }
    }
}
