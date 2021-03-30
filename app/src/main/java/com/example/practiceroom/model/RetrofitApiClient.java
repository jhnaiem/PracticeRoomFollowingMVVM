package com.example.practiceroom.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {

    private static final String BASE_URL = "https://restcountries.eu";
    private static CountriesApi api = null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private RetrofitApiClient() {
    } // So that nobody can create an object with constructor

    public static CountriesApi getClient() {
        if (api == null) {
            synchronized (RetrofitApiClient.class) { //thread safe Singleton implementation
                if (api == null) {
                    api = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()
                            .create(CountriesApi.class);
                }
            }
        }

        return api;
    }


}
