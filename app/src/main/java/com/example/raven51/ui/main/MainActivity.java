package com.example.raven51.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.raven51.R;
import com.example.raven51.data.entity.current.CurrentWeather;
import com.example.raven51.data.entity.current.Weather;
import com.example.raven51.data.entity.forecast.ForecastEntity;
import com.example.raven51.data.internet.RetrofitBuilder;
import com.example.raven51.ui.Services.ServiceActivity;
import com.example.raven51.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.raven51.BuildConfig.API_KEY;

public class MainActivity extends BaseActivity {
    //    ArrayList<CurrentWeather>forWL;
    public static String WEATHER = "qwe";
    ForecastEntity forecastEntity;
    //    Weather weather;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.city)
    EditText editText;
    @BindView(R.id.temp_now)
    TextView tempNow;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.imageWeather)
    ImageView iconWeather;
    @BindView(R.id.temp_today_max)
    TextView tempMax;
    @BindView(R.id.temp_today_min)
    TextView tempMin;
    @BindView(R.id.pressure)
    TextView pressure;
    @BindView(R.id.humidityTV)
    TextView humidity;
    @BindView(R.id.descriptionTV)
    TextView description;
    @BindView(R.id.clouds)
    TextView clouds;
    @BindView(R.id.wDeg)
    TextView wDeg;
    @BindView(R.id.wSpeed)
    TextView wSpeed;
    @BindView(R.id.sunriseTV)
    TextView sunRise;
    @BindView(R.id.sunsetTV)
    TextView sunSet;

    @BindView(R.id.lat)
    TextView tvLat;
    @BindView(R.id.lon)
    TextView tvLon;


    @BindView(R.id.rViewWeek)
    RecyclerView recyclerView;

    private WeatherAdapter weatherAdapter;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListeners();
        initRecycler();
        fetchCurrentWeather("Bishkek");
        fetchForecastWeather("Bishkek");
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
//        getData();
    }

    private void initListeners() {
        button.setOnClickListener(v -> {
            fetchCurrentWeather(editText.getText().toString().trim());
            fetchForecastWeather(editText.getText().toString().trim());
        });

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void fetchCurrentWeather(String city) {
        RetrofitBuilder
                .getService()
                .fetchCurrentWeather(city, API_KEY, "metric", "lat", " lon")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillViews(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void fetchForecastWeather(String city) {
        RetrofitBuilder.getService().getForecast(city, API_KEY, "metric", "lat", " lon")
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        try {
                            if (response.isSuccessful() && response.body() != null ) {

                               forecastEntity = response.body();
                                weatherAdapter.update(forecastEntity.getForecastWeatherList());
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        Log.e("tag", "onFailure: ");
                    }
                });
    }


    private void fillViews(CurrentWeather weather) {
        tempMax.setText(weather.getMain().getTempMax().toString()+ getString(R.string.singtemp));
        tempMin.setText(weather.getMain().getTempMin().toString() + getString(R.string.singtemp));
        tempNow.setText(weather.getMain().getTemp().toString() + getString(R.string.singtemp));
        pressure.setText(weather.getMain().getPressure().toString());
        humidity.setText(weather.getMain().getHumidity().toString() + "%");
        clouds.setText(weather.getClouds().getAll().toString() + "%");
//        wDeg.setText(weather.getWind().getDeg().toString());
        sunRise.setText(DateHelper.convUNIX(weather.getSys().getSunrise()));
        sunSet.setText(DateHelper.convUNIX(weather.getSys().getSunset()));
        wSpeed.setText(weather.getWind().getSpeed().toString());
        description.setText(weather.getWeather().get(0).getDescription());
        tvLat.setText(weather.getCoord().getLat().toString());
        tvLon.setText(weather.getCoord().getLon().toString());
        day.setText(DateHelper.convUNIXDay(weather.getDt()));
        Glide.with(this).load("http://openweathermap.org/img/wn/" + weather.getWeather()
                .get(0).getIcon() + "@2x.png")
                .into(iconWeather);
    }

    private void initRecycler() {
        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);
    }

    public void SecondActivity(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }

//    private void getData(){
//        Intent intent = getIntent();
//        ForecastEntity forecastEntity = (ForecastEntity) intent.getSerializableExtra(WEATHER);
//        weatherAdapter.update(forecastEntity.getForecastWeatherList());
//
//    }


}
