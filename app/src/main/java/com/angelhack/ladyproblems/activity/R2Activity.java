package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.finalResult;
import com.angelhack.ladyproblems.dataModel.leak;

public class R2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void yesLeakage(View view) {
        finalResult.getInstance().setLeak(new leak(leak.leakage.l_yes));
        Intent intent = new Intent(this, R3Activity.class);
        startActivity(intent);
    }

    public void noLeakage(View view) {
        finalResult.getInstance().setLeak(new leak(leak.leakage.l_no));
        Intent intent = new Intent(this, R3Activity.class);
        startActivity(intent);
    }

}
