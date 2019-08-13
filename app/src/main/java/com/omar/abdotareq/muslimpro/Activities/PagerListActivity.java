package com.omar.abdotareq.muslimpro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omar.abdotareq.muslimpro.fragments.AhadethListFragment;
import com.omar.abdotareq.muslimpro.fragments.AzkarListFragment;
import com.omar.abdotareq.muslimpro.adapters.FragmentAdapter;
import com.omar.abdotareq.muslimpro.R;

/**
 * this UI class controls the viewPager & BottomNavigationView & set the correct fragment
 * */

public class PagerListActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_list);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewpager); //Init Viewpager
        setupFm(getSupportFragmentManager(), viewPager); //Setup Fragment
        viewPager.setOnPageChangeListener(new PageChange()); //Listeners For Viewpager When Page Changed

        if (getIntent().getIntExtra("index", -1) == 0) {
            viewPager.setCurrentItem(0); //Set Current Item When Activity Start
            navigation.setSelectedItemId(R.id.navigation_azkar);
        }
        else if (getIntent().getIntExtra("index", -1) == 1) {
            viewPager.setCurrentItem(1); //Set Current Item When Activity Start
            navigation.setSelectedItemId(R.id.navigation_ahadeth);

        }


    }

    public static void setupFm(FragmentManager fragmentManager, ViewPager viewPager) {
        FragmentAdapter Adapter = new FragmentAdapter(fragmentManager);
        //Add All Fragment To List
        Adapter.add(new AzkarListFragment(), "Page Azkar");
        Adapter.add(new AhadethListFragment(), "Page Ahadeth");
        viewPager.setAdapter(Adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_azkar:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_ahadeth:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };

    public class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    navigation.setSelectedItemId(R.id.navigation_azkar);
                    break;
                case 1:
                    navigation.setSelectedItemId(R.id.navigation_ahadeth);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
