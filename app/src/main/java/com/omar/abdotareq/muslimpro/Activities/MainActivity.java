package com.omar.abdotareq.muslimpro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.omar.abdotareq.muslimpro.R;


/**
 * An intro that has azkar and ahadeth
 */
public class MainActivity extends AppCompatActivity {

    private RelativeLayout azkar;
    private RelativeLayout ahadeth;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        azkar = findViewById(R.id.azkar);
        ahadeth = findViewById(R.id.ahadeth);

        azkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });
        ahadeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                Intent intent = new Intent(MainActivity.this, PagerListActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        });

    }


}
