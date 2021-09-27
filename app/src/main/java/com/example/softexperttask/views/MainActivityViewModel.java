package com.example.softexperttask.views;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.softexperttask.data.Action;
import com.example.softexperttask.data.Repo;
import com.example.softexperttask.pojo.Cars;
import com.example.softexperttask.pojo.ResponseCars;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    public int page = 1;
    Repo repo = new Repo();
    List<Cars> carsList = new ArrayList<>();
    public MutableLiveData<List<Cars>> cars = new MutableLiveData<>();
    public MutableLiveData<Action> action = new MutableLiveData<>();

    public void getAllCars() {
        if (page == -1)
            return;

        action.postValue(Action.LOADING);
        repo.getAllCars(page).enqueue(new Callback<ResponseCars>() {
            @Override
            public void onResponse(Call<ResponseCars> call, Response<ResponseCars> response) {
                if (response.body() != null && response.body().getStatus() == 1 && response.body().getCars().size() != 0) {
                    carsList.addAll(response.body().getCars());
                    cars.postValue(carsList);
                    page++;
                }
                action.postValue(Action.SUCCESS);


            }


            @Override
            public void onFailure(Call<ResponseCars> call, Throwable t) {
                action.postValue(Action.ERROR);

            }
        });
    }
}
