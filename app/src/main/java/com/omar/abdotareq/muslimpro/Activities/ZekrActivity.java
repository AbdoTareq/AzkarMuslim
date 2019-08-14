package com.omar.abdotareq.muslimpro.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.adapters.DoaaAdapter;
import com.omar.abdotareq.muslimpro.adapters.FragmentAdapter;
import com.omar.abdotareq.muslimpro.fragments.DoaaFragment;
import com.omar.abdotareq.muslimpro.model.Doaa;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.util.ArrayList;
import java.util.List;

public class ZekrActivity extends AppCompatActivity {

    private ViewPager azkarViewpager;
    private DoaaAdapter doaaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekr);

        azkarViewpager = findViewById(R.id.azkar_viewpager); //Init Viewpager

        setupFm(); //Setup Fragment

        azkarViewpager.setOnPageChangeListener(new PageChange()); //Listeners For Viewpager When Page Changed

//        if (getIntent().getIntExtra("index", -1) == 0) {
//            azkarViewpager.setCurrentItem(0); //Set Current Item When Activity Start
//        } else if (getIntent().getIntExtra("index", -1) == 1) {
//            azkarViewpager.setCurrentItem(1); //Set Current Item When Activity Start
//        } else if (getIntent().getIntExtra("index", -1) == 2) {
//            azkarViewpager.setCurrentItem(2); //Set Current Item When Activity Start
//        }

    }

    public void setupFm() {

        //Initialize three doaas for test
        //Todo: remove these three initializations after implementing the DB Helper
        Doaa doaa1 = new Doaa(0, "سبحان الله", "عمر", 0, 5);
        Doaa doaa2 = new Doaa(1, "الحمد لله", "عمر", 0, 5);
        Doaa doaa3 = new Doaa(2, "الله أكبر", "عمر", 0, 5);

        ArrayList<Doaa> doaaList = new ArrayList<>();
        doaaList.add(doaa1);
        doaaList.add(doaa2);
        doaaList.add(doaa3);

        //initialie the doaa adapter
        doaaAdapter = new DoaaAdapter(getSupportFragmentManager(), doaaList);

        //set up the adapter with the view pager
        azkarViewpager.setAdapter(doaaAdapter);

        //set the offScfreen Pages Limit to the number of doaas - 1
        //Todo: change the 2 to zekr.getDoaasNumber()-1
        azkarViewpager.setOffscreenPageLimit(2);
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

    /**
     * A method called from the fragment in order to get the view pager instance to be able to move to the next page when a doaa finishes
     */
    public ViewPager getPager() {
        return azkarViewpager;
    }
}
