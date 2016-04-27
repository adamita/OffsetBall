package com.salmat_team.offsetball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class MotionSensor {
    private static float x;
    private static float y;
    private static float z;

    public static void setValues(float[] values)
    {
        x=values[0];
        y=values[1];
        z=values[2];
    }

    public static float getX() {
        return x;
    }


    public static float getY() {
        return y;
    }


    public static float getZ() {
        return z;
    }

}
