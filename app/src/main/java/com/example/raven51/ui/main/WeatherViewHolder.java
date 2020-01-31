package com.example.raven51.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.raven51.R;
import com.example.raven51.data.entity.current.CurrentWeather;
import com.example.raven51.data.entity.current.Weather;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        init();
//        ButterKnife.bind(this, itemView);
    }

    private void init(){
        weekDate = itemView.findViewById(R.id.week_date);
        weekImage = itemView.findViewById(R.id.week_image);
        weekTempMax = itemView.findViewById(R.id.week_temp_max);
        weekTempMin = itemView.findViewById(R.id.week_temp_min);
    }

    public void fillViewsForecast(CurrentWeather currentWeather) {
        weekDate.setText(DateHelper.convUNIXDay(currentWeather.getDt()));
        weekTempMax.setText(currentWeather.getMain().getTempMax().toString());
        weekTempMin.setText(currentWeather.getMain().getTempMin().toString());
        Glide.with(itemView).load("http://openweathermap.org/img/wn/" + currentWeather.getWeather()
                .get(0).getIcon() + "@2x.png").into(weekImage);


    }
}
