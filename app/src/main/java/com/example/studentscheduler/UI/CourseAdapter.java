package com.example.studentscheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Entities.Course;

import com.example.studentscheduler.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;


        public CourseViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.courseItemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, DetailedCourseList.class);
                    intent.putExtra("ID", current.getCourseId());
                    intent.putExtra("Title", current.getCourseTitle());
                    intent.putExtra("StartDate", current.getCourseStartDate());
                    intent.putExtra("EndDate", current.getCourseEndDate());
                    intent.putExtra("Status", current.getCourseStatus());
                    intent.putExtra("InstructorName", current.getCourseInstructorsName());
                    intent.putExtra("InstructorPhone", current.getCourseInstructorsPhone());
                    intent.putExtra("InstructorEmail", current.getCourseInstructorsEmail());
                    intent.putExtra("Notes", current.getCourseNotes());
                    intent.putExtra("CourseTermId", current.getCourseTermId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            String courseTitle = current.getCourseTitle();
            holder.courseItemView.setText(courseTitle);

        } else {
            holder.courseItemView.setText("No Course Name");
        }
    }


    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }


}
