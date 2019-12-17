package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class PedometerService extends Service implements SensorEventListener {

    ServiceBinder mServiceBinder = new ServiceBinder();

    private int mStepDetector;
    private StepCallback callback;

    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private Sensor stepCountSensor;

    // constructor
    public PedometerService() {
    }


    public void setCallback(StepCallback callback) {
        this.callback = callback;
    }


    // Service 생성 시 SensorManager 및 Sensor 등록
    @Override
    public void onCreate() {
        super.onCreate();

        // SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Detector
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (stepDetectorSensor == null) {
        } else {
            sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Counter
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCountSensor == null) {
        } else {
            sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // bind
    @Override
    public IBinder onBind(Intent intent) {
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_GAME);
        return mServiceBinder;
        // main activity에서 binder 받으면 thread.start();
    }


    // unbind
    @Override
    public boolean onUnbind(Intent intent) {
        unRegistManager();
        if (callback != null)
            callback.onUnbindService(mStepDetector);
        return super.onUnbind(intent);
    }


    public void unRegistManager() {
        try {
            sensorManager.unregisterListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (sensorEvent.values[0] == 1.0f) {
                mStepDetector += sensorEvent.values[0];
                if (callback != null)
                    callback.onStepCallback(mStepDetector);
                Log.e("스텝 디텍터", "" + sensorEvent.values[0]);
            }
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Log.e("스텝 카운트", "" + sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sensorManager != null){
            sensorManager.unregisterListener(this);
            mStepDetector = 0;
        }
    }

    private class PedometerThread extends Thread {

    }


    // 바인드된 서비스를 사용하기 위한 Binder Class
    public class ServiceBinder extends Binder {
        PedometerService getService() {
            return PedometerService.this;
        }
    }
}


// unregisterderror
// onstartcommand
