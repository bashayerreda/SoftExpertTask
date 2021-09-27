package com.example.softexperttask.data;

import com.example.softexperttask.pojo.ResponseCars;

import retrofit2.Call;

public class Repo {
    public Call<ResponseCars> getAllCars(int page){
        return DataClient.getDataClientInstance().apiInterfaceInstance.getAllCars(page);
    }
}
