package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.dataModel.absorbency;
import com.angelhack.ladyproblems.dataModel.finalResult;

import com.angelhack.ladyproblems.R;

import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_little;

public class R7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r7);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void result(View view){
        Intent intent = new Intent(this, R8Activity.class);
        startActivity(intent);
    }

}
