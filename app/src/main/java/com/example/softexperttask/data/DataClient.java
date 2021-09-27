package com.example.softexperttask.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataClient {
    public static final String BASE_URL = "https://demo1585915.mockable.io";
    ApiInterface apiInterfaceInstance;
    private static DataClient dataClientInstance;

    public DataClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterfaceInstance = retrofit.create(ApiInterface.class);
    }

    public static DataClient getDataClientInstance() {
        if (dataClientInstance == null){
            dataClientInstance = new DataClient();
        }
        return dataClientInstance;
    }
}
