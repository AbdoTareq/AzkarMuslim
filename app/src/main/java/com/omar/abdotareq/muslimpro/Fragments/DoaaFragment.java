package com.omar.abdotareq.muslimpro.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.data.CountsAsString;
import com.omar.abdotareq.muslimpro.model.Doaa;

/**
 * An UI class to view zekr to count zekr count like tasbeh
 */
public class DoaaFragment extends Fragment {

    //Create UI elements
    private TextView doaaText;
    private TextView doaaTeller;
    private TextView doaaOrder;
    private TextView doaaCount;
    private TextView doaaCurrentCount;
    AnimateHorizontalProgressBar doaaProgressBar;

    //an int to indicate the number of spoken doaa
    private int currentDoaaCount = 0;


    public DoaaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doaa, container, false);

        //get the contect
        Context context = getContext();

        //initialize UI elements
        doaaText = view.findViewById(R.id.doaa_text);
        doaaTeller = view.findViewById(R.id.doaa_teller);
        doaaOrder = view.findViewById(R.id.doaa_order);
        doaaCount = view.findViewById(R.id.doaa_count);
        doaaCurrentCount = view.findViewById(R.id.doaa_current_count);
        doaaProgressBar = view.findViewById(R.id.doaa_progress_bar);

        //get the current Doaa nad the total number of doaas
        final Doaa currentDoaa = (Doaa) getArguments().getSerializable("DOAA");
        int totalDoaasNumber = Integer.parseInt(getArguments().getString("DOAAS_NUMBER"));

        //set text for all text fields
        doaaText.setText(currentDoaa.getText());
        doaaTeller.setText(currentDoaa.getTeller());
        String zekrOrderText = (currentDoaa.getId() + 1) + context.getString(R.string.outOf) + totalDoaasNumber;
        doaaOrder.setText(zekrOrderText);
        doaaCount.setText(CountsAsString.getCountAsString(currentDoaa.getNumber()));
        doaaCurrentCount.setText(currentDoaaCount);

        //set the progress bar info
        doaaProgressBar.setMax(currentDoaa.getNumber());
        doaaProgressBar.setProgress(currentDoaaCount);

        //find the whole layout and listen for clicks
        RelativeLayout doaaLayout = view.findViewById(R.id.doaa_full_layout);
        doaaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentDoaaCount < currentDoaa.getNumber()) {
                    //if the current doaa count is still less than the total counts

                    //increment the current count by 1
                    currentDoaaCount++;

                    //update this info to the user
                    doaaCurrentCount.setText(currentDoaaCount);
                    doaaProgressBar.setProgress(currentDoaaCount);

                } else {

                    /**Todo: Here OMAR will check if this is not the last page -> go to next page
                     * Todo: else if last page -> notify user that zekr has ended
                     */

                }

            }
        });

        return view;
    }

}
