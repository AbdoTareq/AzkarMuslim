package com.omar.abdotareq.muslimpro.activities.api_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.adapters.api_adapters.QuranAudioAdapter;
import com.omar.abdotareq.muslimpro.model.api_models.ListOfQuranAudios;
import com.omar.abdotareq.muslimpro.model.api_models.QuranAudio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChooseQuranAudioActivity extends AppCompatActivity {

    //create UI elements
    private ListView quranAudioListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quran_audio);

        //Initialize UI elements
        quranAudioListView = findViewById(R.id.choose_quran_audio_list_view);

        //get all the quran audios
        getAllQuranAudios();

    }

    private void getAllQuranAudios() {

        //Create a new Retrofit Builder and pass the api base url to it
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create());

        //Build the retrofit builder
        Retrofit retrofit = builder.build();

        //create a quran controller object
        QuranController quranInterface = retrofit.create(QuranController.class);

        //initialize the call
        Call<ListOfQuranAudios> quranAudioCall = quranInterface.getAllAudio();

        //start the call
        quranAudioCall.enqueue(new Callback<ListOfQuranAudios>() {
            @Override
            public void onResponse(Call<ListOfQuranAudios> call, Response<ListOfQuranAudios> response) {

                if (response.body().getData() != null && response.code() == 200) {
                    //if response is OK

                    //get the received list of audios
                    final ListOfQuranAudios listOfAudios = response.body();

                    //initialize the adapter
                    QuranAudioAdapter quranAudioAdapter = new QuranAudioAdapter(ChooseQuranAudioActivity.this,
                            R.layout.two_text_views_row, listOfAudios.getData());

                    //set the adapter with the list view
                    quranAudioListView.setAdapter(quranAudioAdapter);

                    //add listener to the audio list view
                    quranAudioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            //get the identifier of the clicked item
                            String identifier = listOfAudios.getData().get(i).getIdentifier();

                            //initialize a new intent to start the QuranAudioActivity
                            Intent quranIntent = new Intent(ChooseQuranAudioActivity.this, QuranAudioActivity.class);

                            //pass the identifier with the intent
                            quranIntent.putExtra("IDENTIFIER", identifier);

                            //start the audio activity
                            startActivity(quranIntent);

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<ListOfQuranAudios> call, Throwable t) {

            }
        });

    }
}
