package com.sopt25.sunnni.mobileprogramming_finalproject.feature.pedometer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sopt25.sunnni.mobileprogramming_finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedometerFragment extends Fragment {


    public PedometerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedometer, container, false);
    }

}
