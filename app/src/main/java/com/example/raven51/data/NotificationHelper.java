package com.example.raven51.data;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.raven51.R;
import com.example.raven51.ui.Services.ServiceActivity;
import com.example.raven51.ui.base.BaseMapActivity;
import com.example.raven51.ui.main.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class NotificationHelper {
    public static final String NOTIFICATION_CHANNEL = "channel_notification";
    static String lat;
    static String lon;

    public static FusedLocationProviderClient fusedLocationProviderClient(Context context) {
        return LocationServices.getFusedLocationProviderClient(context);
    }


    public static void getL(Context context){
        fusedLocationProviderClient(context).getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null){
                        lat = (String.valueOf(location.getLatitude()));
                        lon = (String.valueOf(location.getLongitude()));
                    }
                });
    }

    public static String text(Context context){

        return context.getString(R.string.lat) + " " + lat + " " +context.getString(R.string.lon) + " " + lon;
    }







    public static Notification getNotification(Context context) {
        createNotificationChannel(context);
        return new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.earth2)
                .setContentTitle(context.getString(R.string.coord))
                .setAutoCancel(false)
                .setContentIntent(createPendingIntent(context))
                .setContentText(" ")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text(context)))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

    }
//    public void createNotification(Context context){
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//        notificationManager.notify(1, getNotification(context));
//    }

    public static PendingIntent createPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0, intent, 0);

    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test title";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }





}
