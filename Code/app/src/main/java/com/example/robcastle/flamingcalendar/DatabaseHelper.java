package com.example.robcastle.flamingcalendar;

import android.database.sqlite.SQLiteDatabase;
import	android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Worth noting, this is an API from here: https://github.com/mitchtabian/SaveReadWriteDeleteSQLite
 * An associated YouTube video about the API: https://www.youtube.com/watch?v=aQAIMY-HzL8
 * I did some modifications to the class to make it work with our stuff.
 *
 * @author Robbie :: Geoffrey
 * :: mainly added REMINDER components to DatabaseHelper, SQL_TABLE, to parseData() and addData()
 * @since 11/12/18 :: 12/07/18
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String TAG         = "DatabaseHelper";
    private static final String TABLE_NAME  = "event_table";
    private static final String DATE        = "DATE";
    private static final String DESC        = "DESCRIPTION";
    private static final String START       = "STARTTIME";
    private static final String END         = "ENDTIME";
    private static final String NAME        = "NAME";
    private static final String REMINDER    = "REMINDER";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    /**
     * @author Robbie
     * This creates a new table
     */
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (DATE VARCHAR(45) NOT NULL, " +
                "DESCRIPTION VARCHAR(45) NOT NULL, " +
                "STARTTIME VARCHAR(45) NOT NULL, " +
                "ENDTIME VARCHAR(45) NOT NULL, " +
                "NAME VARCHAR(45) NOT NULL, " +
                "REMINDER INTEGER NOT NULL, " +
                "IDnum INTEGER PRIMARY KEY)";

        db.execSQL(createTable);
        Log.d(TAG, "onCreate: SQL database: making new db file");
    }

    @Override
    /**
     * @author Robbie
     * So this function aborts new table creation if one already exists
     */
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //This is here so Android will stop crying

        //The technical explanation: When extending (aka inheriting) the SQLiteOpenHelper class,
        //  Android requires the new subclass (the child class) to override two methods: onCreate, and onUpgrade

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * IMPORTANT: WE USE THIS FUNCTION TO ADD DATA TO TABLE
     * @param item
     * @return
     */
    public boolean addData(fpEvent item) {
        SQLiteDatabase db = this.getWritableDatabase();

        //we use ContentValues as a "box" to send fpEvent data into our SQLite table
        //basically: "data" -> [box] -> "table"
        //Robbie 11/11/18
        ContentValues values = new ContentValues();

        //load each piece of data in
        values.put("DATE",          item.getDate()          );
        values.put("DESCRIPTION",   item.getDescription()   );
        values.put("STARTTIME",     item.getStartTime()     );
        values.put("ENDTIME",       item.getEndTime()       );
        values.put("NAME",          item.getName()          );
        values.put("REMINDER",      item.getReminder()      );

        //log - for testing purposes - what we plan to do
        Log.d(TAG, "addData: Adding " + values.toString() + " to " + TABLE_NAME);

        //INSERT INTO SQLITE TABLE
        long result1 = db.insert(TABLE_NAME, null, values);

        //if anything got inserted incorrectly it will return -1
        if (result1 == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        //the "SELECT * FROM [TABLE]" statement gets EVERY single piece of data from the table
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Returns only the records that match the date passed in
     * Can return multiple records, WILL NEED TO TEST THIS
     * @param date
     * @return
     */
    private Cursor getItemsForDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + DATE + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the record that match the IDnum passed in
     * Can return only a single record, WILL NEED TO TEST THIS
     * @param givenID
     * @return
     */
    private Cursor getItemForID(int givenID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + "IDnum" + " = '" + givenID + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * @author Robbie
     * @since 11/20/18
     * @return new eventList using date
     */
    public ArrayList<fpEvent> getDailyData(String date)
    {
        ArrayList<fpEvent> eventList;

        Cursor data;
        data = getItemsForDate(date); //query for the data

        eventList = parseData(data);

        return eventList;
    }

    /**
     * @author Robbie
     * @since 11/20/18
     * @return new eventList using date
     */
    public ArrayList<fpEvent> getDailyData(String date, String desc, String name)
    {
        ArrayList<fpEvent> eventList;

        Cursor data;
        data = getItemsForDate(date); //query for the data

        eventList = parseData(data);

        if(eventList.size() == 1)
            return eventList;
        else
        {
            for (int i = 0; i < eventList.size(); i++)
            {
                if(!eventList.get(i).getName().equals(name))
                    eventList.remove(i--);
                else if(!eventList.get(i).getDescription().equals(desc))
                    eventList.remove(i--);
                else
                    Log.d(TAG, "Cannot separate: " + eventList.get(i).getName() + " from: " + name);
            }
        }
        return eventList;
    }

    /**
     * @author Robbie
     * @since 11/24/18
     * @return new eventList using IDnum
     */
    public ArrayList<fpEvent> getDailyData(int givenID)
    {
        ArrayList<fpEvent> eventList;

        Cursor data;
        data = getItemForID(givenID); //query for the data

        eventList = parseData(data);

        return eventList;
    }

    /**
     * @author Robbie
     * @since 11/12/18
     * @return new eventList w/update from SQL file
     */
    public ArrayList<fpEvent> loadWeeklyEventList()
    {
        ArrayList<fpEvent> eventList;

        Cursor data;
        data = getData(); //query for the data

        eventList = parseData(data);

        return eventList;
    }

    /**
     * @author Robbie :: Geoffrey
     * @since 11/24/18 :: 12/07/18
     * @return new eventList with parsed data :: and reminder data
     */
    private ArrayList<fpEvent> parseData(Cursor data)
    {
        ArrayList<fpEvent> eventList = new ArrayList<>();

        int recordNum = data.getCount();    //get how many records

        for(int i = 0; i < recordNum; i++)
        {
            data.moveToNext(); //Index starts at -1, so we moveToNext() so it starts from element 0
            fpEvent newEvent = new fpEvent(
                    data.getString(0), //DATE
                    data.getString(1), //DESCRIPTION
                    data.getString(2), //START TIME
                    data.getString(3), //END TIME
                    data.getString(4), //NAME
                    data.getInt(5)     //REMINDER
            );

            int id = Integer.parseInt( data.getString(6) );      //IDnum
            newEvent.setIDnum(id);


            Log.d(TAG, "parseData:" +
                    " Date: "           + newEvent.getDate() +
                    " Description: "    + newEvent.getDescription() +
                    " Start Time: "     + newEvent.getStartTime() +
                    " End Time: "       + newEvent.getEndTime() +
                    " Name: "           + newEvent.getName() +
                    " Reminder: "       + newEvent.getReminder() +
                    " IDnum: "          + newEvent.getIDnum()
            );

            eventList.add(newEvent);
        }

        return eventList;
    }

    /**
     *
     */
    public void updateRecord(fpEvent item){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + item.getDate() + "'"
                + " AND " + DESC + " = '" + item.getDescription() + "'";

        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + item.getName() + " from database.");
        db.execSQL(query);

        addData(item);
    }


    /**
     * Delete from database
     * @param
     * @param
     */
    public void deleteRecord(String name, String date, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + DATE + " = '" + date + "'"
                + " AND " + DESC + " = '" + desc + "'";

        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }


    public void deleteRecord(final int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + "IDnum" + " = '" + id + "'";

        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + id + " from database.");
        db.execSQL(query);
    }

}


