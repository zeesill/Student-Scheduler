package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;

import java.util.Arrays;
import java.util.List;

public class AssessmentList extends AppCompatActivity {

    TextView assessmentCourseTitleView;
    String assessmentCourseTitle;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        setTitle("Assessments");
        assessmentCourseTitleView = findViewById(R.id.assessmentCourseTitle);
        //int id = getIntent().getIntExtra("ID", -1);
        //assessmentCourseTitle = getIntent().getStringExtra("Title");

        //TESTING//
        assessmentCourseTitle = DetailedCourseList.courseTitle;
        assessmentCourseTitleView.setText(assessmentCourseTitle);

        int id = DetailedCourseList.courseId;


        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());

        List<Assessment> allAssessmentsByCourseId = repository.getAllAssociatedAssessmentsInCourse(id);
        //List<Course> allCourses = repository.getAllCourses();
        assessmentAdapter.setAssessments(allAssessmentsByCourseId);

        System.out.println();
    }


    public void toAddAssessmentBtnPushed(View v) {
        Intent intent = new Intent(this, AddAssessment.class);
        startActivity(intent);
    }
}