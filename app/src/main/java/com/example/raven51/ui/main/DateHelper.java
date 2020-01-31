package com.example.raven51.ui.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    private static final String HOURS_FORMAT = "HH:mm";
    private static final String DATE_TIME_FORMAT = "HH:mm  EE dd/MM";

    public static String convUNIX(int dt) {
        return format(dt,HOURS_FORMAT);
    }

    public static String convUNIXDay(int dt) {
        return format(dt,DATE_TIME_FORMAT);
    }

    private static String format(int dt, String format) {
        Date date = new Date(dt * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
