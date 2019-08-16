package com.omar.abdotareq.muslimpro.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DataBaseHelper.class.getSimpleName();

    public static final int DB_VERSION = 1;

    //The Android's default system path of your application database.

    private static String DB_NAME = "azkark.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static String DB_PATH;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME).getPath();

    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {

            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();
                Toast.makeText(myContext, "Copied Successfully", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public Cursor openDataBase(String tableName) throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return myDataBase.rawQuery("SELECT * FROM " + tableName, null);

    }

    /**
     * A method called to get a doaa list by their zekr parent id
     */
    public Cursor getDoaaByZekrId(int id) {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return myDataBase.rawQuery("SELECT * FROM " + "doaa" + " WHERE " + "zekrParentID" + " = " + id, null);

    }

    /**
     * A method called to get a hadeth by it's id
     */
    public Cursor getHadethById(int id) {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return myDataBase.rawQuery("SELECT * FROM " + "forty" + " WHERE " + "_id" + " = " + id, null);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            db.disableWriteAheadLogging();
        }

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            db.disableWriteAheadLogging();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}