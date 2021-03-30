package com.example.practiceroom.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesApi {

    @GET("/rest/v2/all")
    Call<List<Country>> getCountries();



}
