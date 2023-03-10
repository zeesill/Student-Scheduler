package com.example.studentscheduler.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    int assessmentId; //PRIMARY KEY//

    private String assessmentTitle;
    private String assessmentStartDate;
    private String assessmentEndDate;
    private String assessmentType; //WHETHER IT'S A PERFORMANCE OR OBJECTIVE ASSESSMENT//

    private int assessmentCourseId; //EACH ASSESSMENT NEEDS A COURSE ID - SO WE KNOW WHERE WHERE IT RESIDES//

    //CONSTRUCTORS//

    public Assessment(String assessmentTitle, String assessmentStartDate, String assessmentEndDate, String assessmentType, int assessmentCourseId) {
        this.assessmentTitle = assessmentTitle;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.assessmentType = assessmentType;
        this.assessmentCourseId = assessmentCourseId;
    }

    public Assessment(String assessmentTitle, String assessmentStartDate, String assessmentEndDate) {
        this.assessmentTitle = assessmentTitle;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
    }

    public Assessment() {
    }

    //GETTERS//
    public int getAssessmentId() {
        return assessmentId;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public int getAssessmentCourseId() {
        return assessmentCourseId;
    }

    //SETTERS//
    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public void setAssessmentCourseId(int assessmentCourseId) {
        this.assessmentCourseId = assessmentCourseId;
    }
}
