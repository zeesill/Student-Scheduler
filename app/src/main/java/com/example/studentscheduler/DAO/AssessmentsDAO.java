package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Assessment assessment);

    @Update
    public void update(Assessment assessment);

    @Delete
    public void delete(Assessment assessment);

    @Query("DELETE FROM assessments WHERE assessmentId = :assessmentId")
    void deleteAssessmentById(int assessmentId);

    //GET ALL ASSESSMENTS SQL QUERY//
    @Query("SELECT * FROM Assessments ORDER BY assessmentId ASC")
    List<Assessment> getAllAssessments();

    //GET ALL ASSESSMENTS PERTAINING TO SPECIFIC COURSE//
    @Query("SELECT * FROM Assessments WHERE assessmentCourseId = :currentCourse ORDER BY assessmentId ASC")
    List<Assessment> getAllAssociatedAssessmentsInCourse(int currentCourse);

    //GET ASSESSMENT BY ID//
    @Query("SELECT * FROM Assessments WHERE assessmentId = :assessmentId")
    Assessment getAssessmentById(String assessmentId);

    @Query("UPDATE Assessments SET assessmentTitle = :assessmentTitle, assessmentStartDate = :assessmentStartDate, assessmentEndDate = :assessmentEndDate, assessmentType = :assessmentType, assessmentCourseId = :assessmentCourseId WHERE assessmentId = :assessmentId")
    void updateAssessment(String assessmentTitle, String assessmentStartDate, String assessmentEndDate, String assessmentType, int assessmentCourseId, int assessmentId);
}
