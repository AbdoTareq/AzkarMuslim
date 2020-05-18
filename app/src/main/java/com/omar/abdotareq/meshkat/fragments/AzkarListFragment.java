package com.omar.abdotareq.meshkat.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.omar.abdotareq.meshkat.R;
import com.omar.abdotareq.meshkat.activities.FragmentSearch;
import com.omar.abdotareq.meshkat.activities.ZekrActivity;
import com.omar.abdotareq.meshkat.adapters.AzkarListAdapter;
import com.omar.abdotareq.meshkat.data.DataBaseHelper;
import com.omar.abdotareq.meshkat.model.Zekr;

import java.util.ArrayList;

/**
 * this UI class controls Azkar .
 */
public class AzkarListFragment extends Fragment implements FragmentSearch {

    public static final String LOG_TAG = AzkarListFragment.class.getSimpleName();


    private ListView azkarListView;
    private ArrayList<Zekr> azkar = new ArrayList<>();
    private ArrayList<Zekr> searchedAzkar = new ArrayList<>();

    private AzkarListAdapter azkarAdapter;

    public AzkarListFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //get the view
        View view = inflater.inflate(R.layout.fragment_list_azkar_hadeth, container, false);

        //initialize the list view
        azkarListView = view.findViewById(R.id.listView);
        azkarListView.setRotationY(180) ;

        //Initialize the azkar from the database
        initializeAzkar();

        searchedAzkar = new ArrayList<>(azkar);

        // initialize the adapter
        azkarAdapter = new AzkarListAdapter(
                getContext(),
                R.layout.hadeth_azkar_list_item,
                searchedAzkar);

        //set up the adapter with the list view
        azkarListView.setAdapter(azkarAdapter);

        //listen to list item clicks
        azkarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //if user clicks any item on the list

                //create a new intent to the Zekr Activity
                Intent zekrIntetnt = new Intent(getActivity(), ZekrActivity.class);

                //pass the clicked zekr id as extras with the intent
                zekrIntetnt.putExtra("ZEKR_ID", searchedAzkar.get(i).getId());
                zekrIntetnt.putExtra("ZEKR_TITLE", searchedAzkar.get(i).getTitle());

                //start the activity
                startActivity(zekrIntetnt);

            }
        });

        //return the view
        return view;

    }

    /**
     * A method called to get all the azkar from the database
     */
    private void initializeAzkar() {

        //Initialize Instance of DataBaseHelper class and initiallize it
        DataBaseHelper myDbHelper = new DataBaseHelper(getContext());

        //get the cursor from the database pointing to all the azkar titles
        Cursor azkarCursor = myDbHelper.openDataBase("azkarTitles");

        //loop on all the azkar
        while (azkarCursor.moveToNext()) {

            //get the data
            int id = azkarCursor.getInt(0);
            String titleNoTa4kel = azkarCursor.getString(1);
            int doaaCount = azkarCursor.getInt(2);
            String title = azkarCursor.getString(3);

            //add the zekr to the azkar list view
            azkar.add(new Zekr(id, title, titleNoTa4kel, 0, doaaCount));

        }

    }

    @Override
    public void onSearchTextChange(String text) {

        searchedAzkar = search(text);
        azkarAdapter.updateAzkar(searchedAzkar);

    }


    /**
     * A method called and pass the text to it and it will return a list of azkar which matches
     */
    public ArrayList<Zekr> search(String word) {

        if (TextUtils.isEmpty(word))
            return new ArrayList<>(azkar);

        ArrayList<Zekr> mySearchedArrayList = new ArrayList<>();
        char[] searchWord = word.toCharArray();
        ArrayList<char[]> elementsCharArrayList = new ArrayList<>();

        searchedAzkar.clear();
        searchedAzkar = new ArrayList<>(azkar);

        int arraySize = searchedAzkar.size();
        for (int i = 0; i < arraySize; i++) {
            String x = searchedAzkar.get(i).getTitleNoTa4kel();
            elementsCharArrayList.add(x.toCharArray());
        }

        char[][] elementsCharList = elementsCharArrayList.toArray(new char[0][]);

        for (int i = 0; i < arraySize; i++) {

            int x = 0;
            for (int j = 0; j < searchedAzkar.get(i).getTitleNoTa4kel().length(); j++) {

                if (arabicCheck(searchWord[x], elementsCharList[i][j])) {
                    if (x == word.length() - 1) {
                        mySearchedArrayList.add(searchedAzkar.get(i));
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
