package com.omar.abdotareq.muslimpro.adapters.api_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.omar.abdotareq.muslimpro.model.Zekr;
import com.omar.abdotareq.muslimpro.model.api_models.SimpleSurah;

import java.util.List;

public class SurahAdapter extends ArrayAdapter {

    private Context mContext;
    private int mRes;
    private List<SimpleSurah> mSurahs;

    public SurahAdapter(Context context, int resource, List<SimpleSurah> surahs) {
        super(context, resource, surahs);
        mContext = context;
        mRes = resource;
        mSurahs = surahs;
    }

    /**
     * A method called to update the surahs list of the adapter and notify for changes
     */
    public void updateSurahs(List<SimpleSurah> surahs) {

        //clear the current surahs
        this.mSurahs.clear();

        //add the new surahs
        this.mSurahs.addAll(surahs);

        //notify the adapter for data changes
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(Object item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        //Inflate Layout
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        row = layoutInflater.inflate(mRes, parent, false);

        //find the text view
        TextView surahTextView = row.findViewById(android.R.id.text1);

        //set the surah name on the text view
        surahTextView.setText(mSurahs.get(position).getName());

        return row;
    }

}
