package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;

public class EditCourse extends AppCompatActivity {

    TextView courseTitleView;
    EditText courseStartDateView;
    EditText courseEndDateView;
    EditText courseStatusView;
    EditText courseInstructorNameView;
    EditText courseInstructorPhoneView;
    EditText courseInstructorEmailView;
    EditText courseNotesView;

    String courseTitle;
    String courseStartDate;
    String courseEndDate;
    String courseStatus;
    String courseInstructorName;
    String courseInstructorPhone;
    String courseInstructorEmail;
    String courseNotes;

    int id;
    Repository repository;


    int courseTermId;

    int courseId;

    Course course;

    String spinnerValue;

    int numPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        setTitle("Edit Course");

        courseTitleView = findViewById(R.id.editCourseTitle);
        courseStartDateView = findViewById(R.id.editCourseStartDate);
        courseEndDateView = findViewById(R.id.editCourseEndDate);
        //courseStatusView = findViewById(R.id.editCourseStatus);
        courseInstructorNameView = findViewById(R.id.editCourseInstructorName);
        courseInstructorPhoneView = findViewById(R.id.editCourseInstructorPhone);
        courseInstructorEmailView = findViewById(R.id.editCourseInstructorEmail);
        courseNotesView = findViewById(R.id.editCourseNotes);

        courseTitle = DetailedCourseList.courseTitle;
        courseTitleView.setText(courseTitle);

        courseStartDate = DetailedCourseList.courseStartDate;
        courseStartDateView.setText(courseStartDate);

        courseEndDate = DetailedCourseList.courseEndDate;
        courseEndDateView.setText(courseEndDate);

        courseStatus = DetailedCourseList.courseStatus;
        //courseStatusView.setText(courseStatus);

        courseInstructorName = DetailedCourseList.courseInstructorName;
        courseInstructorNameView.setText(courseInstructorName);

        courseInstructorPhone = DetailedCourseList.courseInstructorPhone;
        courseInstructorPhoneView.setText(courseInstructorPhone);

        courseInstructorEmail = DetailedCourseList.courseInstructorEmail;
        courseInstructorEmailView.setText(courseInstructorEmail);

        courseNotes = DetailedCourseList.courseNotes;
        courseNotesView.setText(courseNotes);


        repository = new Repository(getApplication());

        courseTermId = DetailedCourseList.courseTermId;
        courseId = DetailedCourseList.courseId;

        //dropdown is spinner//
        Spinner dropdown = findViewById(R.id.editAssessmentSpinner);
        String[] items = new String[]{"In Progress", "Completed", "Dropped", "Plan to Take"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        for (int i = 0; i < items.length; i++) {
            if (DetailedCourseList.courseStatus.equals(items[i])) {
                dropdown.setSelection(i);
            }
        }


        Button button = findViewById(R.id.saveEditCourse);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //course = new Course("New Title", courseEndDateView.getText().toString(), courseStartDateView.getText().toString(), courseStatusView.getText().toString(), courseInstructorNameView.getText().toString(), courseInstructorPhoneView.getText().toString(), courseInstructorEmailView.getText().toString(), courseNotesView.getText().toString(), courseTermId);
                spinnerValue = dropdown.getSelectedItem().toString();
                repository.updateCourse(courseTitleView.getText().toString(), courseStartDateView.getText().toString(), courseEndDateView.getText().toString(), spinnerValue, courseInstructorNameView.getText().toString(), courseInstructorPhoneView.getText().toString(), courseInstructorEmailView.getText().toString(), courseNotesView.getText().toString(), courseTermId, courseId);
                Context context = getApplicationContext();
                String text = "Course has been edited!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}