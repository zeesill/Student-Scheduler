package com.example.studentscheduler.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    int termId; //PRIMARY KEY//

    private String termTitle;
    private String termStartDate;
    private String termEndDate;


    //CONSTRUCTORS//
    public Term(String termTitle, String termStartDate, String termEndDate) {
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public Term() {
    }

    //GETTERS//
    public int getTermId() {
        return termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }


    //SETTERS//
    public void setTermId(int termId) {
        this.termId = termId;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }
}
