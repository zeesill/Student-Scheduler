package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.R;

public class EditAssessment extends AppCompatActivity {

    EditText assessmentTitleEditView;
    EditText assessmentStartDateEditView;
    EditText assessmentEndDateEditView;


    String assessmentTitle;
    String assessmentStartDate;
    String assessmentEndDate;


    String spinnerValue;

    Repository repository;

    int assessmentId;
    int assessmentCourseId;

    Assessment assessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        setTitle("Edit Assessment");

        assessmentTitleEditView = findViewById(R.id.editAssessmentTitle);
        assessmentStartDateEditView = findViewById(R.id.editAssessmentStartDate);
        assessmentEndDateEditView = findViewById(R.id.editAssessmentEndDate);

        assessmentTitle = DetailedAssessmentList.assessmentTitleFinal;
        assessmentTitleEditView.setText(assessmentTitle);

        assessmentStartDate = DetailedAssessmentList.assessmentStartDateFinal;
        assessmentStartDateEditView.setText(assessmentStartDate);

        assessmentEndDate = DetailedAssessmentList.assessmentEndDateFinal;
        assessmentEndDateEditView.setText(assessmentEndDate);

        assessmentId = DetailedAssessmentList.assessmentId;
        assessmentCourseId = DetailedAssessmentList.assessmentCourseId;

        repository = new Repository(getApplication());

        //dropdown is spinner//
        Spinner dropdown = findViewById(R.id.editAssessmentSpinner);
        String[] items = new String[]{"Performance", "Objective"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        for (int i = 0; i < items.length; i++) {
            if (DetailedAssessmentList.assessmentTypeFinal.equals(items[i])) {
                dropdown.setSelection(i);
            }
        }
        Button button = findViewById(R.id.saveEditAssessment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //course = new Course("New Title", courseEndDateView.getText().toString(), courseStartDateView.getText().toString(), courseStatusView.getText().toString(), courseInstructorNameView.getText().toString(), courseInstructorPhoneView.getText().toString(), courseInstructorEmailView.getText().toString(), courseNotesView.getText().toString(), courseTermId);
                spinnerValue = dropdown.getSelectedItem().toString();
                repository.updateAssessment(assessmentTitleEditView.getText().toString(), assessmentStartDateEditView.getText().toString(), assessmentEndDateEditView.getText().toString(), spinnerValue, assessmentCourseId, assessmentId);
                Context context = getApplicationContext();
                String text = "Assessment has been Edited!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}