package com.example.raven51.data.internet;


import com.example.raven51.data.entity.current.CurrentWeather;
import com.example.raven51.data.entity.forecast.ForecastEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.raven51.data.internet.ApiEndpoints.CURRENT;
import static com.example.raven51.data.internet.ApiEndpoints.FORECAST;

public interface RetrofitService {


    @GET(CURRENT)
    Call<CurrentWeather> fetchCurrentWeather(@Query("q") String city,
                                             @Query("appid") String appId,
                                             @Query("units") String metric,
                                             @Query("lat") String lat,
                                             @Query("lon") String lon);

    @GET(FORECAST)
    Call<ForecastEntity>getForecast(@Query("q") String city,
                                    @Query("appid") String appId,
                                    @Query("units") String metric,
                                    @Query("lat") String lat,
                                    @Query("lon") String lon);
}

