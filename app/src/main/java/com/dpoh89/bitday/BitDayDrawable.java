package com.dpoh89.bitday;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.Calendar;

public class BitDayDrawable extends Activity {
    private static SharedPreferences preferences;

    public static String EARLY_MORNING = "early_morning";
    public static String MORNING = "morning";
    public static String LATE_MORNING = "late_morning";
    public static String EARLY_AFTERNOON = "early_afternoon";
    public static String AFTERNOON = "afternoon";
    public static String LATE_AFTERNOON = "late_afternoon";
    public static String EARLY_EVENING = "early_evening";
    public static String EVENING = "evening";
    public static String LATE_EVENING = "late_evening";
    public static String EARLY_NIGHT = "early_night";
    public static String NIGHT = "night";
    public static String LATE_NIGHT = "late_night";

    private static int morningHour, lateMorningHour, afternoonHour, lateAfternoonHour,  eveningHour,
            lateEveningHour, nightHour, lateNightHour, earlyMorningHour, earlyAfternoonHour, earlyEveningHour, earlyNightHour;

    public static int get(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String tag = "BitDay";

        getPreferences();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int currentHour = c.get(Calendar.HOUR_OF_DAY);

        while ( currentHour != earlyMorningHour &&
                currentHour != morningHour &&
                currentHour != lateMorningHour &&
                currentHour != earlyAfternoonHour &&
                currentHour != afternoonHour &&
                currentHour != lateAfternoonHour &&
                currentHour != earlyEveningHour &&
                currentHour != eveningHour &&
                currentHour != lateEveningHour &&
                currentHour != earlyNightHour &&
                currentHour != nightHour &&
                currentHour != lateNightHour) {
            Log.i(tag, "currentHour = " + currentHour);
            if (currentHour == 0) {
                // it's 12am, set it to 11pm
                currentHour = 23;
            } else {
                currentHour--;
            }
        }

        if (currentHour == earlyMorningHour) {
            Log.i(tag, "Early Morning");
            return R.drawable.early_morning;
        } else if (currentHour == morningHour) {
            Log.i(tag, "Morning");
            return R.drawable.morning;
        } else if (currentHour == lateMorningHour) {
            Log.i(tag, "Late Morning");
            return R.drawable.late_morning;
        } else if (currentHour == earlyAfternoonHour) {
            Log.i(tag, "Early Afternoon");
            return R.drawable.early_afternoon;
        } else if (currentHour == afternoonHour) {
            Log.i(tag, "Afternoon");
            return R.drawable.afternoon;
        } else if (currentHour == lateAfternoonHour) {
            Log.i(tag, "Late Afternoon");
            return R.drawable.late_afternoon;
        } else if (currentHour == earlyEveningHour) {
            Log.i(tag, "Early Evening");
            return R.drawable.early_evening;
        } else if (currentHour == eveningHour) {
            Log.i(tag, "Evening");
            return R.drawable.evening;
        } else if (currentHour == lateEveningHour) {
            Log.i(tag, "Late Evening");
            return R.drawable.late_evening;
        } else if (currentHour == earlyNightHour) {
            Log.i(tag, "Early Night");
            return R.drawable.early_night;
        } else if (currentHour == nightHour) {
            Log.i(tag, "Night");
            return R.drawable.night;
        } else if (currentHour == lateNightHour) {
            Log.i(tag, "Late Night");
            return R.drawable.late_night;
        } else {
            Log.e(tag, "No Time Match");
            return R.drawable.splash;
        }

        //Cropped version
/*        if (currentHour == earlyMorningHour) {
            Log.i(tag, "Early Morning");
            return R.drawable.early_morning_cropped;
        } else if (currentHour == morningHour) {
            Log.i(tag, "Morning");
            return R.drawable.morning_cropped;
        } else if (currentHour == lateMorningHour) {
            Log.i(tag, "Late Morning");
            return R.drawable.late_morning_cropped;
        } else if (currentHour == earlyAfternoonHour) {
            Log.i(tag, "Early Afternoon");
            return R.drawable.early_afternoon_cropped;
        } else if (currentHour == afternoonHour) {
            Log.i(tag, "Afternoon");
            return R.drawable.afternoon_cropped;
        } else if (currentHour == lateAfternoonHour) {
            Log.i(tag, "Late Afternoon");
            return R.drawable.late_afternoon_cropped;
        } else if (currentHour == earlyEveningHour) {
            Log.i(tag, "Early Evening");
            return R.drawable.early_evening_cropped;
        } else if (currentHour == eveningHour) {
            Log.i(tag, "Evening");
            return R.drawable.evening_cropped;
        } else if (currentHour == lateEveningHour) {
            Log.i(tag, "Late Evening");
            return R.drawable.late_evening_cropped;
        } else if (currentHour == earlyNightHour) {
            Log.i(tag, "Early Night");
            return R.drawable.early_night_cropped;
        } else if (currentHour == nightHour) {
            Log.i(tag, "Night");
            return R.drawable.night_cropped;
        } else if (currentHour == lateNightHour) {
            Log.i(tag, "Late Night");
            return R.drawable.late_night_cropped;
        } else {
            Log.e(tag, "No Time Match");
            return R.drawable.splash_cropped;
        }*/

    }

    public static void getPreferences() {
        earlyMorningHour = preferences.getInt(EARLY_MORNING, 5);
        morningHour = preferences.getInt(MORNING, 6);
        lateMorningHour = preferences.getInt(LATE_MORNING, 8);
        earlyAfternoonHour = preferences.getInt(EARLY_AFTERNOON, 10);
        afternoonHour = preferences.getInt(AFTERNOON, 12);
        lateAfternoonHour = preferences.getInt(LATE_AFTERNOON, 14);
        earlyEveningHour = preferences.getInt(EARLY_EVENING, 17);
        eveningHour = preferences.getInt(EVENING, 19);
        lateEveningHour = preferences.getInt(LATE_EVENING, 20);
        earlyNightHour = preferences.getInt(EARLY_NIGHT, 22);
        nightHour = preferences.getInt(NIGHT, 0);
        lateNightHour = preferences.getInt(LATE_NIGHT, 2);
    }



}