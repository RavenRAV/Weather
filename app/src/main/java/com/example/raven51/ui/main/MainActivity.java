package com.example.raven51.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.raven51.R;
import com.example.raven51.data.NotificationHelper;
import com.example.raven51.data.entity.current.CurrentWeather;
import com.example.raven51.data.entity.forecast.ForecastEntity;
import com.example.raven51.data.internet.RetrofitBuilder;
import com.example.raven51.ui.Services.ServiceActivity;
import com.example.raven51.ui.base.BaseActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.raven51.BuildConfig.API_KEY;
import static com.example.raven51.data.internet.ApiEndpoints.IMAGE_API;
import static com.example.raven51.data.internet.ApiEndpoints.IMG;

public class MainActivity extends BaseActivity {
    //    ArrayList<CurrentWeather>forWL;
    private String latitude;
    private String longitude;
    public static String WEATHER = "qwe";
    private FusedLocationProviderClient fusedLocationProviderClient;
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

        getLocation();
        permissions();
//        getData();
    }

    private void initListeners() {
        button.setOnClickListener(v -> {
            fetchCurrentWeather(editText.getText().toString().trim());
            fetchForecastWeather(editText.getText().toString().trim());
        });

    }

    //
    public void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                latitude = (String.valueOf(location.getLatitude()));
                longitude = (String.valueOf(location.getLongitude()));
                tvLat.setText(latitude);
                tvLon.setText(longitude);
                fetchCurrentWeatherByCoordinates();
            }


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

    private void fetchCurrentWeatherByCoordinates() {
        RetrofitBuilder
                .getService()
                .fetchCurrentWeatherByCoordinates(API_KEY, "metric", latitude, longitude)
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            fillViews(response.body());
                            editText.setText(response.body().getName());

                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {

                    }
                });
    }

    private void fetchForecastWeather(String city) {
        RetrofitBuilder.getService().getForecast(city, API_KEY, "metric", "lat", " lon")
                .enqueue(new Callback<ForecastEntity>() {
                    @Override
                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
                        try {
                            if (response.isSuccessful() && response.body() != null) {

                                forecastEntity = response.body();
                                weatherAdapter.update(forecastEntity.getForecastWeatherList());
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), " что-то пошло не так" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.e("tag", "onFailure: ");
                    }
                });
    }


    private void fillViews(CurrentWeather weather) {
        tempMax.setText(weather.getMain().getTempMax().toString() + getString(R.string.singtemp));
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

//        tvLat.setText(latitude);
//        tvLon.setText(longitude);

        day.setText(DateHelper.convUNIXDay(weather.getDt()));
        Glide.with(this).load(IMAGE_API + weather.getWeather()
                .get(0).getIcon() + IMG)
                .into(iconWeather);
    }

    private void initRecycler() {
        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);
    }

    public void SecondActivity(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }

    private void permissions() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            tvLat.setText(latitude);
            tvLon.setText(longitude);

        } else {

        }
    }


//    private void getData(){
//        Intent intent = getIntent();
//        ForecastEntity forecastEntity = (ForecastEntity) intent.getSerializableExtra(WEATHER);
//        weatherAdapter.update(forecastEntity.getForecastWeatherList());
//
//    }


}
