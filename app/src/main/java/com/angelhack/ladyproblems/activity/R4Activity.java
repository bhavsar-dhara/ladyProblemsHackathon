package com.angelhack.ladyproblems.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.angelhack.ladyproblems.R;
import com.angelhack.ladyproblems.dataModel.anotherTampon;
import com.angelhack.ladyproblems.dataModel.finalResult;

import static com.angelhack.ladyproblems.dataModel.anotherTampon.tampon.t_no;
import static com.angelhack.ladyproblems.dataModel.anotherTampon.tampon.t_yes;

public class R4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void anotherTampon(View view){
        finalResult.getInstance().setAnotherTampon(new anotherTampon(t_yes));
        Intent intent = new Intent(this, R5Activity.class);
        startActivity(intent);
    }

    public void noTampon(View view){
        finalResult.getInstance().setAnotherTampon(new anotherTampon(t_no));
        Intent intent = new Intent(this, R5Activity.class);
        startActivity(intent);
    }

}
