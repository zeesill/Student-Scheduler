package com.example.studentscheduler.Database;

import android.app.Application;

import com.example.studentscheduler.DAO.AssessmentsDAO;
import com.example.studentscheduler.DAO.CoursesDAO;
import com.example.studentscheduler.DAO.TermsDAO;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssessmentsDAO mAssessmentsDao;
    private CoursesDAO mCoursesDao;
    private TermsDAO mTermsDao;


    private List<Assessment> mAllAssessments;
    private List<Course> mAllCourses;
    private List<Term> mAllTerms;

    private Course mCourse;


    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //CONSTRUCTOR//
    public Repository(Application application) {
        StudentSchedulerDatabase db = StudentSchedulerDatabase.getDatabase(application);
        mTermsDao = db.termsDAO();
        mCoursesDao = db.coursesDAO();
        mAssessmentsDao = db.assessmentsDAO();
    }

    //GET ALL//
    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            mAllTerms = mTermsDao.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public List<Course> getAllCourses() {
        databaseExecutor.execute(() -> {
            mAllCourses = mCoursesDao.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<Course> getAllCoursesByTermId(int termId) {
        databaseExecutor.execute(() -> {
            mAllCourses = mCoursesDao.getAllAssociatedCoursesWithTerm(termId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }


    //TESTING//////////////////////////////
    public void updateCourse(String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorsName, String courseInstructorsPhone, String courseInstructorsEmail, String courseNotes, int courseTermId, int courseId) {
        databaseExecutor.execute(() -> {
            mCoursesDao.updateCourse(courseTitle, courseStartDate, courseEndDate, courseStatus, courseInstructorsName, courseInstructorsPhone, courseInstructorsEmail, courseNotes, courseTermId, courseId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<Assessment> getAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentsDao.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public List<Assessment> getAllAssociatedAssessmentsInCourse(int currentCourse) {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentsDao.getAllAssociatedAssessmentsInCourse(currentCourse);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }


    //TERMS INSERT UPDATE DELETE//
    //////////////////////////////

    public void insert(Term term) {
        databaseExecutor.execute(() -> {
            mTermsDao.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term) {
        databaseExecutor.execute(() -> {
            mTermsDao.update(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        databaseExecutor.execute(() -> {
            mTermsDao.delete(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //COURSES UPDATE INSERT DELETE//
    ////////////////////////////////

    public void insert(Course course) {
        databaseExecutor.execute(() -> {
            mCoursesDao.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        databaseExecutor.execute(() -> {
            mCoursesDao.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        databaseExecutor.execute(() -> {
            mCoursesDao.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //ASSESSMENTS INSERT UPDATE DELETE//
    ////////////////////////////////////

    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentsDao.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentsDao.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentsDao.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssessmentById(int assessmentId) {
        databaseExecutor.execute(() -> {
            mAssessmentsDao.deleteAssessmentById(assessmentId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void updateAssessment(String assessmentTitle, String assessmentStartDate, String assessmentEndDate, String assessmentType, int assessmentCourseId, int assessmentId) {
        databaseExecutor.execute(() -> {
            mAssessmentsDao.updateAssessment(assessmentTitle, assessmentStartDate, assessmentEndDate, assessmentType, assessmentCourseId, assessmentId);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
