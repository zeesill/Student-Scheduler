package com.example.studentscheduler.UI;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Term;
import com.example.studentscheduler.R;

import java.text.SimpleDateFormat;


public class AddTerm extends AppCompatActivity {

    EditText addTermTitle;
    EditText addTermStartDate;
    EditText addTermEndDate;

    String termTitle;
    String termStartDate;
    String termEndDate;

    int id;
    Term term;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        setTitle("Add Term");

        addTermTitle = findViewById(R.id.addTermTitle);
        addTermStartDate = findViewById(R.id.addTermStartDate);
        addTermEndDate = findViewById(R.id.addTermEndDate);

        id = getIntent().getIntExtra("ID", -1);
        termTitle = getIntent().getStringExtra("Title");
        termStartDate = getIntent().getStringExtra("StartDate");
        termEndDate = getIntent().getStringExtra("EndDate");

        addTermTitle.setText(termTitle);
        addTermStartDate.setText(termStartDate);
        addTermEndDate.setText(termEndDate);

        repository = new Repository(getApplication());


        Button button = findViewById(R.id.saveAddTerm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == -1) {
                    term = new Term(addTermTitle.getText().toString(), addTermStartDate.getText().toString(), addTermEndDate.getText().toString());
                    repository.insert(term);
                } else {
                    term = new Term(addTermTitle.getText().toString(), addTermStartDate.getText().toString(), addTermEndDate.getText().toString());
                    repository.update(term);
                }

                Context context = getApplicationContext();
                String text = "Term Added!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}