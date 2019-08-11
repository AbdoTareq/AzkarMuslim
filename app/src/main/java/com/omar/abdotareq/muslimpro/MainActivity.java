package com.omar.abdotareq.muslimpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


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
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PagerActivity.class);
                startActivity(intent);
                index = 0;
            }
        });
        ahadeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PagerActivity.class);
                startActivity(intent);
                index = 1;
            }
        });

    }


}
