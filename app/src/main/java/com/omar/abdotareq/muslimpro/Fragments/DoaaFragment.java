package com.omar.abdotareq.muslimpro.fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daasuu.ahp.AnimateHorizontalProgressBar;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.activities.ZekrActivity;
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

        //get the context
        final Context context = getContext();

        //initialize UI elements
        doaaText = view.findViewById(R.id.doaa_text);
        doaaTeller = view.findViewById(R.id.doaa_teller);
        doaaOrder = view.findViewById(R.id.doaa_order);
        doaaCount = view.findViewById(R.id.doaa_count);
        doaaCurrentCount = view.findViewById(R.id.doaa_current_count);
        doaaProgressBar = view.findViewById(R.id.doaa_progress_bar);

        //get the current Doaa nad the total number of doaas
        final Doaa currentDoaa = (Doaa) getArguments().getSerializable("DOAA");
        final int totalDoaasNumber = Integer.parseInt(getArguments().getString("DOAAS_NUMBER"));

        //set text for all text fields
        doaaText.setText(currentDoaa.getText());
        doaaTeller.setText(currentDoaa.getTeller());
        String zekrOrderText = (currentDoaa.getId() + 1) + context.getString(R.string.outOf) + totalDoaasNumber;
        doaaOrder.setText(zekrOrderText);
        doaaCount.setText(CountsAsString.getCountAsString(currentDoaa.getNumber()));
        doaaCurrentCount.setText(String.valueOf(currentDoaaCount));

        //set the progress bar info
        doaaProgressBar.setMax(currentDoaa.getNumber());
        doaaProgressBar.setProgress(currentDoaaCount);

        //find the whole layout and listen for clicks
        RelativeLayout doaaLayout = view.findViewById(R.id.doaa_full_layout);
        doaaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentDoaaCount == currentDoaa.getNumber() - 1
                        && currentDoaa.getId() != totalDoaasNumber - 1) {
                    //if the current doaa count is the last count for this doaa
                    //and this is not the last doaa

                    //increment the current count by 1
                    currentDoaaCount++;

                    //update this info to the user
                    doaaCurrentCount.setText(String.valueOf(currentDoaaCount));
                    doaaProgressBar.setProgress(currentDoaaCount);

                    //then go to the next doaa
                    ((ZekrActivity) getActivity()).getPager().setCurrentItem(currentDoaa.getId() + 1);

                } else if (currentDoaaCount < currentDoaa.getNumber() && currentDoaaCount != currentDoaa.getNumber() - 1) {
                    //if the current doaa count is still less than the total counts

                    //increment the current count by 1
                    currentDoaaCount++;

                    //update this info to the user
                    doaaCurrentCount.setText(String.valueOf(currentDoaaCount));
                    doaaProgressBar.setProgress(currentDoaaCount);

                } else if (currentDoaa.getId() < totalDoaasNumber - 1) {
                    //if the current doaa count is not the last doaa

                    //then go to the next doaa
                    ((ZekrActivity) getActivity()).getPager().setCurrentItem(currentDoaa.getId() + 1);

                } else {
                    //if the current doaa count is the last doaa

                    //increment the current count by 1
                    if (currentDoaaCount < currentDoaa.getNumber())
                        currentDoaaCount++;

                    //update this info to the user
                    doaaCurrentCount.setText(String.valueOf(currentDoaaCount));
                    doaaProgressBar.setProgress(currentDoaaCount);

                    //vibrate and notify user that this is this zekr finished
                    vibrate(context);
                    Toast.makeText(context, context.getString(R.string.doaa_finished), Toast.LENGTH_SHORT).show();

                }

            }
        });

        return view;
    }

    /**
     * this method must be @JavascriptInterface as ' It's cause the method getSystemService
     * belongs to the class Context, so you have to run it on a context but we're running it from an activity.'
     */
    @JavascriptInterface
    public void vibrate(Context cn) {
        Vibrator v = (Vibrator) cn.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 700 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            assert v != null;
            v.vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            assert v != null;
            v.vibrate(400);
        }

    }
}
