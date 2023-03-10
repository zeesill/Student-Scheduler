package com.example.studentscheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Entities.Assessment;
import com.example.studentscheduler.Entities.Course;
import com.example.studentscheduler.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    public class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;


        public AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.assessmentItemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, DetailedAssessmentList.class);
                    intent.putExtra("ID", current.getAssessmentId());
                    intent.putExtra("Title", current.getAssessmentTitle());
                    intent.putExtra("StartDate", current.getAssessmentStartDate());
                    intent.putExtra("EndDate", current.getAssessmentEndDate());
                    intent.putExtra("Type", current.getAssessmentType());
                    intent.putExtra("CourseID", current.getAssessmentCourseId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assessment> mAssessments;
    private final Context context;

    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            String assessmentTitle = current.getAssessmentTitle();
            holder.assessmentItemView.setText(assessmentTitle);

        } else {
            holder.assessmentItemView.setText("No Assessment Name");
        }
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public void setAssessments(List<Assessment> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}
