package com.dpoh89.bitday;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener {
    private SharedPreferences.Editor editor;
    ImageView background;
    Button manualButton, activateButton;
    Switch switchToggle;
    LinearLayout activate_layout;
    SharedPreferences preferences;
    ObjectAnimator colorFadeIn, colorFadeOut;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        activate_layout = (LinearLayout) findViewById(R.id.activate_button_layout);

        manualButton = (Button) findViewById(R.id.manualButton);
        manualButton.setOnClickListener(this);

        activateButton = (Button) findViewById(R.id.activate_button);
        activateButton.setOnClickListener(this);

        background = (ImageView) findViewById(R.id.background);
        background.setImageResource(BitDayDrawable.get(this));

        colorFadeOut = ObjectAnimator.ofObject(activate_layout, "backgroundColor", new ArgbEvaluator(), getResources().getColor(R.color.blue), getResources().getColor(R.color.lightRed_Pressed));
        colorFadeOut.setDuration(500);

        colorFadeIn = ObjectAnimator.ofObject(activate_layout, "backgroundColor", new ArgbEvaluator(), getResources().getColor(R.color.lightRed_Pressed), getResources().getColor(R.color.blue));
        colorFadeIn.setDuration(500);

        if (preferences.getBoolean("isRunning", false)) {
            activateButton.setText("Activated");
            activate_layout.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            activateButton.setText("Disabled");
            activate_layout.setBackgroundColor(getResources().getColor(R.color.lightRed_Pressed));
        }

        //background.startAnimation(animation);

        TextView textView = (TextView) findViewById(R.id.textView);
        String text = "BitDay Beta";
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            text += " v" + pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("BitDay", "Unable to get version name");
        }
        textView.setText(text);

        ImageButton info = (ImageButton) findViewById(R.id.bitday_info);
        info.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bitday_info:

                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);

                builder.setTitle("BitDay Info");
                //final TextView infoText = new TextView(this);
                try {
                    PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    builder.setMessage("BitDay v" + pInfo.versionName + " modified by Darren Poh. \n" + getResources().getString(R.string.text_footer));
                    builder.setNegativeButton("Close", null);

                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("BitDay", "Unable to get version name");
                }
                builder.show();
                break;

            case R.id.manualButton:

                Intent i = new Intent(this, BitDayManual.class);
                startActivity(i);
                finish();

                break;

            case R.id.activate_button:
                if (!preferences.getBoolean("isRunning", false)) {
                    editor.putBoolean("isRunning", true);
                    startAlarms();
                    colorFadeIn.start();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something
                            activateButton.setText("Activated");
                        }
                    }, 400);
                } else {
                    editor.putBoolean("isRunning", false);
                    stopAlarms();
                    colorFadeOut.start();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something
                            activateButton.setText("Disabled");
                        }
                    }, 400);
                }
                editor.commit();

                break;
        }
    }


    private void startAlarms() {
        // 3600000 hour in millis

        Intent receiverIntent = new Intent(this, BitDayReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 5315, receiverIntent, 0);

        Calendar c = Calendar.getInstance();

        c.add(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        String tag = "BitDay";
        Log.i(tag, c.getTime().toString());

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, c.getTimeInMillis(), 1000 * 60 * 60, sender);
        Log.i(tag, "Hourly alarm started");

        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setResource(BitDayDrawable.get(this));
        } catch (IOException e) {
            Log.e("BitDay", "Error setting wallpaper");
        }
    }

    private void stopAlarms() {
        Intent receiverIntent = new Intent(this, BitDayReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 5315, receiverIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    @Override
    public void onResume() {
        super.onResume();
        //background.setImageResource(BitDayDrawable.get(this));
    }


}