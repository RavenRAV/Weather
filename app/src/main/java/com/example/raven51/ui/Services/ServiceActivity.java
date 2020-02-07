package com.example.raven51.ui.Services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.raven51.R;
import com.example.raven51.data.NotificationHelper;
import com.example.raven51.ui.base.BaseActivity;
import com.example.raven51.ui.base.BaseMapActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import butterknife.BindView;

import static com.example.raven51.data.NotificationHelper.NOTIFICATION_CHANNEL;


public class ServiceActivity extends BaseMapActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationManager locationManager;
    LocationListener locationListener;
    String[] permissios = new String[2];
    private final int REQ_COD = 1001;

    public static final String SERVICE_ACTIVE = "isA";
    @BindView(R.id.serviceButton)
    Button button;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_service_actyvity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initListners();



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        locationManager = getSystemService(LOCATION_SERVICE);



        permissios[0]=(Manifest.permission.ACCESS_FINE_LOCATION);
        permissios[1]=(Manifest.permission.ACCESS_COARSE_LOCATION);
        if(ContextCompat.checkSelfPermission(this, permissios[0])
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, permissios[1])
                == PackageManager.PERMISSION_GRANTED ){
//            locUpdates();
            NotificationHelper.locUpdates(this);

//            NotificationHelper.getL(this);
//            getLastLocation();
        }else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(permissios, REQ_COD);


            }
        }

    }

//    public void locUpdates(){
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PermissionChecker.PERMISSION_GRANTED &&
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        == PermissionChecker.PERMISSION_GRANTED){
//            fusedLocationProviderClient = new FusedLocationProviderClient(this);
//            locationRequest = new LocationRequest();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(5000).setFastestInterval(1000);
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback(){
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                super.onLocationResult(locationResult);
//                Double lat = locationResult.getLastLocation().getLatitude();
//                Double lon = locationResult.getLastLocation().getLongitude();
//
//
//            }
//        }, getMainLooper());}
//    }






//    public void getLastLocation() {
//        fusedLocationProviderClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if( location != null){
////                            NotificationHelper.getNotification().
//
//
//                        }
//                    }
//                });
//    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_COD){
            for (int results:grantResults) {
                if(results == PackageManager.PERMISSION_GRANTED){
//                    getLastLocation();
//                    NotificationHelper.getL(this);
                    NotificationHelper.locUpdates(this);
                }

            }
        }

    }

//    public Notification getNotification(Context context) {
//        NotificationHelper.createNotificationChannel(context);
//        return new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("My notification")
//                .setAutoCancel(false)
//                .setContentIntent(NotificationHelper.createPendingIntent(context))
//                .setContentText("Much longer text that cannot fit one line...")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(text()))
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build();
//
//    }
//    public String text(){
//        return lat+ " "+ lon;
//    }




    private void startService(boolean isActivated) {
        Intent intent = new Intent(this, MyForegroundService.class);
        intent.putExtra(SERVICE_ACTIVE, isActivated);
        startService(intent);
    }






    private void initListners(){
//        button.setOnClickListener(v -> button.setActivated(!button.isActivated()));

        button.setOnClickListener(v -> {
            if(!button.isActivated()){
                button.setActivated(true);
                startService(true);
            }else{
                button.setActivated(false);
                startService(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
