package com.dpoh89.bitday;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.transition.Fade;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitDayManual extends AppCompatActivity implements View.OnClickListener {

    ImageView background, reset,
            early_morning, morning, late_morning,
            early_afternoon, afternoon, late_afternoon,
            early_evening, evening, late_evening,
            early_night, night, late_night;

    TextView early_morning_text, morning_text, late_morning_text,
            early_afternoon_text, afternoon_text, late_afternoon_text,
            early_evening_text, evening_text, late_evening_text,
            early_night_text, night_text, late_night_text;

    SharedPreferences preferences;
    String tag;

    int xDim, yDim;

    AlertDialog.Builder builder;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setupWindowAnimations();
        setContentView(R.layout.activity_bit_day_manual);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

/*
        digital = Typeface.createFromAsset(getAssets(), "digital-7.ttf");
*/
        early_morning_text = (TextView) findViewById(R.id.early_morning_text);
        morning_text = (TextView) findViewById(R.id.morning_text);
        late_morning_text = (TextView) findViewById(R.id.late_morning_text);

        early_afternoon_text = (TextView) findViewById(R.id.early_afternoon_text);
        afternoon_text = (TextView) findViewById(R.id.afternoon_text);
        late_afternoon_text = (TextView) findViewById(R.id.late_afternoon_text);

        early_evening_text = (TextView) findViewById(R.id.early_evening_text);
        evening_text = (TextView) findViewById(R.id.evening_text);
        late_evening_text = (TextView) findViewById(R.id.late_evening_text);

        early_night_text = (TextView) findViewById(R.id.early_night_text);
        night_text = (TextView) findViewById(R.id.night_text);
        late_night_text = (TextView) findViewById(R.id.late_night_text);

        showTimes();

        background = (ImageView) findViewById(R.id.manual_background);

        early_morning = (ImageView) findViewById(R.id.early_morning_image);
        morning = (ImageView) findViewById(R.id.morning_image);
        late_morning = (ImageView) findViewById(R.id.late_morning_image);

        early_afternoon = (ImageView) findViewById(R.id.early_afternoon_image);
        afternoon = (ImageView) findViewById(R.id.afternoon_image);
        late_afternoon = (ImageView) findViewById(R.id.late_afternoon_image);

        early_evening = (ImageView) findViewById(R.id.early_evening_image);
        evening = (ImageView) findViewById(R.id.evening_image);
        late_evening = (ImageView) findViewById(R.id.late_evening_image);

        early_night = (ImageView) findViewById(R.id.early_night_image);
        night = (ImageView) findViewById(R.id.night_image);
        late_night = (ImageView) findViewById(R.id.late_night_image);

        early_morning.setOnClickListener(this);
        morning.setOnClickListener(this);
        late_morning.setOnClickListener(this);

        early_afternoon.setOnClickListener(this);
        afternoon.setOnClickListener(this);
        late_afternoon.setOnClickListener(this);

        early_evening.setOnClickListener(this);
        evening.setOnClickListener(this);
        late_evening.setOnClickListener(this);

        early_night.setOnClickListener(this);
        night.setOnClickListener(this);
        late_night.setOnClickListener(this);

        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.early_morning);
        //scaleToActualAspectRatio(bitmap);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        xDim = size.x;
        yDim = early_morning.getHeight();

        early_morning.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.early_morning_cropped), 500, xDim));
        morning.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.morning_cropped), 500, xDim));
        late_morning.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.late_morning_cropped), 500, xDim));

        early_afternoon.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.early_afternoon_cropped), 500, xDim));
        afternoon.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.afternoon_cropped), 500, xDim));
        late_afternoon.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.late_afternoon_cropped), 500, xDim));

        early_evening.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.early_evening_cropped), 500, xDim));
        evening.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.evening_cropped), 500, xDim));
        late_evening.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.late_evening_cropped), 500, xDim));

        early_night.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.early_night_cropped), 500, xDim));
        night.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.night_cropped), 500, xDim));
        late_night.setImageBitmap(getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.late_night_cropped), 500, xDim));


        tag = "BitDay";

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.early_morning_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("early_morning", 5)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("early_morning", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("early_morning", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("early_morning", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("early_morning", 5)));
                            early_morning_text.setText(Integer.toString(preferences.getInt("early_morning", 5)) + ":00");

                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                builder.show();
                break;

            case R.id.morning_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("morning", 6)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.edit().putInt("morning", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("morning", 25)) + ":00");
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("morning", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("morning", 6)));
                            morning_text.setText(Integer.toString(preferences.getInt("morning", 6)) + ":00");

                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.late_morning_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("late_morning", 8)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60 ,0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            preferences.edit().putInt("late_morning", 25).commit();
                            late_morning_text.setText(Integer.toString(preferences.getInt("late_morning", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("late_morning", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("late_morning", 8)));
                            late_morning_text.setText(Integer.toString(preferences.getInt("late_morning", 8)) + ":00");

                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.early_afternoon_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("early_afternoon", 10)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("early_afternoon", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("early_afternoon", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("early_afternoon", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("early_afternoon", 10)));
                            early_afternoon_text.setText(Integer.toString(preferences.getInt("early_afternoon", 10)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.afternoon_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("afternoon", 12)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("afternoon", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("afternoon", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("afternoon", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("afternoon", 12)));
                            afternoon_text.setText(Integer.toString(preferences.getInt("afternoon", 12)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.late_afternoon_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("late_afternoon", 14)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("late_afternoon", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("late_afternoon", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("late_afternoon", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("late_afternoon", 14)));
                            late_afternoon_text.setText(Integer.toString(preferences.getInt("late_afternoon", 14)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.early_evening_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("early_evening", 17)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("early_evening", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("early_evening", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("early_evening", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("early_evening", 17)));
                            early_evening_text.setText(Integer.toString(preferences.getInt("early_evening", 17)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.evening_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("evening", 19)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("evening", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("evening", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("evening", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("evening", 19)));
                            evening_text.setText(Integer.toString(preferences.getInt("evening", 19)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.late_evening_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("late_evening", 20)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("late_evening", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("late_evening", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("late_evening", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("late_evening", 20)));
                            late_evening_text.setText(Integer.toString(preferences.getInt("late_evening", 20)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.early_night_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("early_night", 22)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("early_night", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("early_night", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("early_night", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("early_night", 22)));
                            early_night_text.setText(Integer.toString(preferences.getInt("early_night", 22)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.night_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("night", 0)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("night", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("night", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("night", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("night", 0)));
                            night_text.setText(Integer.toString(preferences.getInt("night", 0)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;

            case R.id.late_night_image:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Set Time");
                input = new EditText(this);
                input.setText(Integer.toString(preferences.getInt("late_night", 2)));
                input.setSelection(input.getText().length());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input, 60, 0, 60, 0);
                builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putInt("late_night", 25).commit();
                        late_morning_text.setText(Integer.toString(preferences.getInt("late_night", 25)) + ":00");

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            preferences.edit().putInt("late_night", Integer.parseInt(input.getText().toString())).commit();
                            Log.i("Preferences", Integer.toString(preferences.getInt("late_night", 2)));
                            late_night_text.setText(Integer.toString(preferences.getInt("late_night", 2)) + ":00");
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter a valid time", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

/*    public Bitmap scaleToActualAspectRatio(Bitmap bitmap) {
        if (bitmap != null) {
            boolean flag = true;

            int deviceWidth = getWindowManager().getDefaultDisplay()
                    .getWidth();
            int deviceHeight = getWindowManager().getDefaultDisplay()
                    .getHeight();

            int bitmapHeight = bitmap.getHeight(); // 563
            int bitmapWidth = bitmap.getWidth(); // 900

// aSCPECT rATIO IS Always WIDTH x HEIGHT rEMEMMBER 1024 x 768

            if (bitmapWidth > deviceWidth) {
                flag = false;

// scale According to WIDTH
                int scaledWidth = deviceWidth;
                int scaledHeight = (scaledWidth * bitmapHeight) / bitmapWidth;

                try {
                    if (scaledHeight > deviceHeight)
                        scaledHeight = deviceHeight;

                    bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
                            scaledHeight, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (flag) {
                if (bitmapHeight > deviceHeight) {
                    // scale According to HEIGHT
                    int scaledHeight = deviceHeight;
                    int scaledWidth = (scaledHeight * bitmapWidth)
                            / bitmapHeight;

                    try {
                        if (scaledWidth > deviceWidth)
                            scaledWidth = deviceWidth;

                        bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
                                scaledHeight, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bitmap;
    }*/

    public static Bitmap getResizedBitmap(Bitmap image, int newHeight, int newWidth) {
        int width = image.getWidth();
        int height = image.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bit_day_manual, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_reset){
            reset();
        }

        return false;
    }

    public void reset() {
        preferences.edit().putInt("early_morning", 5).commit();
        preferences.edit().putInt("morning", 6).commit();
        preferences.edit().putInt("late_morning", 8).commit();

        preferences.edit().putInt("early_afternoon", 10).commit();
        preferences.edit().putInt("afternoon", 12).commit();
        preferences.edit().putInt("late_afternoon", 14).commit();

        preferences.edit().putInt("early_evening", 17).commit();
        preferences.edit().putInt("evening", 19).commit();
        preferences.edit().putInt("late_evening", 20).commit();

        preferences.edit().putInt("early_night", 22).commit();
        preferences.edit().putInt("night", 0).commit();
        preferences.edit().putInt("late_night", 2).commit();

        showTimes();

        Toast.makeText(getApplicationContext(), "All wallpaper times reset!", Toast.LENGTH_LONG).show();
    }

    public void showTimes() {
        early_morning_text.setText(Integer.toString(preferences.getInt("early_morning", 5)) + ":00");
        morning_text.setText(Integer.toString(preferences.getInt("morning", 6)) + ":00");
        late_morning_text.setText(Integer.toString(preferences.getInt("late_morning", 8)) + ":00");

        early_afternoon_text.setText(Integer.toString(preferences.getInt("early_afternoon", 10)) + ":00");
        afternoon_text.setText(Integer.toString(preferences.getInt("afternoon", 12)) + ":00");
        late_afternoon_text.setText(Integer.toString(preferences.getInt("late_afternoon", 14)) + ":00");

        early_evening_text.setText(Integer.toString(preferences.getInt("early_evening", 17)) + ":00");
        evening_text.setText(Integer.toString(preferences.getInt("evening", 19)) + ":00");
        late_evening_text.setText(Integer.toString(preferences.getInt("late_evening", 20)) + ":00");

        early_night_text.setText(Integer.toString(preferences.getInt("early_night", 22)) + ":00");
        night_text.setText(Integer.toString(preferences.getInt("night", 0)) + ":00");
        late_night_text.setText(Integer.toString(preferences.getInt("late_night", 2)) + ":00");
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }
}

