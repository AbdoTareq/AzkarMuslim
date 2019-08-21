package com.omar.abdotareq.muslimpro.adapters.api_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.model.api_models.QuranAudio;

import java.util.List;

public class QuranAudioAdapter extends ArrayAdapter {

    private Context mContext;
    private int mRes;
    private List<QuranAudio> mQuranAudios;

    public QuranAudioAdapter(Context context, int resource, List<QuranAudio> quranAudios) {
        super(context, resource, quranAudios);
        mContext = context;
        mRes = resource;
        mQuranAudios = quranAudios;
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

        TextView headerTextView = row.findViewById(R.id.row_header);
        TextView textTextView = row.findViewById(R.id.row_text);

        headerTextView.setText(mQuranAudios.get(position).getEnglishName());
        textTextView.setText(mContext.getString(R.string.language) + mQuranAudios.get(position).getLanguageName());

        return row;
    }


}
