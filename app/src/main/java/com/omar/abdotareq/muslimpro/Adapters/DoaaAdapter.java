package com.omar.abdotareq.muslimpro.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.omar.abdotareq.muslimpro.fragments.DoaaFragment;
import com.omar.abdotareq.muslimpro.model.Doaa;

import java.util.ArrayList;
import java.util.List;

public class DoaaAdapter extends FragmentPagerAdapter {

    private List<Doaa> doaas;

    public DoaaAdapter(FragmentManager fm, List<Doaa> doaas) {
        super(fm);
        this.doaas = doaas;

    }

    @Override
    public int getCount() {
        return this.doaas.size();
    }


    @Override
    public Fragment getItem(int position) {

        //create a new DoaaFragment
        DoaaFragment doaaFragment = new DoaaFragment();

        //create a new Bundle and get the current Zekr
        Bundle bundle = new Bundle();

        //put the zekr as extra to the fragment
        bundle.putSerializable("DOAA", doaas.get(position));
        bundle.putString("DOAAS_NUMBER", String.valueOf(doaas.size()));

        //set the arguments for the fragments
        doaaFragment.setArguments(bundle);

        //return the fragment
        return doaaFragment;
    }

}
