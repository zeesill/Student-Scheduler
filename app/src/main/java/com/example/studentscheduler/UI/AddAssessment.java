package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.R;

public class AddAssessment extends AppCompatActivity {

    EditText addAssessmentTitleView;
    EditText addAssessmentStartDateView;
    EditText addAssessmentEndDateView;


    Repository repository;
    Assessment assessment;

    String spinnerValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        setTitle("Add Assessment");


        //dropdown is spinner//
        Spinner dropdown = findViewById(R.id.editAssessmentSpinner);
        String[] items = new String[]{"Performance", "Objective"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        addAssessmentTitleView = findViewById(R.id.assessmentTitleText);
        addAssessmentEndDateView = findViewById(R.id.assessmentEndDateText);
        addAssessmentStartDateView = findViewById(R.id.assessmentStartDateText);
        int courseId = DetailedCourseList.courseId;

        repository = new Repository(getApplication());


        Button button = findViewById(R.id.addAssessment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerValue = dropdown.getSelectedItem().toString();
                assessment = new Assessment(addAssessmentTitleView.getText().toString(), addAssessmentStartDateView.getText().toString(), addAssessmentEndDateView.getText().toString(), spinnerValue, courseId);
                repository.insert(assessment);

                Context context = getApplicationContext();
                String text = "Assessment Added!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}