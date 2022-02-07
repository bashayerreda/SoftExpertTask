package com.example.softexperttask.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.softexperttask.data.Action;
import com.example.softexperttask.repos.Repo;
import com.example.softexperttask.pojo.Cars;
import com.example.softexperttask.pojo.ResponseCars;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {
    private Repo repo;
    @ViewModelInject
    public MainActivityViewModel(Repo repo) {
        this.repo = repo;
    }
    public int page = 1;
        private static final String TAG = "MainActivityViewModel";
       public MutableLiveData<List<Cars>> mutableLiveDataCars =  new MutableLiveData<>();
        List<Cars> cars = new ArrayList<>();
        ResponseCars responseCars = new ResponseCars();
       public MutableLiveData<Action> mutableLiveDataAction = new MutableLiveData<>();
        @SuppressLint("CheckResult")
        public void getCars() {
            if (page == -1)
                return;

            mutableLiveDataAction.setValue(Action.LOADING);

            Observable observableCars = repo.getCars(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

            Observer<ResponseCars> Observer = new Observer<ResponseCars>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                }

                @Override
                public void onNext(@NonNull ResponseCars responseCars) {
                    if(responseCars.getCars() != null) {
                        cars.addAll(responseCars.getCars());
                        mutableLiveDataCars.setValue(cars);

                        page++;
                    }
                    mutableLiveDataAction.setValue(Action.SUCCESS);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "onError: " +e.getMessage());
                    mutableLiveDataAction.postValue(Action.ERROR);

                }

                @Override
                public void onComplete() {


                }
            };

            observableCars.subscribe(Observer);


        }


    }
