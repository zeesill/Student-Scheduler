package com.example.studentscheduler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.studentscheduler.DAO.AssessmentsDAO;
import com.example.studentscheduler.DAO.CoursesDAO;
import com.example.studentscheduler.DAO.TermsDAO;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1, exportSchema = false)
public abstract class StudentSchedulerDatabase extends RoomDatabase {
    public abstract AssessmentsDAO assessmentsDAO();

    public abstract CoursesDAO coursesDAO();

    public abstract TermsDAO termsDAO();

    private static volatile StudentSchedulerDatabase INSTANCE;

    static StudentSchedulerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentSchedulerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StudentSchedulerDatabase.class, "StudentScheduler.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
