package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entities.Term;

import java.util.List;

@Dao
public interface TermsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Term term);

    @Update
    public void update(Term term);

    @Delete
    public void delete(Term term);

    //DELETE TERM BY ID//
    @Query("DELETE FROM Terms WHERE termId = :termId")
    void deleteTermById(String termId);

    //GET ALL TERMS IN LIST//
    @Query("SELECT * FROM Terms ORDER BY termId ASC")
    List<Term> getAllTerms();

    //GET TERM BY ID//
    @Query("SELECT * FROM Terms WHERE termId = :termId")
    Term getTermById(String termId);


}
