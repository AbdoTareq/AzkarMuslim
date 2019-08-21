package com.omar.abdotareq.muslimpro.activities.api_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.omar.abdotareq.muslimpro.R;

public class QuranMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_main);

        RelativeLayout audioButton = findViewById(R.id.quran_main_Activity_audio_button);
        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent audioIntent = new Intent(QuranMainActivity.this, ChooseQuranAudioActivity.class);
                startActivity(audioIntent);
            }
        });
    }
}
