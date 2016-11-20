package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    private finalResult finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r8);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        finalResult = finalResult.getInstance();
    }

    public void done(View view){
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

    public void ultra(View view){
        finalResult.setAbsorbency(new absorbency(ab_ultra));
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

    public void lite(View view){
        finalResult.setAbsorbency(new absorbency(ab_lite));
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

    public void super1(View view){
        finalResult.setAbsorbency(new absorbency(ab_super));
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

    public void superplus(View view){
        finalResult.setAbsorbency(new absorbency(ab_super_plus));
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

    public void regular(View view){
        finalResult.setAbsorbency(new absorbency(ab_regular));
        Intent intent = new Intent(this, absorbency.class);
        startActivity(intent);
    }

}
