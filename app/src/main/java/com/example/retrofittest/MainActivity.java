package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofittest.adapter.CountryAdapter;
import com.example.retrofittest.model.CountriesInfo;
import com.example.retrofittest.model.Result;
import com.example.retrofittest.service.CountryService;
import com.example.retrofittest.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> resultArrayList;
    private CountryAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCountries();
    }

    private Object getCountries() {

        CountryService countryService = RetrofitInstance.getService();
        Call<CountriesInfo> call = countryService.getResults();

        call.enqueue(new Callback<CountriesInfo>() {
            @Override
            public void onResponse(Call<CountriesInfo> call, Response<CountriesInfo> response) {

                CountriesInfo countriesInfo = response.body();
                if (countriesInfo != null && countriesInfo.getRestResponse() != null) {

                    resultArrayList =
                            (ArrayList<Result>) countriesInfo.getRestResponse()
                                    .getResult();

                    // for (Result result : resultArrayList) {
                    //Log.d("resultArrayList:", result.getName());

                    //}
                    fillRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<CountriesInfo> call, Throwable t) {

            }

        });

        return resultArrayList;

    }

    private void fillRecyclerView() {

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new CountryAdapter(resultArrayList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
