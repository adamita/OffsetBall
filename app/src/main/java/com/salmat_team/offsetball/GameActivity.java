package com.salmat_team.offsetball;

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

    String fragm="";
    SensorManager manager;
    TextView timeTV;
    //String time="00:00:00";
    long milli=0;
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
        fragm = getIntent().getExtras().getString("fragment",null);
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

    private SensorEventListener listener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            MotionSensor.setValues(event.values);
        }

    };


    private void loadFragment(){
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        switch (fragm) {
            case "Game":
                ft.addToBackStack(GameFragment.class.getName());
                ft.add(R.id.game_fragment, new GameFragment());
                ft.addToBackStack(GameFragment2.class.getName());
                ft.add(R.id.game_fragment, new GameFragment2());
                break;
            case "Options":
                ft.addToBackStack(OptionsFragment.class.getName());
                ft.add(R.id.fragment_options, new OptionsFragment());
                break;
        }

        ft.commit();
    }



    }
