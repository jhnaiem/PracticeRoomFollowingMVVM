package com.example.practiceroom.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Model class
 */

@Entity(tableName = "countries_table")
public class Country {


    @PrimaryKey
    private int numericCode;
    private String name;
    private String capital;
    private String flag;

    public Country(String name, String capital, String flag) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
    }

    public int getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(int numericCode) {
        this.numericCode = numericCode;
    }

    public String getCapital() {
        return capital;
    }


    public String getFlag() {
        return flag;
    }


    public String getName() {
        return name;
    }
}
