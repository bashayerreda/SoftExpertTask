package com.example.softexperttask.data;

import com.example.softexperttask.pojo.ResponseCars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/v1/cars")
    Call<ResponseCars> getAllCars(@Query("page") int page);
}
