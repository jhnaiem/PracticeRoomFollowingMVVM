package com.example.practiceroom.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    void insert(Country country);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Country> country);

    @Update
    void update(Country country);

    @Delete
    void delete(Country country);

    @Query("SELECT * FROM countries_table ORDER BY numericCode ASC")
    LiveData<List<Country>> getAllCountires();
}
