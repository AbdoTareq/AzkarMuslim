package com.omar.abdotareq.muslimpro.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.fragments.AhadethListFragment;
import com.omar.abdotareq.muslimpro.fragments.AzkarListFragment;
import com.omar.abdotareq.muslimpro.adapters.FragmentAdapter;
import com.omar.abdotareq.muslimpro.R;

/**
 * this UI class controls the viewPager & BottomNavigationView & set the correct fragment
 */

public class PagerListActivity extends AppCompatActivity {

    public static final String LOG_TAG = PagerListActivity.class.getSimpleName();

    ViewPager viewPager;
    BottomNavigationView navigation;

    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_list);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewpager); //Init Viewpager
        setupFm(getSupportFragmentManager(), viewPager); //Setup Fragment
        viewPager.setOnPageChangeListener(new PageChange()); //Listeners For Viewpager When Page Changed

        viewPager.setRotationY(180);

        if (getIntent().getIntExtra("index", -1) == 0) {

            viewPager.setCurrentItem(0); //Set Current Item When Activity Start
            navigation.setSelectedItemId(R.id.navigation_azkar);

        } else if (getIntent().getIntExtra("index", -1) == 1) {

            viewPager.setCurrentItem(1); //Set Current Item When Activity Start
            navigation.setSelectedItemId(R.id.navigation_ahadeth);

        }

    }

    public void setupFm(FragmentManager fragmentManager, ViewPager viewPager) {
        fragmentAdapter = new FragmentAdapter(fragmentManager);
        //Add All Fragment To List
        fragmentAdapter.add(new AzkarListFragment(), "Page Azkar");
        fragmentAdapter.add(new AhadethListFragment(), "Page Ahadeth");
        viewPager.setAdapter(fragmentAdapter);
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

                fragmentAdapter.changeAzkarAndAhadeth(newText);

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
}
