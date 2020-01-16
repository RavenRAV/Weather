package com.example.raven51;

import android.app.Application;

import com.example.raven51.data.PreferenceHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new PreferenceHelper(this);
    }
}
