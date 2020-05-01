package com.omar.abdotareq.meshkat.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.omar.abdotareq.meshkat.R;
import com.omar.abdotareq.meshkat.data.DataBaseHelper;
import com.omar.abdotareq.meshkat.utils.SharedPreference;

import java.io.IOException;

import static com.omar.abdotareq.meshkat.utils.SharedPreference.getSharedPrefsNightMode;


/**
 * An intro that has azkarButton and ahadeth_button
 */
public class MainActivity extends AppCompatActivity {

    private RelativeLayout azkarButton;
    private RelativeLayout ahadeth_button;
    private Switch aSwitch;
    private int index;

    private DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this for settings dark mode
        if (getSharedPrefsNightMode(this))
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Initialize Instance of DataBaseHelper class and initiallize it
        myDbHelper = new DataBaseHelper(this);

        createDataBaseTables();

        azkarButton = findViewById(R.id.azkar);
        ahadeth_button = findViewById(R.id.ahadeth);
        aSwitch = findViewById(R.id.switchButton);

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

        aSwitch.setChecked(SharedPreference.getSharedPrefsNightMode(getApplicationContext()));

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    // The toggle is enabled
                    SharedPreference.setSharedPrefsNightMode(getApplicationContext(), true);
                } else {
                    // The toggle is disabled
                    SharedPreference.setSharedPrefsNightMode(getApplicationContext(), false);
                }
                restartApp();
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

    public void restartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
