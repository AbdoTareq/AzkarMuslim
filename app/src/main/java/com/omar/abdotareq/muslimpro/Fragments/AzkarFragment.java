package com.omar.abdotareq.muslimpro.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omar.abdotareq.muslimpro.R;

/**
 * this UI class controls Azkar .
 */
public class AzkarFragment extends Fragment {


    private TextView textView;

    public AzkarFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View Azkar_fragment = inflater.inflate(R.layout.fragment_azkar_hadeth, container, false);

        textView = Azkar_fragment.findViewById(R.id.text_fragment);
        textView.setText("Azkar");



        return Azkar_fragment;


    }

}
