package com.example.retrofittest.service;

import com.example.retrofittest.model.CountriesInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    @GET ("country/get/all")
    Call<CountriesInfo> getResults();
}
