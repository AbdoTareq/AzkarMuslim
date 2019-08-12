package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.omar.abdotareq.muslimpro.activities.ZekrActivity;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.R;

import java.util.ArrayList;

/**
 * this UI class controls Azkar .
 */
public class AzkarListFragment extends Fragment {

    private ListView azkarListView;
    private ArrayList<Hadeth> azkarArrayList;
    private ArrayList<String> temp;

    public AzkarListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        azkarListView = view.findViewById(R.id.listView);

        azkarArrayList = new ArrayList<Hadeth>();
        temp = new ArrayList<String >();
        azkarArrayList.add(new Hadeth("1st Zekr"));
        azkarArrayList.add(new Hadeth("2nd"));
        azkarArrayList.add(new Hadeth("3rd"));
        azkarArrayList.add(new Hadeth("4th"));

        for (Hadeth hadeth : azkarArrayList
        ) {
            temp.add(hadeth.getTitle());
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String > azkarAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                temp);

        azkarListView.setAdapter(azkarAdapter);

        azkarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "position: "+i, Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(), ZekrActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

}
