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
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;


public class AddCourse extends AppCompatActivity {

    EditText addCourseTitle;
    EditText addCourseStartDate;
    EditText addCourseEndDate;
    EditText addCourseStatus;
    EditText addCourseInstructorsName;
    EditText addCourseInstructorsPhone;
    EditText addCourseInstructorsEmail;
    EditText addCourseNotes;

    int idKey;

    int courseTermId;


    Course course;


    Repository repository;

    String courseTitle;
    String courseStartDate;
    String courseEndDate;
    String courseStatus;
    String courseInstructorsName;
    String courseInstructorsPhone;
    String courseInstructorsEmail;
    String courseNotes;
    String spinnerValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setTitle("Add Course");

        idKey = getIntent().getIntExtra("ID", -1);

        //TESTING//
        courseTermId = DetailedTermList.theTermIdForCourses;


        addCourseTitle = findViewById(R.id.addCourseTitle);
        addCourseStartDate = findViewById(R.id.addCourseStartDate);
        addCourseEndDate = findViewById(R.id.addCourseEndDate);
        //addCourseStatus = findViewById(R.id.addCourseStatus);
        addCourseInstructorsName = findViewById(R.id.addCourseInstructorsName);
        addCourseInstructorsPhone = findViewById(R.id.addCourseInstructorsPhone);
        addCourseInstructorsEmail = findViewById(R.id.addCourseInstructorsEmail);
        addCourseNotes = findViewById(R.id.addCourseNotes);

        courseTitle = getIntent().getStringExtra("Title");
        courseStartDate = getIntent().getStringExtra("StartDate");
        courseEndDate = getIntent().getStringExtra("EndDate");
        courseStatus = getIntent().getStringExtra("Status");
        courseInstructorsName = getIntent().getStringExtra("InstructorName");
        courseInstructorsPhone = getIntent().getStringExtra("InstructorPhone");
        courseInstructorsEmail = getIntent().getStringExtra("InstructorEmail");
        courseNotes = getIntent().getStringExtra("Notes");

        addCourseTitle.setText(courseTitle);
        addCourseStartDate.setText(courseStartDate);
        addCourseEndDate.setText(courseEndDate);
        //addCourseStatus.setText(courseStatus);
        addCourseInstructorsName.setText(courseInstructorsName);
        addCourseInstructorsPhone.setText(courseInstructorsPhone);
        addCourseInstructorsEmail.setText(courseInstructorsEmail);
        addCourseNotes.setText(courseNotes);

        repository = new Repository(getApplication());

        //TESTING//
        //dropdown is spinner//
        Spinner dropdown = findViewById(R.id.editAssessmentSpinner);
        String[] items = new String[]{"In Progress", "Completed", "Dropped", "Plan to Take"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        /////////////////////////////////////

        Button button = findViewById(R.id.saveAddCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idKey == -1) {
                    spinnerValue = dropdown.getSelectedItem().toString();
                    course = new Course(addCourseTitle.getText().toString(), addCourseStartDate.getText().toString(), addCourseEndDate.getText().toString(), spinnerValue, addCourseInstructorsName.getText().toString(), addCourseInstructorsPhone.getText().toString(), addCourseInstructorsEmail.getText().toString(), addCourseNotes.getText().toString(), courseTermId);
                    repository.insert(course);
                } else {
                    spinnerValue = dropdown.getSelectedItem().toString();
                    course = new Course(addCourseTitle.getText().toString(), addCourseStartDate.getText().toString(), addCourseEndDate.getText().toString(), spinnerValue, addCourseInstructorsName.getText().toString(), addCourseInstructorsPhone.getText().toString(), addCourseInstructorsEmail.getText().toString(), addCourseNotes.getText().toString(), courseTermId);
                    repository.update(course);
                }
                Context context = getApplicationContext();
                String text = "Course Added!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}