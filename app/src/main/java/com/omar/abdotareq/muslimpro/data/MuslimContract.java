package com.omar.abdotareq.muslimpro.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Azkar app.
 */
public final class MuslimContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private MuslimContract() {
    }

    public static final class ZekrEntry implements BaseColumns {

        /**
         * Inner class that defines constant values for the ZEKRs database table.
         * Each entry in the table represents a single ZEKR.
         */
        public static final String TABLE_NAME = "zekr";

        /**
         * Unique ID number for the ZEKR (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ZEKR_TEXT = "text";
        public static final String COLUMN_ZEKR_TELLER = "teller";
        public static final String COLUMN_ZEKR_HEADID = "headID";
        public static final String COLUMN_ZEKR_NUMBER = "number";


    }

  public static final class HadethEntry implements BaseColumns {

        /**
         * Inner class that defines constant values for the HADETHs database table.
         * Each entry in the table represents a single HADETH.
         */
        public static final String TABLE_NAME = "hadeth";

        /**
         * Unique ID number for the HADETH (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HADETH_TEXT = "text";
        public static final String COLUMN_HADETH_TITLE = "title";
        public static final String COLUMN_HADETH_TELLER = "teller";
        public static final String COLUMN_HADETH_HEADID = "headID";
        public static final String COLUMN_HADETH_NUMBER = "favourite";


    }


}







