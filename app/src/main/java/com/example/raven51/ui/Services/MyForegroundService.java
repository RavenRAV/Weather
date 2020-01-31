package com.example.raven51.ui.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.raven51.R;
import com.example.raven51.data.NotificationHelper;

import static com.example.raven51.ui.Services.ServiceActivity.SERVICE_ACTIVE;

public class MyForegroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getBooleanExtra(SERVICE_ACTIVE, false)){
        startForeground(1, NotificationHelper.getNotification(getApplicationContext()));
        }
        else {
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.service_stopped, Toast.LENGTH_LONG).show();
    }
}
