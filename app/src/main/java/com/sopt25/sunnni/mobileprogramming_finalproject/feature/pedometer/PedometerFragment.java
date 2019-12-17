package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedometerFragment extends Fragment implements View.OnClickListener{

    Context context;

    private Button mBtnStart, mBtnStop;
    private TextView mTvStep;

    private Intent intent;

    private PedometerService mPedoService;
    boolean isService = false;

    // 서비스의 이벤트 결과 전달
    private StepCallback callback = new StepCallback() {
        @Override
        public void onStepCallback(int stepCount) {
            mTvStep.setText("" + stepCount);
        }

        @Override
        public void onUnbindService() {
            isService = false;
        }
    };

    // constructor
    public PedometerFragment(Context ctx) {
        this.context = ctx;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PedometerService.ServiceBinder mBinder = (PedometerService.ServiceBinder) iBinder;
            mPedoService = mBinder.getService();
            mPedoService.setCallback(callback);
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isService = false;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedometer, container, false);

        mPedoService = new PedometerService();

        mBtnStart = view.findViewById(R.id.btn_start);
        mBtnStop = view.findViewById(R.id.btn_stop);
        mTvStep = view.findViewById(R.id.tv_stepCount);

        // onClickListener 등록
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_start:

                intent = new Intent(context, PedometerService.class);
                context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

                mBtnStart.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn_inactive));
                mBtnStart.setEnabled(false);

                mBtnStop.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn));
                mBtnStop.setEnabled(true);
                break;

            case R.id.btn_stop:

                mBtnStart.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn));
                mBtnStart.setEnabled(true);

                mBtnStop.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_btn_inactive));
                mBtnStart.setEnabled(false);

                try{
                    context.unbindService(serviceConnection);
                    context.stopService(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // context.unbindService(serviceConnection);
    }
}
