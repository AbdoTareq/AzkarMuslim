package com.omar.abdotareq.muslimpro.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.model.Hadeth;

/**
 * A UI class Activity which shows a hadeth
 */
public class HadethActivity extends AppCompatActivity {

    private Hadeth hadeth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadeth);

        //initialize UI elements
        TextView hadethText = findViewById(R.id.hadeth_text);
        TextView hadethTeller = findViewById(R.id.hadeth_teller);

        //get the clicked hadeth id from intent extra
        int hadethId = getIntent().getIntExtra("HADETH_ID", 0);

        //get the clicked hadeth from the database
        hadeth = initializeHdeth(hadethId);

        //set the hadeth text and teller
        hadethText.setText(hadeth.getText());
        hadethTeller.setText(hadeth.getTeller());

        getSupportActionBar().setTitle(hadeth.getTitle());

    }

    /**
     * A method called to return a hadeth by the passed id from the database
     */
    private Hadeth initializeHdeth(int hadethId) {

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(HadethActivity.this);

        //get the cursor object from the database pointing to the desired hadeth
        Cursor ahadethCursor = myDbHelper.getHadethById(hadethId);

        //if the hadeth exists
        if (ahadethCursor.moveToNext()) {

            //get hadeth data
            int id = ahadethCursor.getInt(0);
            String title = ahadethCursor.getString(1);
            String text = ahadethCursor.getString(2);
            String teller = ahadethCursor.getString(3);
            int favourite = ahadethCursor.getInt(4);

            //return the hadeth
            return new Hadeth(id, title, text, teller, favourite);

        }

        //notify user that a problem occurred
        Toast.makeText(HadethActivity.this, getString(R.string.problem_occurred), Toast.LENGTH_SHORT).show();

        //return an empty hadeth to avoid null pointer exceptions
        return new Hadeth();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.hadeth_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.share_hadeth) {

            String hadethText = hadeth.getText();
            hadethText += "\n";
            hadethText += hadeth.getTeller();

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, hadethText);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }

        return super.onOptionsItemSelected(item);
    }

}
