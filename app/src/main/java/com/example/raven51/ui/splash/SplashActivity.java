package com.example.raven51.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.raven51.R;
import com.example.raven51.data.PreferenceHelper;
import com.example.raven51.data.entity.forecast.ForecastEntity;
import com.example.raven51.data.internet.RetrofitBuilder;
import com.example.raven51.ui.base.BaseActivity;
import com.example.raven51.ui.main.MainActivity;
import com.example.raven51.ui.onboard.OnBoardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.raven51.BuildConfig.API_KEY;
import static com.example.raven51.ui.main.MainActivity.WEATHER;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLaunch();
//        fetchForecastWeather();


    }

//    private void fetchForecastWeather(){
//        RetrofitBuilder.getService().getForecast("Bishkek", API_KEY, "metric")
//                .enqueue(new Callback<ForecastEntity>() {
//                    @Override
//                    public void onResponse(Call<ForecastEntity> call, Response<ForecastEntity> response) {
//                        if(response.isSuccessful()&& response.body()!= null){
//                            startF(response.body());
//                            finish();
//
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ForecastEntity> call, Throwable t) {
//                        Log.e("tag", "onFailure: ");
//                    }
//                });
//    }
    private void initLaunch(){
        if (PreferenceHelper.getIsNotFirstLaunch()) {
            MainActivity.start(this);

        } else {
            OnBoardActivity.start(this);
            PreferenceHelper.setIsFirstLaunch();


        }
        finish();
    }
//    public void startF(ForecastEntity forecastEntity){
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(WEATHER, forecastEntity);
//        startActivity(intent);
//    }
}
