package com.omar.abdotareq.meshkat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.omar.abdotareq.meshkat.R;
import com.omar.abdotareq.meshkat.data.DataBaseHelper;

import java.io.IOException;


/**
 * An intro that has azkarButton and ahadeth_button
 */
public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();


    private RelativeLayout azkarButton;
    private RelativeLayout ahadeth_button;
    private int index;

    private DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Instance of DataBaseHelper class and initiallize it
        myDbHelper = new DataBaseHelper(this);

        createDataBaseTables();

        azkarButton = findViewById(R.id.azkar);
        ahadeth_button = findViewById(R.id.ahadeth);

        azkarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });
        ahadeth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
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

}
