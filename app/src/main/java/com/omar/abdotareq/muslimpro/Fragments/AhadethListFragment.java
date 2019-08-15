package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omar.abdotareq.muslimpro.activities.HadethActivity;
import com.omar.abdotareq.muslimpro.activities.PagerListActivity;
import com.omar.abdotareq.muslimpro.activities.ZekrActivity;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.omar.abdotareq.muslimpro.activities.PagerListActivity.LOG_TAG;

/**
 * this UI class controls Ahadeth .
 */
public class AhadethListFragment extends Fragment {

    private ListView ahadethListView;
    private ArrayList<Hadeth> ahadethArrayList;
    private ArrayList<String> temp;

    private int azkarOrAhadeth=1;

    public AhadethListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         final String LOG_TAG = AhadethListFragment.class.getSimpleName();

        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);


        ahadethListView = view.findViewById(R.id.listView);

        ahadethArrayList = new ArrayList<Hadeth>();

        // Receive data(Azkar) from PagerListActivity
        String ahadethAsString = "empty";
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            ahadethAsString = bundle.getString("FOURTIES");
        }

        // Change string to ArrayList
        Type listOfAhadeth = new TypeToken<List<Hadeth>>() {
        }.getType();
        Log.d(LOG_TAG, "this FOURTIES gson" + ahadethAsString);

        ahadethArrayList = new Gson().fromJson(ahadethAsString, listOfAhadeth);

//        temp = new ArrayList<String>();
//        for (Hadeth hadeth : ahadethArrayList
//        ) {
//            temp.add(hadeth.getTitle());
//        }
//
//        // This is the array adapter, it takes the context of the activity as a
//        // first parameter, the type of list view as a second parameter and your
//        // array as a third parameter.
//        ArrayAdapter<String> HadethAdapter = new ArrayAdapter<>(
//                getContext(),
//                android.R.layout.simple_list_item_1,
//                temp);
//
//        ahadethListView.setAdapter(HadethAdapter);

        ahadethListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "position: " + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HadethActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
