package com.example.practiceroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.practiceroom.model.Country;
import com.example.practiceroom.model.CountryRepository;
import com.example.practiceroom.model.RetrofitApiClient;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListViewModel extends AndroidViewModel {

    private LiveData<List<Country>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private CountryRepository countryRepository;


    public ListViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        countries = countryRepository.getAllCountries();

    }

    public LiveData<List<Country>> getCountriesLive() {
        return countryRepository.getAllCountries();
    }


    public void refresh() {
        fetchCountries();

    }


    private void fetchCountries() {

        loading.setValue(true);

        Call<List<Country>> call = RetrofitApiClient.getClient().getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                List<Country> storeCountryList = response.body();

                if (response.code() == 200 && storeCountryList != null) {


                    // countries.setValue(storeCountryList);
                    countryRepository.insertListCountry(storeCountryList);
                    countryLoadError.setValue(false);
                    loading.setValue(false);


                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                countryLoadError.setValue(true);
                loading.setValue(false);

            }
        });


    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
