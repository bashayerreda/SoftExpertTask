package com.example.softexperttask.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.softexperttask.R;
import com.example.softexperttask.data.Action;
import com.example.softexperttask.data.actionInterface;
import com.example.softexperttask.pojo.Cars;
import com.example.softexperttask.viewmodels.MainActivityViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements actionInterface {
    MainActivityViewModel mainActivityViewModel;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ProgressBar loadingProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingProgressBar = findViewById(R.id.loading_Progress);
        //mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getCars();

        recyclerView = findViewById(R.id.rec_cars);
        adapter = new DataAdapter( this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mainActivityViewModel.mutableLiveDataCars.observe(this, new Observer<List<Cars>>() {
            @Override
            public void onChanged(List<Cars> cars) {
                adapter.setData(cars);
            }
        });
        mainActivityViewModel.mutableLiveDataAction.observe(this, new Observer<Action>() {
            @Override
            public void onChanged(Action action) {
                switch (action){
                    case LOADING: Loading();
                        break;
                    case SUCCESS: Success();
                        break;
                    case ERROR: Error();
                        break;
                }
            }
        });

    }


    @Override
    public void Loading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void Success() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void Error() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadingNext() {
        mainActivityViewModel.getCars();
    }
}