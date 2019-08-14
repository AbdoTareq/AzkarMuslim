package com.omar.abdotareq.muslimpro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.adapters.FragmentAdapter;
import com.omar.abdotareq.muslimpro.fragments.DoaaFragment;

public class ZekrActivity extends AppCompatActivity {

    ViewPager azkarViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekr);

        azkarViewpager = findViewById(R.id.azkar_viewpager); //Init Viewpager

        setupFm(getSupportFragmentManager(), azkarViewpager); //Setup Fragment
        azkarViewpager.setOnPageChangeListener(new PageChange()); //Listeners For Viewpager When Page Changed

        if (getIntent().getIntExtra("index", -1) == 0) {
            azkarViewpager.setCurrentItem(0); //Set Current Item When Activity Start
        } else if (getIntent().getIntExtra("index", -1) == 1) {
            azkarViewpager.setCurrentItem(1); //Set Current Item When Activity Start
        } else if (getIntent().getIntExtra("index", -1) == 2) {
            azkarViewpager.setCurrentItem(2); //Set Current Item When Activity Start
        }

    }

    public static void setupFm(FragmentManager fragmentManager, ViewPager viewPager) {
        FragmentAdapter Adapter = new FragmentAdapter(fragmentManager);
        //Add All Fragment To List
        Adapter.add(new DoaaFragment(), "Zekr 1");
        Adapter.add(new DoaaFragment(), "Zekr 2");
        viewPager.setAdapter(Adapter);
    }


    public class PageChange implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
