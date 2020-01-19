package com.example.raven51.ui.main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String convUNIX(int dt){
        Date date = new Date(dt * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH: mm");
        String formatt = simpleDateFormat.format(date);
        return formatt;
    }
    public static String convUNIXDay(int dt){
        Date date = new Date(dt * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm  EE dd MM yyyy");
        String formatt = simpleDateFormat.format(date);
        return formatt;
    }
}
