package com.omar.abdotareq.muslimpro.adapters.api_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.model.api_models.QuranAudio;
import com.omar.abdotareq.muslimpro.model.api_models.Surah;

import java.util.List;

public class SurahAdapter extends ArrayAdapter {

    private Context mContext;
    private int mRes;
    private List<Surah> mSurahs;

    public SurahAdapter(Context context, int resource, List<Surah> surahs) {
        super(context, resource, surahs);
        mContext = context;
        mRes = resource;
        mSurahs = surahs;
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
