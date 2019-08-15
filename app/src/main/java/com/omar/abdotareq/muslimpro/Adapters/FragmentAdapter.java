package com.omar.abdotareq.muslimpro.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.omar.abdotareq.muslimpro.activities.FragmentSearch;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<androidx.fragment.app.Fragment> fragments = new ArrayList<>(); //Fragment List
    private List<String> NamePage = new ArrayList<>(); // Fragment Name List

    public FragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    public void add(Fragment Frag, String Title) {
        fragments.add(Frag);
        NamePage.add(Title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return NamePage.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * A method called when searching , the searched word is passed to it and then the method
     * will call the search method in all fragments it holds
     */
    public void changeAzkarAndAhadeth(String word) {

        //loop on all fragments inside it
        for (androidx.fragment.app.Fragment frag : fragments) {

            //call onSearchTextChange method
            ((FragmentSearch) frag).onSearchTextChange(word);

        }

    }
}