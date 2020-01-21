package com.example.raven51.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raven51.R;
import com.example.raven51.data.entity.current.CurrentWeather;

import butterknife.BindView;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.week_date)
    TextView weekDate;
    @BindView(R.id.week_image)
    ImageView weekImage;
    @BindView(R.id.week_temp_max)
    TextView weekTempMax;
    @BindView(R.id.week_temp_min)
    TextView weekTempMin;
    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void fillViewsForecast(CurrentWeather currentWeather){
        weekDate.setText(DateHelper.convUNIXDay(currentWeather.getDt()));
        weekTempMax.setText(currentWeather.getMain().getTempMax().toString());
        weekTempMin.setText(currentWeather.getMain().getTempMin().toString());

    }
}
