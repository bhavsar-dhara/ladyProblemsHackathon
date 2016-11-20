package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.dataModel.discomfort;
import com.angelhack.ladyproblems.dataModel.finalResult;

import com.angelhack.ladyproblems.R;

import static com.angelhack.ladyproblems.dataModel.discomfort.options.d_no;
import static com.angelhack.ladyproblems.dataModel.discomfort.options.d_yes;

public class R3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void discomfort(View view) {
        finalResult.getInstance().setDiscomfort(new discomfort(d_yes));
        Intent intent = new Intent(this, R4Activity.class);
        startActivity(intent);
    }

    public void comfort(View view) {
        finalResult.getInstance().setDiscomfort(new discomfort(d_no));
        Intent intent = new Intent(this, R4Activity.class);
        startActivity(intent);
    }

}
