package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentscheduler.Entities.Term;
import com.example.studentscheduler.R;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toTermsList(View v) {
        Intent toTermsList = new Intent(this, TermsList.class);
        startActivity(toTermsList);
    }


}