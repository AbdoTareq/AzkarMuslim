package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.database.Cursor;
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
import com.omar.abdotareq.muslimpro.adapters.AzkarListAdapter;
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
    private ArrayList<Zekr> azkar = new ArrayList<>();

    public AzkarListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //get the view
        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        //initialize the list view
        azkarListView = view.findViewById(R.id.listView);

        //Initialize the azkar from the database
        initializeAzkar();

        // initialize the adapter
        AzkarListAdapter azkarAdapter = new AzkarListAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                azkar);

        //set up the adapter with the list view
        azkarListView.setAdapter(azkarAdapter);

        //listen to list item clicks
        azkarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if user clicks any item on the list

                //create a new intent to the Zekr Activity
                Intent zekrIntetnt = new Intent(getActivity(), ZekrActivity.class);

                //pass the clicked zekr id as extras with the intent
                zekrIntetnt.putExtra("ZEKR_ID", azkar.get(i).getId());

                //start the activity
                startActivity(zekrIntetnt);

            }
        });

        //return the view
        return view;

    }

    /**
     * A method called to get all the azkar from the database
     */
    private void initializeAzkar() {

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(getContext());

        //get the cursor from the database pointing to all the azkar titles
        Cursor azkarCursor = myDbHelper.openDataBase("azkarTitles");

        //loop on all the azkar
        while (azkarCursor.moveToNext()) {

            //get the data
            int id = azkarCursor.getInt(0);
            String titleNoTa4kel = azkarCursor.getString(1);
            int doaaCount = azkarCursor.getInt(2);
            String title = azkarCursor.getString(3);

            //add the zekr to the azkar list view
            azkar.add(new Zekr(id, title, titleNoTa4kel, 0, doaaCount));

        }

    }

}
