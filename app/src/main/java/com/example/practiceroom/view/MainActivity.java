package com.example.practiceroom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.practiceroom.R;
import com.example.practiceroom.model.Country;
import com.example.practiceroom.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListViewModel listViewModel;
    private CountryListAdapter countryListAdapter = new CountryListAdapter(new ArrayList<Country>());

    private RecyclerView countriesList;
    private TextView mTextError;
    private ProgressBar progressBar;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countriesList = findViewById(R.id.countriesList);
        mTextError = findViewById(R.id.list_error);
        progressBar = findViewById(R.id.loading_view);
        swipeRefreshLayout = findViewById(R.id.swipeerLayout);

        listViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(ListViewModel.class);

        //To refresh country list fetching using the API
        listViewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(countryListAdapter);

        observeViewModel();


        //Refreshing fetch countries
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //setting Refreshing to false
                swipeRefreshLayout.setRefreshing(false);


            }
        });



    }


    //Observe if country list has been updated
    private void observeViewModel() {
        listViewModel.getCountriesLive().observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                if (!countries.isEmpty()) {
                    countriesList.setVisibility(View.VISIBLE); // Make the country list recyclerview visible
                    countryListAdapter.updateCountire(countries);
                    countryListAdapter.notifyDataSetChanged();
                }

            }
        });

        listViewModel.countryLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    mTextError.setVisibility(View.VISIBLE);
                else
                    mTextError.setVisibility(View.GONE);

            }
        });


        listViewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                    mTextError.setVisibility(View.GONE);
                    countriesList.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.GONE);
                }

            }

        });
    }
}