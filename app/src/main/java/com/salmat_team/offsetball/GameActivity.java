package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by adamita on 2016. 03. 20..
 */
public class GameActivity extends AppCompatActivity {


    SensorManager manager;
    TextView timeTV;
    //String time="00:00:00";
    public long milli=0;
    long startTime=0L;
    int secund=0;
    int minute=0;


    Handler timeH = new Handler();
    Runnable timeR = new Runnable() {
        @Override
        public void run() {
            milli = System.currentTimeMillis()-startTime;
            secund = (int) (milli/1000);
            minute = secund/60;
            secund=secund%60;

            //timeTV.setText(String.format("%d:%02d", m, s));
            timeTV.setText("" + minute + ":"
                    + String.format("%02d", secund) + ":"
                    + String.format("%03d", (int)(milli%1000)));
            timeH.postDelayed(this,0);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        manager= (SensorManager) getSystemService(SENSOR_SERVICE);

        timeTV=(TextView)findViewById(R.id.TimeTV);
        startTime=System.currentTimeMillis();
        //timeH.post(timeR);
        //timeH.removeCallbacks(timeR);
        timeTV.post(timeR);
        //timeTV.setText(time);



        }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(
                listener,
                manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(listener);
    }

    public void BackTime()
    {
        getIntent().putExtra("time",milli);
        setResult(Activity.RESULT_OK,getIntent());
        finish();
    }

    private SensorEventListener listener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            MotionSensor.setValues(event.values);
        }

    };






    }
