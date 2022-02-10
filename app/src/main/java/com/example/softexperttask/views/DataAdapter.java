package com.example.softexperttask.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.softexperttask.R;
import com.example.softexperttask.data.actionInterface;
import com.example.softexperttask.pojo.Cars;


import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.PostViewHolder> {
    List<Cars> cars;
    actionInterface actions;

    public DataAdapter(actionInterface actions) {
        this.actions = actions;
        this.cars = new ArrayList<>();
    }

    public void setData(List<Cars> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.PostViewHolder holder, int position) {
        holder.onBind(cars.get(position));
        if (position == cars.size() - 1)
            actions.loadingNext();
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView brandName, isUsed, constYear;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_car);
            brandName = itemView.findViewById(R.id.brand_name);
            isUsed = itemView.findViewById(R.id.is_used);
            constYear = itemView.findViewById(R.id.const_year);

        }

        void onBind(Cars car) {
            brandName.setText(car.getBrand());
            constYear.setText(car.getConstractionYear());
            isUsed.setText(String.valueOf(car.isUsed()));
            Glide
                    .with(itemView)
                    .load(car.getImageUrl())
                    .centerCrop()
                    .into(imageView);

        }
    }
}

