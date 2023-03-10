package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailedAssessmentList extends AppCompatActivity {

    TextView assessmentTitleView;
    TextView assessmentStartDateView;
    TextView assessmentEndDateView;
    TextView assessmentTypeView;

    static String assessmentTitleFinal;
    static String assessmentStartDateFinal;
    static String assessmentEndDateFinal;
    static String assessmentTypeFinal;

    static int assessmentId;
    static int assessmentCourseId;
    Repository repository;

    String myFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    private static final String DATE_PATTERN =
            "^(0[1-9]|1[012])/(0[1-9]|[12][0-9]|[3][01])/\\d{2}$";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detailed_assessment_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemAssessment1:

                Date current = Calendar.getInstance().getTime();
                String formattedDate = sdf.format(current);
                Date formattedCurrentDate = null;
                try {
                    formattedCurrentDate = sdf.parse(formattedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                String dateFromScreen = assessmentStartDateView.getText().toString();
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

                    Intent intent = new Intent(DetailedAssessmentList.this, MyReceiver.class);
                    intent.putExtra("key", "Good Luck! Your assessment begins today!");
                    PendingIntent sender = PendingIntent.getBroadcast(DetailedAssessmentList.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }
                return false;
            case R.id.itemAssessment2:
                current = Calendar.getInstance().getTime();
                formattedCurrentDate = null;
                myDate = null;
                dateFromScreen = assessmentEndDateView.getText().toString();
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
                dateFromScreen = assessmentEndDateView.getText().toString();
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

                    Intent intent = new Intent(DetailedAssessmentList.this, MyReceiver.class);
                    intent.putExtra("key", "Great Work! Assessment is due today!");
                    PendingIntent sender = PendingIntent.getBroadcast(DetailedAssessmentList.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                    return true;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_assessment_list);
        setTitle("Detailed Assessment Information");

        assessmentTitleView = findViewById(R.id.assessmentTitle);
        assessmentStartDateView = findViewById(R.id.assessmentStartDate);
        assessmentEndDateView = findViewById(R.id.assessmentEndDate);
        assessmentTypeView = findViewById(R.id.assessmentType);

        assessmentId = getIntent().getIntExtra("ID", -1);
        assessmentCourseId = getIntent().getIntExtra("CourseID", -1);


        assessmentTitleFinal = getIntent().getStringExtra("Title");
        assessmentTitleView.setText(assessmentTitleFinal);

        assessmentStartDateFinal = getIntent().getStringExtra("StartDate");
        assessmentStartDateView.setText(assessmentStartDateFinal);

        assessmentEndDateFinal = getIntent().getStringExtra("EndDate");
        assessmentEndDateView.setText(assessmentEndDateFinal);

        assessmentTypeFinal = getIntent().getStringExtra("Type");
        assessmentTypeView.setText(assessmentTypeFinal);

        repository = new Repository(getApplication());
    }

    public void deleteAssessmentBtnPushed(View v) {
        for (Assessment assessment : repository.getAllAssessments()) {
            if (assessment.getAssessmentId() == assessmentId) {
                repository.deleteAssessmentById(assessmentId);
                Context context = getApplicationContext();
                String text = "Assessment Deleted!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void toEditAssessmentBtnPushed(View v) {
        Intent toEditAssessment = new Intent(this, EditAssessment.class);
        startActivity(toEditAssessment);
    }


}