package com.omar.abdotareq.muslimpro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.omar.abdotareq.muslimpro.model.Zekr;

import java.util.List;

public class AzkarListAdapter extends ArrayAdapter {

    Context mContext;
    int mRes;
    List<Zekr> mAzkar;

    public AzkarListAdapter(Context context, int resource, List<Zekr> azkar) {
        super(context, resource, azkar);
        mContext = context;
        mRes = resource;
        mAzkar = azkar;
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

        TextView textView = row.findViewById(android.R.id.text1);

        textView.setText(mAzkar.get(position).getTitle());


        return row;
    }
}
