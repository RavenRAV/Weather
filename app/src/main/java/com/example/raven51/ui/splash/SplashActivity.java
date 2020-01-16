package com.example.raven51.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.raven51.R;
import com.example.raven51.data.PreferenceHelper;
import com.example.raven51.ui.base.BaseActivity;
import com.example.raven51.ui.main.MainActivity;
import com.example.raven51.ui.onboard.OnBoardActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLaunch();


    }
    private void initLaunch(){
        if (PreferenceHelper.getIsNotFirstLaunch()) {
            MainActivity.start(this);

        } else {
            OnBoardActivity.start(this);
            PreferenceHelper.setIsFirstLaunch();


        }
        finish();
    }
}
