package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.finalResult;
import com.angelhack.ladyproblems.dataModel.flow;

import static com.angelhack.ladyproblems.dataModel.flow.flows.f_decrease;
import static com.angelhack.ladyproblems.dataModel.flow.flows.f_increase;
import static com.angelhack.ladyproblems.dataModel.flow.flows.f_same_as_before;

public class R5Activity extends AppCompatActivity {

    private static finalResult finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        finalResult = finalResult.getInstance();
    }

    public void increase(View view){
        finalResult.setFlow(new flow(f_increase));
        Intent intent = new Intent(this, R6Activity.class);
        startActivity(intent);
    }

    public void same(View view){
        finalResult.setFlow(new flow(f_same_as_before));
        Intent intent = new Intent(this, R6Activity.class);
        startActivity(intent);
    }

    public void decrease(View view){
        finalResult.setFlow(new flow(f_decrease));
        Intent intent = new Intent(this, R6Activity.class);
        startActivity(intent);
    }
}
