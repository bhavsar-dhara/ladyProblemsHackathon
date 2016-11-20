package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.angelhack.ladyproblems.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTampaxAlgo(View view) {
        Intent intent = new Intent(this, LetStartActivity.class);
        startActivity(intent);
    }

    public void startCamTest(View view) {
        Intent intent = new Intent(this, FaceEmoDetectActivity.class);
        startActivity(intent);
    }
}
