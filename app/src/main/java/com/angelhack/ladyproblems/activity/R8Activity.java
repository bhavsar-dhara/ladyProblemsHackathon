package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.absorbency;
import com.angelhack.ladyproblems.dataModel.finalResult;

import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_lite;
import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_regular;
import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_super;
import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_super_plus;
import static com.angelhack.ladyproblems.dataModel.absorbency.absorbencies.ab_ultra;

public class R8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r8);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void done(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ultra(View view){
        finalResult.getInstance().setAbsorbency(new absorbency(ab_ultra));
    }

    public void lite(View view){
        finalResult.getInstance().setAbsorbency(new absorbency(ab_lite));
    }

    public void super1(View view){
        finalResult.getInstance().setAbsorbency(new absorbency(ab_super));
    }

    public void superplus(View view){
        finalResult.getInstance().setAbsorbency(new absorbency(ab_super_plus));
    }

    public void regular(View view){
        finalResult.getInstance().setAbsorbency(new absorbency(ab_regular));
    }

}
