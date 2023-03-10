package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Query;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Database.StudentSchedulerDatabase;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.Entities.Term;
import com.example.studentscheduler.R;

public class DetailedTermList extends AppCompatActivity {

    TextView termTitleView;
    TextView termStartDateView;
    TextView termEndDateView;


    String termTitle;
    String termStartDate;
    String termEndDate;
    int termId;

    Term term;
    Term currentTerm;

    int numParts;

    Repository repository;

    //TESTING//
    static int theTermIdForCourses;
    static String theTermTitleForCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_list);
        setTitle("Detailed Term View");


        termTitleView = findViewById(R.id.termTitleView);
        termStartDateView = findViewById(R.id.termStartDateView);
        termEndDateView = findViewById(R.id.termEndDateView);

        termId = getIntent().getIntExtra("ID", -1);

        //TESTING//
        theTermIdForCourses = termId;


        termTitle = getIntent().getStringExtra("Title");
        termTitleView.setText(termTitle);
        //TESTING//
        theTermTitleForCourses = termTitleView.getText().toString();


        termStartDate = getIntent().getStringExtra("StartDate");
        termStartDateView.setText(termStartDate);

        termEndDate = getIntent().getStringExtra("EndDate");
        termEndDateView.setText(termEndDate);

        repository = new Repository(getApplication());
    }

    public void viewCoursesForTermBtnPushed(View v) {
        Intent intent = new Intent(this, CourseList.class);
        intent.putExtra(String.valueOf(termId), -1);
        startActivity(intent);
    }

    public void deleteTermBtnPushed(View v) {
        for (Term term : repository.getAllTerms()) {
            if (term.getTermId() == termId) currentTerm = term;
        }

        numParts = 0;
        for (Course course : repository.getAllCourses()) {
            if (course.getCourseTermId() == termId) ++numParts;
        }

        if (numParts == 0) {
            repository.delete(currentTerm);
            Toast.makeText(DetailedTermList.this, currentTerm.getTermTitle() + " was deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DetailedTermList.this, "Must Delete Courses First", Toast.LENGTH_SHORT).show();
        }
    }


}