package com.example.raven51.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raven51.R;
import com.example.raven51.data.entity.current.CurrentWeather;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private ArrayList<CurrentWeather> forecastWeatherList = new ArrayList<>();
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_week, parent, false);
        return new WeatherViewHolder(view);
    }
    public void update(@NonNull ArrayList<CurrentWeather> forecastWeatherList){
        this.forecastWeatherList = forecastWeatherList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.fillViewsForecast(forecastWeatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastWeatherList.size();
    }
}
