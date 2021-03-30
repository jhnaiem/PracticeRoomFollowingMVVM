package com.example.practiceroom.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {

    private CountryDao countryDao;
    private LiveData<List<Country>> allCountries;
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();


    public CountryRepository(Application application) {

        //fetchCountries();
        CountryDatabase countryDatabase = CountryDatabase.getInstance(application);
        countryDao = countryDatabase.countryDao();
        allCountries = countryDao.getAllCountires();

    }

//    public void insert(Country country){
//        new InsertCountryAsyncTask(countryDao).execute(country);
//
//    }

    public void insertListCountry(List<Country> countryList) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            public void run() {
                countryDao.insertList(countryList);
            }
        });


    }

    public LiveData<List<Country>> getAllCountries() {

        return allCountries;
    }




}
