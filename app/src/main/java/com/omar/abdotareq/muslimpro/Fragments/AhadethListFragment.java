package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.database.Cursor;
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
import com.omar.abdotareq.muslimpro.adapters.AhadethListAdapter;
import com.omar.abdotareq.muslimpro.adapters.AzkarListAdapter;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
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
    private List<Hadeth> ahadeth = new ArrayList<>();


    public AhadethListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final String LOG_TAG = AhadethListFragment.class.getSimpleName();

        //get the view
        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        //initialize the hadeth list view
        ahadethListView = view.findViewById(R.id.listView);

        //initialize all the ahadeth from the databse
        initializeAhadeth();

        //initialize the adapter
        AhadethListAdapter ahadethAdapter = new AhadethListAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                ahadeth);

        //set up the adapter with the list view
        ahadethListView.setAdapter(ahadethAdapter);

        //listen to list item clicks
        ahadethListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if user clicks any item on the list

                //create a new intent to the Hadeth Activity
                Intent zekrIntetnt = new Intent(getActivity(), HadethActivity.class);

                //pass the clicked hadeth id as extras with the intent
                zekrIntetnt.putExtra("HADETH_ID", ahadeth.get(i).getId());

                //start the activity
                startActivity(zekrIntetnt);
            }
        });

        //return the view
        return view;
    }

    /**
     * A method called to get all the ahadeth from the database and initialize them
     */
    private void initializeAhadeth() {

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(getContext());

        //get the cursor from the database pointing to all the ahadeth
        Cursor ahadethCursor = myDbHelper.openDataBase("forty");

        //loop on all the ahadeth
        while (ahadethCursor.moveToNext()) {

            //get the data
            int id = ahadethCursor.getInt(0);
            String title = ahadethCursor.getString(1);
            String text = ahadethCursor.getString(2);
            String teller = ahadethCursor.getString(3);
            int favourite = ahadethCursor.getInt(4);

            //add the hadeth to the ahadeth list view
            ahadeth.add(new Hadeth(id, title, text, teller, favourite));

        }

    }

}
