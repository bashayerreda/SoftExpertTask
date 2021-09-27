package com.example.softexperttask.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCars {
    @SerializedName("data")
    private List<Cars> cars;
    private int status;

    public List<Cars> getCars() {


        return cars;
    }

    public int getStatus() {

        return status;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
