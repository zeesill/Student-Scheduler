package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.Entities.Term;
import com.example.studentscheduler.R;

import java.util.List;

public class CourseList extends AppCompatActivity {

    TextView coursesTitle;


    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        setTitle("Courses");

        coursesTitle = findViewById(R.id.coursesTitle);

        //TESTING//
        coursesTitle.setText(DetailedTermList.theTermTitleForCourses);


        RecyclerView recyclerView = findViewById(R.id.courseRecylerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());

        List<Course> allCoursesByTermId = repository.getAllCoursesByTermId(DetailedTermList.theTermIdForCourses);
        //List<Course> allCourses = repository.getAllCourses();
        courseAdapter.setCourses(allCoursesByTermId);


    }

    public void toAddCourse(View v) {
        Intent toAddCourseScreen = new Intent(this, AddCourse.class);
        startActivity(toAddCourseScreen);
    }


}