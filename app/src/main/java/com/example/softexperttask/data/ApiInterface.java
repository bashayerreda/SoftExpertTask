package com.example.softexperttask.data;

import com.example.softexperttask.pojo.ResponseCars;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/v1/cars")
   Observable<ResponseCars> getCars(@Query("page")int page);
}
