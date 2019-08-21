package com.omar.abdotareq.muslimpro.activities.api_activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.adapters.api_adapters.SurahAdapter;
import com.omar.abdotareq.muslimpro.model.Zekr;
import com.omar.abdotareq.muslimpro.model.api_models.SimpleQuran;
import com.omar.abdotareq.muslimpro.model.api_models.SimpleSurah;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuranAudioActivity extends AppCompatActivity {

    //create Ui elements
    private ListView surahListView;
    private TextView currentSurahTextView;
    private ImageView playButton;

    SimpleQuran quran;

    private String identifier = "";

    //important variables to manage audio play
    private int currentAyaNumber = 0;
    private SimpleSurah surah;
    private boolean isPlaying = false;
    private Call<ResponseBody> audioCall;

    List<SimpleSurah> searchedSurahs;

    SurahAdapter surahAdapter;

    /**
     * A Queue of bytes array, as the response is received as array of bytes for each aya
     * then store the array of bytes for each aya in a queue one by one in order to be easily played one after the other
     */
    private Queue<byte[]> bytesQueue = new LinkedList<>();

    //the media player that will be playing the ayahs
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_audio);

        //initialize Ui elements
        surahListView = findViewById(R.id.quran_audio_list_view);
        currentSurahTextView = findViewById(R.id.quran_audio_current_surah);
        playButton = findViewById(R.id.quran_audio_play_button);

        //get the quran from the stored json file at the assets and convert it to Quran Object
        quran = new Gson().fromJson(getQuranAsString(QuranAudioActivity.this), SimpleQuran.class);

        //initialize the searched surahs list used for search
        searchedSurahs = new ArrayList<>(quran.getSimpleSurahs());

        //initialize the adapter
        surahAdapter = new SurahAdapter(QuranAudioActivity.this, android.R.layout.simple_list_item_1
                , searchedSurahs);

        //set the adapter with the list view
        surahListView.setAdapter(surahAdapter);

        //get the identifier passed from the previous activity
        identifier = getIntent().getStringExtra("IDENTIFIER");

        surahListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //if media player is currently playing stop and reset it
                mediaPlayer.stop();
                mediaPlayer.reset();

                //set the isPlaying flag to false
                isPlaying = false;

                //set the play button as pause icon
                playButton.setImageResource(R.drawable.pause_white_icon);

                //if the bytes queue contains any byte arrays -> clear it
                bytesQueue.clear();

                //get the ayahs of the clicked surah
                surah = searchedSurahs.get(i);

                //reset the current aya index
                currentAyaNumber = surah.getMinAya();

                //start requesting the first aya of this surah
                retrofitCall(identifier, currentAyaNumber);

                //set the current surah text
                currentSurahTextView.setText(surah.getName());

            }
        });

        //add listener to the play/pause button clicks
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.play_white_icon);
                } else {
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.pause_white_icon);
                }

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                //call start playing to start playing the next aya if exists
                startPlaying();

            }
        });

    }

    /**
     * A method called to start a retrofit call request for the current aya
     */
    private void retrofitCall(final String identifier, int ayaNumber) {

        //Create a new Retrofit Builder and pass the api base url to it
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_audio_url))
                .addConverterFactory(GsonConverterFactory.create());

        //Build the retrofit builder
        Retrofit retrofit = builder.build();

        //create a quran controller object
        QuranController quranController = retrofit.create(QuranController.class);

        //initialize the call request
        audioCall = quranController.getAyaAudio(identifier, ayaNumber);

        //start the call
        audioCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(final Call<ResponseBody> call, final Response<ResponseBody> response) {

                /**
                 * A Thread is used to handle the bytes conversion
                 */
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            //add the received bytes array of the current aya
                            bytesQueue.add(response.body().bytes());

                            //increment the current aya index
                            currentAyaNumber++;

                            if (currentAyaNumber <= surah.getMaxAya())
                                //if the current index is not equal the size of the current ayahs
                                // then request the next aya
                                retrofitCall(identifier, currentAyaNumber);

                            if (!isPlaying) {
                                //if the quran is not playing now -> start playing and set the playing flag to true
                                isPlaying = true;
                                startPlaying();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

                //start the thread
                thread.start();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    /**
     * A method called to start playing the surah
     */
    private void startPlaying() {

        //if the queue is not empty -> play the aya at the queue peek
        if (!bytesQueue.isEmpty()) {

            //set the isPlaying true
            isPlaying = true;

            //play the aya at the queue peek
            playMp3(bytesQueue.poll());

        } else {
            //if queue is empty -> set isPlaying flag to false
            isPlaying = false;
        }

    }

    /**
     * A method called to convert array of bytes to file to be able to be played by the MediaPlayer
     */
    private void playMp3(byte[] mp3SoundByteArray) {
        try {
            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(mp3SoundByteArray);
            fos.close();

            // resetting mediaplayer instance to evade problems
            mediaPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }

    /**
     * A method called to read the stored json file that contains all the quran from the assets folder and return it as String
     */
    private String getQuranAsString(Context context) {

        String json = null;
        try {
            InputStream is = context.getAssets().open("simple_quran.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "Unicode");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //stop the media player, reset and release it
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();

        //if there is any audio calls. cancel it
        if (audioCall != null)
            audioCall.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.main_toolbar_menu_search);
        final SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchedSurahs = search(newText);
                surahAdapter.updateSurahs(searchedSurahs);

                return false;
            }
        });

        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return true;
            }
        };
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);

        return true;

    }

    //For back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        else if (item.getItemId() == R.id.main_toolbar_menu_search) {

        }

        return super.onOptionsItemSelected(item);

    }


    /**
     * A method called and pass the text to it and it will return a list of azkar which matches
     */
    public ArrayList<SimpleSurah> search(String word) {

        if (TextUtils.isEmpty(word))
            return new ArrayList<>(quran.getSimpleSurahs());

        ArrayList<SimpleSurah> mySearchedArrayList = new ArrayList<>();
        char[] searchWord = word.toCharArray();
        ArrayList<char[]> elementsCharArrayList = new ArrayList<>();

        searchedSurahs.clear();
        searchedSurahs = new ArrayList<>(quran.getSimpleSurahs());

        int arraySize = searchedSurahs.size();
        for (int i = 0; i < arraySize; i++) {
            String x = searchedSurahs.get(i).getName();
            elementsCharArrayList.add(x.toCharArray());
        }

        char[][] elementsCharList = elementsCharArrayList.toArray(new char[0][]);

        for (int i = 0; i < arraySize; i++) {

            int x = 0;
            for (int j = 0; j < searchedSurahs.get(i).getName().length(); j++) {

                if (arabicCheck(searchWord[x], elementsCharList[i][j])) {
                    if (x == word.length() - 1) {
                        mySearchedArrayList.add(searchedSurahs.get(i));
                        break;
                    }
                    x++;
                } else {
                    x = 0;
                }
            }
        }
        return mySearchedArrayList;

    }

    //a function to check the Arabic Characters
    private Boolean arabicCheck(Character first, Character second) {

        int x = (int) first;
        int y = (int) second;

        Boolean a = x == y;

        // أ case for
        Boolean b = (x == 1569 || x == 1570 || x == 1571 || x == 1573 || x == 1574 || x == 1575
                || x == 1649 || x == 1650 || x == 1651 || x == 1652 || x == 1653)
                && (y == 1569 || y == 1570 || y == 1571 || y == 1573 || y == 1574 || y == 1575
                || y == 1649 || y == 1650 || y == 1651 || y == 1652 || y == 1653);

        // ي case for
        Boolean c = (x == 1568 || x == 1574 || x == 1597 || x == 1598 || x == 1599 || x == 1609
                || x == 1610 || x == 1656 || x == 1740 || x == 1741 || x == 1742 || x == 1744 || x == 1745)
                && (y == 1568 || y == 1574 || y == 1597 || y == 1598 || y == 1599 || y == 1609
                || y == 1610 || y == 1656 || y == 1740 || y == 1741 || y == 1742 || y == 1744 || y == 1745);

        // و case for
        Boolean d = (x == 1572 || x == 1608 || x == 1654 || x == 1655 || x == 1732 || x == 1733
                || x == 1734 || x == 1735 || x == 1736 || x == 1737 || x == 1738 || x == 1739 || x == 1743)
                && (y == 1572 || y == 1608 || y == 1654 || y == 1655 || y == 1732 || y == 1733
                || y == 1734 || y == 1735 || y == 1736 || y == 1737 || y == 1738 || y == 1739 || y == 1743);

        // ة case for
        Boolean e = (x == 1577 || x == 1607 || x == 1726 || x == 1728 || x == 1729 || x == 1730
                || x == 1731 || x == 1749 || x == 1791)
                && (y == 1577 || y == 1607 || y == 1726 || y == 1728 || y == 1729 || y == 1730
                || y == 1731 || y == 1749 || y == 1791);

        return a || b || c || d || e;

    }
}
