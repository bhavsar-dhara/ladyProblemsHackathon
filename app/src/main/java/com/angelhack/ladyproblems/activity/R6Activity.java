package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.activityLevel;
import com.angelhack.ladyproblems.dataModel.finalResult;

import static com.angelhack.ladyproblems.dataModel.activityLevel.activity.ac_high_exercise;
import static com.angelhack.ladyproblems.dataModel.activityLevel.activity.ac_low_resting;
import static com.angelhack.ladyproblems.dataModel.activityLevel.activity.ac_moderate_sitting;
import static com.angelhack.ladyproblems.dataModel.activityLevel.activity.ac_sleeping;

public class R6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void sleep(View view){
        finalResult.getInstance().setActivityLevel(new activityLevel(ac_sleeping));
        Intent intent = new Intent(this, FaceEmoDetectActivity.class);
        startActivity(intent);
    }

    public void low(View view){
        finalResult.getInstance().setActivityLevel(new activityLevel(ac_low_resting));
        Intent intent = new Intent(this, FaceEmoDetectActivity.class);
        startActivity(intent);
    }

    public void mod(View view){
        finalResult.getInstance().setActivityLevel(new activityLevel(ac_moderate_sitting));
        Intent intent = new Intent(this, FaceEmoDetectActivity.class);
        startActivity(intent);
    }

    public void high(View view){
        finalResult.getInstance().setActivityLevel(new activityLevel(ac_high_exercise));
        Intent intent = new Intent(this, FaceEmoDetectActivity.class);
        startActivity(intent);
    }

}
