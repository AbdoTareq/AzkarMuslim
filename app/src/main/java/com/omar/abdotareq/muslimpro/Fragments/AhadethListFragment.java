package com.omar.abdotareq.muslimpro.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omar.abdotareq.muslimpro.activities.FragmentSearch;
import com.omar.abdotareq.muslimpro.activities.HadethActivity;
import com.omar.abdotareq.muslimpro.activities.PagerListActivity;
import com.omar.abdotareq.muslimpro.activities.ZekrActivity;
import com.omar.abdotareq.muslimpro.adapters.AhadethListAdapter;
import com.omar.abdotareq.muslimpro.adapters.AzkarListAdapter;
import com.omar.abdotareq.muslimpro.data.DataBaseHelper;
import com.omar.abdotareq.muslimpro.model.Hadeth;
import com.omar.abdotareq.muslimpro.R;
import com.omar.abdotareq.muslimpro.model.Zekr;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.omar.abdotareq.muslimpro.activities.PagerListActivity.LOG_TAG;

/**
 * this UI class controls Ahadeth .
 */
public class AhadethListFragment extends Fragment implements FragmentSearch {

    private ListView ahadethListView;
    private List<Hadeth> ahadeth = new ArrayList<>();
    private List<Hadeth> searchedAhadeth = new ArrayList<>();

    private AhadethListAdapter ahadethAdapter;


    public AhadethListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final String LOG_TAG = AhadethListFragment.class.getSimpleName();

        //get the view
        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        //initialize the hadeth list view
        ahadethListView = view.findViewById(R.id.listView);

        //initialize all the ahadeth from the databse
        initializeAhadeth();

        searchedAhadeth = new ArrayList<>(ahadeth);

        //initialize the adapter
        ahadethAdapter = new AhadethListAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                searchedAhadeth);

        //set up the adapter with the list view
        ahadethListView.setAdapter(ahadethAdapter);

        //listen to list item clicks
        ahadethListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if user clicks any item on the list

                //create a new intent to the Hadeth Activity
                Intent zekrIntetnt = new Intent(getActivity(), HadethActivity.class);

                //pass the clicked hadeth id as extras with the intent
                zekrIntetnt.putExtra("HADETH_ID", searchedAhadeth.get(i).getId());

                //start the activity
                startActivity(zekrIntetnt);
            }
        });

        //return the view
        return view;
    }

    /**
     * A method called to get all the ahadeth from the database and initialize them
     */
    private void initializeAhadeth() {

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(getContext());

        //get the cursor from the database pointing to all the ahadeth
        Cursor ahadethCursor = myDbHelper.openDataBase("forty");

        //loop on all the ahadeth
        while (ahadethCursor.moveToNext()) {

            //get the data
            int id = ahadethCursor.getInt(0);
            String title = ahadethCursor.getString(1);
            String text = ahadethCursor.getString(2);
            String teller = ahadethCursor.getString(3);
            int favourite = ahadethCursor.getInt(4);

            //add the hadeth to the ahadeth list view
            ahadeth.add(new Hadeth(id, title, text, teller, favourite));

        }

    }

    @Override
    public void onSearchTextChange(String text) {

        searchedAhadeth = search(text) ;
        ahadethAdapter.updateAhadethList(searchedAhadeth);

    }

    /**
     * A method called and pass the text to it and it will return a list of ahadeth which matches
     */
    public ArrayList<Hadeth> search(String word) {

        if (TextUtils.isEmpty(word)) {
            return new ArrayList<>(ahadeth);
        }

        ArrayList<Hadeth> mySearchedArrayList = new ArrayList<>();
        char[] searchWord = word.toCharArray();
        ArrayList<char[]> elementsCharArrayList = new ArrayList<>();

        searchedAhadeth.clear();
        searchedAhadeth = new ArrayList<>(ahadeth);

        int arraySize = searchedAhadeth.size();
        for (int i = 0; i < arraySize; i++) {
            String x = searchedAhadeth.get(i).getTitle();
            elementsCharArrayList.add(x.toCharArray());
        }

        char[][] elementsCharList = elementsCharArrayList.toArray(new char[0][]);

        for (int i = 0; i < arraySize; i++) {

            int x = 0;
            for (int j = 0; j < searchedAhadeth.get(i).getTitle().length(); j++) {

                if (arabicCheck(searchWord[x], elementsCharList[i][j])) {
                    if (x == word.length() - 1) {
                        mySearchedArrayList.add(searchedAhadeth.get(i));
                        break;
                    }
                    x++;
                } else {
                    x = 0;
                }
            }
        }
        return mySearchedArrayList;

    }

    //a function to check the Arabic Characters
    private Boolean arabicCheck(Character first, Character second) {

        int x = (int) first;
        int y = (int) second;

        Boolean a = x == y;

        // أ case for
        Boolean b = (x == 1569 || x == 1570 || x == 1571 || x == 1573 || x == 1574 || x == 1575
                || x == 1649 || x == 1650 || x == 1651 || x == 1652 || x == 1653)
                && (y == 1569 || y == 1570 || y == 1571 || y == 1573 || y == 1574 || y == 1575
                || y == 1649 || y == 1650 || y == 1651 || y == 1652 || y == 1653);

        // ي case for
        Boolean c = (x == 1568 || x == 1574 || x == 1597 || x == 1598 || x == 1599 || x == 1609
                || x == 1610 || x == 1656 || x == 1740 || x == 1741 || x == 1742 || x == 1744 || x == 1745)
                && (y == 1568 || y == 1574 || y == 1597 || y == 1598 || y == 1599 || y == 1609
                || y == 1610 || y == 1656 || y == 1740 || y == 1741 || y == 1742 || y == 1744 || y == 1745);

        // و case for
        Boolean d = (x == 1572 || x == 1608 || x == 1654 || x == 1655 || x == 1732 || x == 1733
                || x == 1734 || x == 1735 || x == 1736 || x == 1737 || x == 1738 || x == 1739 || x == 1743)
                && (y == 1572 || y == 1608 || y == 1654 || y == 1655 || y == 1732 || y == 1733
                || y == 1734 || y == 1735 || y == 1736 || y == 1737 || y == 1738 || y == 1739 || y == 1743);

        // ة case for
        Boolean e = (x == 1577 || x == 1607 || x == 1726 || x == 1728 || x == 1729 || x == 1730
                || x == 1731 || x == 1749 || x == 1791)
                && (y == 1577 || y == 1607 || y == 1726 || y == 1728 || y == 1729 || y == 1730
                || y == 1731 || y == 1749 || y == 1791);

        return a || b || c || d || e;

    }
}
