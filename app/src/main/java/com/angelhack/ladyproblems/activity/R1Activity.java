package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.finalResult;
import com.angelhack.ladyproblems.dataModel.hoursOfInsertion;

import static com.angelhack.ladyproblems.dataModel.hoursOfInsertion.hours.h_greaterThan3;
import static com.angelhack.ladyproblems.dataModel.hoursOfInsertion.hours.h_lessThan3;

public class R1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void lessThan3(View view) {
        finalResult.getInstance().setHoursOfInsertion(new hoursOfInsertion(h_lessThan3));
        Intent intent = new Intent(this, R2Activity.class);
        startActivity(intent);
    }

    public void greaterThan3(View view) {
        finalResult.getInstance().setHoursOfInsertion(new hoursOfInsertion(h_greaterThan3));
        Intent intent = new Intent(this, R2Activity.class);
        startActivity(intent);
    }
}
