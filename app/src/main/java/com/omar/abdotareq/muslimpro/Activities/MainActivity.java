package com.omar.abdotareq.muslimpro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.model.Doaa;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * An intro that has azkarButton and ahadeth_button
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();


    private RelativeLayout azkarButton;
    private RelativeLayout ahadeth_button;
    private int index;
    private ArrayList<Zekr> azkar = new ArrayList<>();
    private DataBaseHelper myDbHelper;
    private Cursor fourtyData, zekrTitleData, zekrData;
    private String azkarAsString;
    private String fourtiesAsString;
    private ArrayList<Hadeth> fourties = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Instance of DataBaseHelper class and initiallize it
        myDbHelper = new DataBaseHelper(this);

        createDataBaseTables();

        //get the tables from the database
        fourtyData = openDataBaseTable("forty");
        zekrTitleData = openDataBaseTable("azkarTitles");
//        zekrData = openDataBaseTable("allAzkar");

        initializeAzkarTitle();
//        initializeAzkarContents();
        initializeFourties();


        Type listOfFourties = new TypeToken<List<Hadeth>>() {
        }.getType();

        Type listOfAzkar = new TypeToken<List<Zekr>>() {
        }.getType();

        fourtiesAsString = new Gson().toJson(fourties, listOfFourties);
        azkarAsString = new Gson().toJson(azkar, listOfAzkar);

        azkarButton = findViewById(R.id.azkar);
        ahadeth_button = findViewById(R.id.ahadeth);

        azkarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
                intent.putExtra("index", index);
                intent.putExtra("AZKAR", azkarAsString);
                startActivity(intent);
            }
        });
        ahadeth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
                intent.putExtra("FOURTIES", fourtiesAsString);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });

    }

    //Method to copy the database file from the assets folder to the app database default location
    public void createDataBaseTables() {

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }
    }

    //Method to get a table from the database
    public Cursor openDataBaseTable(String tableName) {

        try {

            return myDbHelper.openDataBase(tableName);

        } catch (SQLException sqle) {

            throw sqle;

        }

    }

    //Method to initialize Azkar Titles from the database
    public void initializeAzkarTitle() {

        //Check if the table has content
        if (zekrTitleData.getCount() != 0) {

            //Check if the table contains more rows
            while (zekrTitleData.moveToNext()) {

                //Create new temp Zekr object
                Zekr zekr = new Zekr();

                //Initialize the Zekr object created
                zekr.setId(zekrTitleData.getInt(0));
                zekr.setTitleNoTa4kel(zekrTitleData.getString(1));
                zekr.setNumberOfDoaa(zekrTitleData.getInt(2));
                zekr.setTitle(zekrTitleData.getString(3));

                //add the Zekr object created to the Azkar List
                azkar.add(zekr);

            }

        }
    }

    //Method to initialize Azkar Contents from the database
    public void initializeAzkarContents() {

        //Check if the table has content
        if (zekrData.getCount() != 0) {

            ArrayList<Doaa> doaas = new ArrayList<>();
            int lastHeadID = 0;

            //Check if the table contains more rows
            while (zekrData.moveToNext()) {

                //Check if the ID of this Doaa is the same like the last one OR it's the first row
                if (zekrData.getInt(3) == lastHeadID || zekrData.isFirst()) {

                    //forcing lastHeadID variable to equal this Doaa ID
                    lastHeadID = zekrData.getInt(3);

                    //Create new temp Doaa object
                    Doaa doaa = new Doaa();

                    //Initialize the Doaa object created
                    doaa.setId(zekrData.getInt(0));
                    doaa.setText(zekrData.getString(1));
                    doaa.setTeller(zekrData.getString(2));
                    doaa.setHeadID(zekrData.getInt(3));
                    doaa.setNumber(zekrData.getInt(4));

                    //add the Doaa object created to the list containing 'Doaas' with the same ID
                    doaas.add(doaa);

                } else {//else if the Doaa ID is not equal to the last Doaa ID

                    //Set the azkar Doaas List to the Doaas ArrayList which contains all Doaas with headID same to this zekr ID
                    getZekrWithId(lastHeadID).setDoaas(doaas);

                    //clear the Doaas List so it start accepting the new zekr Doaas
                    doaas.clear();

                    //forcing lastHeadID variable to equal this Doaa ID
                    lastHeadID = zekrData.getInt(3);

                    //Create new temp Doaa object
                    Doaa doaa = new Doaa();

                    //Initialize the Doaa object created
                    doaa.setId(zekrData.getInt(0));
                    doaa.setText(zekrData.getString(1));
                    doaa.setTeller(zekrData.getString(2));
                    doaa.setHeadID(zekrData.getInt(3));
                    doaa.setNumber(zekrData.getInt(4));

                    //add the Doaa object created to the list containing 'Doaas' with the same ID
                    doaas.add(doaa);
                }

            }

            //Set the last zekr Doaas List with the last Doaas ArrayList
            getZekrWithId(lastHeadID).setDoaas(doaas);

        }

    }

    //Method to initialize Forties from the database
    public void initializeFourties() {

        //Check if the table has content
        if (fourtyData.getCount() != 0) {

            //Check if the table contains more rows
            while (fourtyData.moveToNext()) {

                //Create new temp Forty object
                Hadeth fourty = new Hadeth();

                //Initialize the Forty object created
                fourty.setId(fourtyData.getInt(0));
                fourty.setTitle(fourtyData.getString(1));
                fourty.setText(fourtyData.getString(2));
                fourty.setTeller(fourtyData.getString(3));
                fourty.setFavourite(fourtyData.getInt(4));

                //add the Forty object created to the Forties List
                fourties.add(fourty);
            }

        }

    }

    public Zekr getZekrWithId(int id){

        if(id == 132)
            Log.w("AHOO", "132");
        else if(id == 131)
            Log.w("AHOO", "131");

        for (int i = 0 ; i < azkar.size() ; i++){

            if(azkar.get(i).getId() == id)
                return azkar.get(i);

            continue;
        }

        return null ;

    }


}
