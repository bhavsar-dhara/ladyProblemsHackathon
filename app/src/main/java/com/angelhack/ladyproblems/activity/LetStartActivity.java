package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.finalResult;
import com.angelhack.ladyproblems.dataModel.letStart;

import static com.angelhack.ladyproblems.dataModel.letStart.letStartOptions.s_insert;
import static com.angelhack.ladyproblems.dataModel.letStart.letStartOptions.s_remove;

public class LetStartActivity extends AppCompatActivity {

    private finalResult finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_let_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        finalResult = finalResult.getInstance();
    }

    public void removed(View view) {
        finalResult.setLetStart(new letStart(s_remove));
        Intent intent = new Intent(this, R8Activity.class);
        startActivity(intent);
    }

    public void inserted(View view) {
        finalResult.setLetStart(new letStart(s_insert));
        Intent intent = new Intent(this, R8Activity.class);
        startActivity(intent);
    }
}
