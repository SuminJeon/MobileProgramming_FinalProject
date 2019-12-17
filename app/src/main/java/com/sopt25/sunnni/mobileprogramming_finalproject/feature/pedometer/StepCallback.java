package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;

public interface StepCallback {
    void onStepCallback(int stepCount);
    void onUnbindService(int stepCount);
}
