package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailedCourseList extends AppCompatActivity {
    ///TESTING///
    private static final String DATE_PATTERN =
            "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|[3][01])/\\d{2}$";
    ///////////


    String myFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detailed_course_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");
                i.putExtra(Intent.EXTRA_TEXT, courseNotes);
                this.startActivity(i);
                return true;
            case R.id.item2:

                Date current = Calendar.getInstance().getTime();
                String formattedDate = sdf.format(current);
                Date formattedCurrentDate = null;
                try {
                    formattedCurrentDate = sdf.parse(formattedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                String dateFromScreen = courseStartDateView.getText().toString();
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date myDate = null;

                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (dateFromScreen.isEmpty()) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - Start Date is empty";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (!dateFromScreen.matches(DATE_PATTERN)) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - Start Date must be formatted - MM/DD/YY";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (myDate.before(formattedCurrentDate)) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - Start Date is in the Past";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Long trigger = myDate.getTime();

                    Context context = getApplicationContext();
                    String text = "Alert has been set! You will be notified on " + dateFromScreen;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = new Intent(DetailedCourseList.this, MyReceiver.class);
                    intent.putExtra("key", "Getting excited? Your course begins today!");
                    PendingIntent sender = PendingIntent.getBroadcast(DetailedCourseList.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
                return false;

            case R.id.item3:
                current = Calendar.getInstance().getTime();
                formattedCurrentDate = null;
                myDate = null;
                dateFromScreen = courseEndDateView.getText().toString();
                myFormat = "MM/dd/yy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                formattedDate = sdf.format(current);
                try {
                    formattedCurrentDate = sdf.parse(formattedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateFromScreen = courseEndDateView.getText().toString();
                Long trigger = myDate.getTime();

                if (dateFromScreen.isEmpty()) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - End Date is empty";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (!dateFromScreen.matches(DATE_PATTERN)) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - End Date must be formatted - MM/DD/YY";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (myDate.before(formattedCurrentDate)) {
                    Context context = getApplicationContext();
                    String text = "Cannot set up alert - End Date is in the Past";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Context context = getApplicationContext();
                    String text = "Alert has been set! You will be notified on " + dateFromScreen;
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    Intent intent = new Intent(DetailedCourseList.this, MyReceiver.class);
                    intent.putExtra("key", "Getting excited? Your course ends today!");
                    PendingIntent sender = PendingIntent.getBroadcast(DetailedCourseList.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    TextView courseTitleView;
    TextView courseStartDateView;
    TextView courseEndDateView;
    TextView courseStatusView;
    TextView courseInstructorNameView;
    TextView courseInstructorPhoneView;
    TextView courseInstructorEmailView;
    TextView courseNotesView;

    static String courseTitle;
    static String courseStartDate;
    static String courseEndDate;
    static String courseStatus;
    static String courseInstructorName;
    static String courseInstructorPhone;
    static String courseInstructorEmail;
    static String courseNotes;

    static int courseTermId;

    static int courseId;

    Button button;

    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course_list);
        setTitle("Detailed Course Information");


        courseTitleView = findViewById(R.id.courseTitle);
        courseStartDateView = findViewById(R.id.courseStartDate);
        courseEndDateView = findViewById(R.id.courseEndDate);
        courseStatusView = findViewById(R.id.courseStatus);
        courseInstructorNameView = findViewById(R.id.courseInstructorName);
        courseInstructorPhoneView = findViewById(R.id.courseInstructorPhone);
        courseInstructorEmailView = findViewById(R.id.courseInstructorEmail);
        courseNotesView = findViewById(R.id.courseNotes);

        courseId = getIntent().getIntExtra("ID", -1);

        courseTitle = getIntent().getStringExtra("Title");
        courseTitleView.setText(courseTitle);

        courseStartDate = getIntent().getStringExtra("StartDate");
        courseStartDateView.setText(courseStartDate);

        courseEndDate = getIntent().getStringExtra("EndDate");
        courseEndDateView.setText(courseEndDate);

        courseStatus = getIntent().getStringExtra("Status");
        courseStatusView.setText(courseStatus);

        courseInstructorName = getIntent().getStringExtra("InstructorName");
        courseInstructorNameView.setText(courseInstructorName);

        courseInstructorPhone = getIntent().getStringExtra("InstructorPhone");
        courseInstructorPhoneView.setText(courseInstructorPhone);

        courseInstructorEmail = getIntent().getStringExtra("InstructorEmail");
        courseInstructorEmailView.setText(courseInstructorEmail);

        courseNotes = getIntent().getStringExtra("Notes");
        courseNotesView.setText(courseNotes);

        courseTermId = getIntent().getIntExtra("CourseTermId", -1);

        repository = new Repository(getApplication());


    }

    public void toEditCourseBtnPushed(View v) {
        Intent toEditCourse = new Intent(this, EditCourse.class);
        startActivity(toEditCourse);
    }

    public void deleteCourseBtnPushed(View v) {
        for (Course course : repository.getAllCourses()) {
            if (course.getCourseId() == courseId) {
                repository.delete(course);
                Context context = getApplicationContext();
                String text = "Course Deleted!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void toAssessmentsList(View v) {
        Intent toAssessmentsList = new Intent(this, AssessmentList.class);
        toAssessmentsList.putExtra("ID", courseId);
        toAssessmentsList.putExtra("Title", courseTitle);
        startActivity(toAssessmentsList);
    }


}