package com.example.studentscheduler.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    int courseId; //PRIMARY KEY//

    private String courseTitle;
    private String courseStartDate;
    private String courseEndDate;
    private String courseStatus; //IN PROGRESS, COMPLETED, DROPPED, OR PLAN TO TAKE//
    private String courseInstructorsName;
    private String courseInstructorsPhone;
    private String courseInstructorsEmail;
    private String courseNotes; //OPTIONAL BUT MINIMUM OF 1 PER COURSE

    private int courseTermId;


    //CONSTRUCTOR//
    public Course(String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorsName, String courseInstructorsPhone, String courseInstructorsEmail, String courseNotes, int courseTermId) {
        this.courseTitle = courseTitle;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorsName = courseInstructorsName;
        this.courseInstructorsPhone = courseInstructorsPhone;
        this.courseInstructorsEmail = courseInstructorsEmail;
        this.courseNotes = courseNotes;
        this.courseTermId = courseTermId;
    }

    public Course() {
    }

    //GETTERS//
    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public String getCourseInstructorsName() {
        return courseInstructorsName;
    }

    public String getCourseInstructorsPhone() {
        return courseInstructorsPhone;
    }

    public String getCourseInstructorsEmail() {
        return courseInstructorsEmail;
    }

    public String getCourseNotes() {
        return courseNotes;
    }


    public int getCourseTermId() {
        return courseTermId;
    }

    //SETTERS//
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setCourseInstructorsName(String courseInstructorsName) {
        this.courseInstructorsName = courseInstructorsName;
    }

    public void setCourseInstructorsPhone(String courseInstructorsPhone) {
        this.courseInstructorsPhone = courseInstructorsPhone;
    }

    public void setCourseInstructorsEmail(String courseInstructorsEmail) {
        this.courseInstructorsEmail = courseInstructorsEmail;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }


    public void setCourseTermId(int courseTermId) {
        this.courseTermId = courseTermId;
    }
}
