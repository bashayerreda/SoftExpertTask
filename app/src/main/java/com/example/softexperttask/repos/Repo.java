package com.example.softexperttask.repos;

import com.example.softexperttask.data.ApiInterface;
import com.example.softexperttask.pojo.ResponseCars;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

public class Repo {
    private ApiInterface apiInterface;
    @Inject
    public Repo(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<ResponseCars> getCars(int page){
        return apiInterface.getCars(page);

    }

}
