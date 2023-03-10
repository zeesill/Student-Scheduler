package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entities.Course;

import java.util.List;

@Dao
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Course course);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Course course);

    @Delete
    public void delete(Course course);

    @Query("DELETE FROM Courses WHERE courseId = :courseId")
    public void deleteCourseById(String courseId);

    //GET ALL COURSES//
    @Query("SELECT * FROM Courses ORDER BY courseId ASC")
    List<Course> getAllCourses();

    //GET ALL COURSES ASSOCIATED WITH A PARTICULAR TERM//
    @Query("SELECT * FROM Courses WHERE courseTermId = :courseTermId ORDER BY courseId ASC")
    List<Course> getAllAssociatedCoursesWithTerm(int courseTermId);


    @Query("UPDATE Courses SET courseTitle=:courseTitle, courseStartDate = :courseStartDate, courseEndDate = :courseEndDate, courseStatus = :courseStatus, courseInstructorsName = :courseInstructorsName, courseInstructorsPhone = :courseInstructorsPhone, courseInstructorsEmail = :courseInstructorsEmail, courseNotes = :courseNotes, courseTermId = :courseTermId WHERE courseId=:courseId")
    void updateCourse(String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorsName, String courseInstructorsPhone, String courseInstructorsEmail, String courseNotes, int courseTermId, int courseId);


}
