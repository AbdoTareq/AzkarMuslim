package com.omar.abdotareq.muslimpro.activities;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.adapters.DoaaAdapter;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.model.Doaa;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.util.ArrayList;
import java.util.List;

/**
 * A UI class Activity which contains a view pager that shows all the doaa for a zekr
 */
public class ZekrActivity extends AppCompatActivity {

    //create empty view pager
    private ViewPager azkarViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekr);

        //initialize view pager
        azkarViewpager = findViewById(R.id.azkar_viewpager); //Init Viewpager

        //get the clicked zekr id from intent extra
        int zekrId = getIntent().getIntExtra("ZEKR_ID", 0);

        //get the zekr title and set to the toolbar
        String zekrTitle = getIntent().getStringExtra("ZEKR_TITLE");
        getSupportActionBar().setTitle(zekrTitle);

        //set up the view pager with the fragment manager
        setupDoaaFm(zekrId); //Setup Fragment

    }

    /**
     * A method called to setup the fragment manger along with the doaa in this zekr
     */
    public void setupDoaaFm(int zekrParentId) {

        //get the doaa list in this zekr (which have the same parent zekr id as the passed from the intent")
        List<Doaa> doaaList = initializeDoaa(zekrParentId);

        //initialie the doaa adapter
        DoaaAdapter doaaAdapter = new DoaaAdapter(getSupportFragmentManager(), doaaList);

        //set up the adapter with the view pager
        azkarViewpager.setAdapter(doaaAdapter);

        //set the offScfreen Pages Limit to the number of doaas - 1
        azkarViewpager.setOffscreenPageLimit(doaaList.size() - 1);
        azkarViewpager.setRotationY(180);

        //set the direction of the view pager swipe LTR(LEFT TO RIGHT)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            azkarViewpager.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
//        }
    }

    /**
     * A method called from the fragment in order to get the view pager instance to be able to move to the next page when a doaa finishes
     */
    public ViewPager getPager() {
        return azkarViewpager;
    }

    /**
     * A method called to get the doaa which have the same zekr parent id as the passed to the method and return doaa list
     */
    private List<Doaa> initializeDoaa(int zekrParentId) {

        //initialize empty Doaa list
        List<Doaa> doaaList = new ArrayList<>();

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(ZekrActivity.this);

        //get the cursor object from the database pointing to the desired doaas
        Cursor azkarCursor = myDbHelper.getDoaaByZekrId(zekrParentId);

        //loop on the cursor rows
        while (azkarCursor.moveToNext()) {

            //get the data
            int id = azkarCursor.getInt(0);
            String text = azkarCursor.getString(1);
            String teller = azkarCursor.getString(2);
            int count = azkarCursor.getInt(4);

            //add a new doaa to the list
            doaaList.add(new Doaa(id, text, teller, zekrParentId, count));

        }

        //return the doaa list
        return doaaList;

    }
}
