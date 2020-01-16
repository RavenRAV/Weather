package com.example.raven51.data.internet;


import com.example.raven51.data.entity.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("data/2.5/weather")
    Call<CurrentWeather> fetchCurrentWeather(@Query("q") String city,
                                             @Query("appid") String appId,
                                             @Query("units") String metric,
                                             @Query("dt") String UTC
                                             );
}

