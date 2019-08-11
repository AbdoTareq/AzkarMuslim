package com.omar.abdotareq.muslimpro.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.omar.abdotareq.muslimpro.R;

/**
 * this UI class controls Ahadeth .
 */
public class AhadethFragment extends Fragment {

    TextView textView;

    public AhadethFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View Ahadeth_fragment = inflater.inflate(R.layout.fragment_azkar_hadeth, container, false);

        textView = Ahadeth_fragment.findViewById(R.id.text_fragment);
        textView.setText("Ahadeth");


        return Ahadeth_fragment;
    }

}
