package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omar.abdotareq.muslimpro.activities.ZekrActivity;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * this UI class controls Azkar .
 */
public class AzkarListFragment extends Fragment {

    public static final String LOG_TAG = AzkarListFragment.class.getSimpleName();


    private ListView azkarListView;
    private ArrayList<String> temp;
    private ArrayList<Zekr> azkar = new ArrayList<>();

    public AzkarListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        azkarListView = view.findViewById(R.id.listView);


        // Receive data(Azkar) from PagerListActivity
        String azkarAsString = "empty";
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            azkarAsString = bundle.getString("AZKAR");
        }

        // Change string to ArrayList
        Type listOfAzkar = new TypeToken<List<Zekr>>() {
        }.getType();
        azkar = new Gson().fromJson(azkarAsString, listOfAzkar);

        temp = new ArrayList<String>();
        for (Zekr zekr : azkar
        ) {
            temp.add(zekr.getTitle());
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> azkarAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                temp);

        azkarListView.setAdapter(azkarAdapter);

        azkarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "position: " + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ZekrActivity.class);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });

        return view;

    }

}
