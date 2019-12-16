package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PedometerService extends Service {
    public PedometerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class PedometerThread extends Thread{

    }

    public class ServiceBinder extends Binder{
        PedometerService getService(){
            return PedometerService.this;
        }
    }
}
