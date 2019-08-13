package com.omar.abdotareq.muslimpro.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omar.abdotareq.muslimpro.R;

/**
 * An UI class to view zekr to count zekr count like tasbeh
 */
public class ZekrFragment extends Fragment {


    public ZekrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zekr, container, false);


        return view;
    }

}
