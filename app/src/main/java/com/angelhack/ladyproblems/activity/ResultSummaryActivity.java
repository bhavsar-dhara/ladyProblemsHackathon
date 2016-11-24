package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.activityLevel;
import com.angelhack.ladyproblems.dataModel.discomfort;
import com.angelhack.ladyproblems.dataModel.age;
import com.angelhack.ladyproblems.dataModel.emotion;
import com.angelhack.ladyproblems.dataModel.finalResult;
import com.angelhack.ladyproblems.dataModel.flow;
import com.angelhack.ladyproblems.dataModel.leak;

import java.util.Locale;

public class ResultSummaryActivity extends AppCompatActivity {

    private TextView confidenceLevel;
    private TextView flowCalculation;
    private TextView age;
    private TextView faceEmotion;
    private TextView smileDegree;

    private emotion e_emotions;
    private leak leakage;
    private discomfort options;
    private int emotions;
    private boolean leak = true;
    private boolean discomfort = true;
    private flow flow;
    private activityLevel ac_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        confidenceLevel = (TextView) findViewById(R.id.textView2);
        flowCalculation = (TextView) findViewById(R.id.textView4);
        age = (TextView) findViewById(R.id.textView6);
        faceEmotion = (TextView) findViewById(R.id.textView8);
        smileDegree = (TextView) findViewById(R.id.textView10);

        confidenceLevel.setText(calculateConfidenceLevel().toString());
        flowCalculation.setText(calculateFlowLevel().getText());
        age.setText(finalResult.getInstance().getAge().toString());
        faceEmotion.setText(finalResult.getInstance().getEmotion().toString());
        smileDegree.setText(String.valueOf(finalResult.getInstance().getSmileDegree()));
        leakage = finalResult.getInstance().getLeak();

        if(finalResult.getInstance().getEmotion() != null) {
            e_emotions = finalResult.getInstance().getEmotion();
        } else {
            e_emotions = new emotion(emotion.emotions.e_contempt);
        }
        options = finalResult.getInstance().getDiscomfort();
        flow = finalResult.getInstance().getFlow();
        ac_activity = finalResult.getInstance().getActivityLevel();

        confidenceLevel.setText(calculateConfidenceLevel().toString());
        flowCalculation.setText(calculateFlowLevel().getText());


        if(finalResult.getInstance().getAge() != null) {
            age ageValue = finalResult.getInstance().getAge();
            age.setText(ageValue.getAGES().getText());
        } else {
            age.setText("Age not recorded.");
        }
        if(finalResult.getInstance().getEmotion() != null) {
            emotion emotionValue = finalResult.getInstance().getEmotion();
            faceEmotion.setText(emotionValue.getEMOTIONS().getText());
        } else {
            faceEmotion.setText("Emotion not recorded.");
        }
        smileDegree.setText(String.valueOf(finalResult.getInstance().getSmileDegree()));
    }


    private Double calculateConfidenceLevel() {
        if (leakage.getLEAKAGE().getValue() == 1) {
            leak = false;
        }

        if (options.getOPTIONS().getValue() == 1) {
            discomfort = false;
        }

        emotions = e_emotions.getEMOTIONS().getValue();

        Double confidenceLevel = 50.0;
        if ((!discomfort || !leak) &&
                emotions == emotion.emotions.e_joy.getValue())
            confidenceLevel=100.0;
        else if ((discomfort || !leak) &&
                (emotions == emotion.emotions.e_joy.getValue() ||
                        emotions == emotion.emotions.e_surprise.getValue()))
            confidenceLevel=90.0;
        else if ((!discomfort || leak) &&
                (emotions == emotion.emotions.e_joy.getValue() ||
                        emotions == emotion.emotions.e_surprise.getValue()))
            confidenceLevel=80.0;
        else if ((discomfort || leak) &&
                (emotions == emotion.emotions.e_joy.getValue() ||
                        emotions == emotion.emotions.e_surprise.getValue()))
            confidenceLevel=70.0;
        else if ((!discomfort && leak )&&
                (emotions == emotion.emotions.e_disgust.getValue() ||
                        emotions == emotion.emotions.e_contempt.getValue()))
            confidenceLevel=60.0;
        else if (discomfort && leak &&
                (emotions == emotion.emotions.e_disgust.getValue() ||
                        emotions == emotion.emotions.e_fear.getValue() ||
                        emotions == emotion.emotions.e_anger.getValue()))
            confidenceLevel=50.0;

        return confidenceLevel;
    }

    private flow.flows calculateFlowLevel() {
        flow.flows f_flow = flow.getFLOWS().f_same_as_before;
        if (discomfort)
        {
            if (leak) {
                f_flow = flow.getFLOWS().f_increase;
            } else
            if (ac_activity.getACTIVITY().getValue() != activityLevel.activity.ac_high_exercise.getValue()
                    || ac_activity.getACTIVITY().getValue() != activityLevel.activity.ac_sleeping.getValue())
            {
                f_flow = flow.getFLOWS().f_increase;
            } else
                f_flow = flow.getFLOWS().f_decrease;
        }
        else if (!discomfort && !leak)
        {
            if (ac_activity.getACTIVITY().getValue() != activityLevel.activity.ac_high_exercise.getValue()
                    || ac_activity.getACTIVITY().getValue() != activityLevel.activity.ac_sleeping.getValue())
                f_flow = flow.getFLOWS().f_increase;
            else
                f_flow = flow.getFLOWS().f_decrease;
        }
        return f_flow;
    }

    public void next(View view) {
        startActivity(new Intent(this, R8Activity.class));
    }
}
