package com.salmat_team.offsetball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        manager= (SensorManager) getSystemService(SENSOR_SERVICE);
        fragm = getIntent().getExtras().getString("fragment",null);

<<<<<<< Updated upstream
        //loadFragment();
=======

>>>>>>> Stashed changes
        //TextView TV=(TextView)findViewById(R.id.textView);
        //TV.setText(fragm);



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
                ft.add(R.id.fragment_game1, new GameFragment());
                break;
            case "Options":
                ft.addToBackStack(OptionsFragment.class.getName());
                ft.add(R.id.fragment_options, new OptionsFragment());
                break;
        }

        ft.commit();
    }



    }
