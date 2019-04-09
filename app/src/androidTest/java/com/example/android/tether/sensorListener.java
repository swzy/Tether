package com.example.android.tether;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class sensorListener{
    SensorListener mShaker;
    boolean shakeOccurred = false;
    ArrayList<Float[]> list = new ArrayList<>();

    public void testShake() throws InterruptedException {
        mShaker.onSensorChanged(Sensor.TYPE_ACCELEROMETER, new float[] {0, 0, 0} );
        float[] float1 = new float[0,0,0];
        list.add(new Float[0,0,0]);
        //Required because method only allows one shake per 100ms
        Thread.sleep(500);
        mShaker.onSensorChanged(Sensor.TYPE_ACCELEROMETER, new float[] {300, 300, 300});
        assertTrue(String.valueOf(shakeOccurred), shakeOccurred);
    }

    public boolean onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accValues[0] = sensorEvent.values[0];
            accValues[1] = sensorEvent.values[1];
            accValues[2] = sensorEvent.values[2];
        }

    }

}
