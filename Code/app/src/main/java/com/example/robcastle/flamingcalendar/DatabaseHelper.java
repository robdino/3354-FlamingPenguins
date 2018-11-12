package com.example.robcastle.flamingcalendar;

import android.database.sqlite.SQLiteDatabase;
import	android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Worth noting, this is an API from here: https://github.com/mitchtabian/SaveReadWriteDeleteSQLite
 * An associated YouTube video about the API: https://www.youtube.com/watch?v=aQAIMY-HzL8
 * I did some modifications to the class to make it work with our stuff.
 * @author Robbie
 * @since 11/11/18
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String TAG         = "DatabaseHelper";
    private static final String TABLE_NAME  = "event_table";
    private static final String DATE        = "DATE";
    private static final String DESC        = "DESCRIPTION";
    private static final String START       = "STARTTIME";
    private static final String END         = "ENDTIME";


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
                "ENDTIME VARCHAR(45) NOT NULL)";
        db.execSQL(createTable);
        Log.d(TAG, "onCreate: SQL database: making new db file");
    }

    @Override
    /**
     * @author Robbie
     * So this function aborts new table creation if one already exists
     */
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, "onUpgrade: SQL database: hopefully opening existing db file");
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
        values.put("STARTTIME",      item.getStartTime()     );
        values.put("ENDTIME",       item.getEndTime()       );

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
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        //the "SELECT * FROM [TABLE]" statement gets EVERY single piece of data from the table
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
       // data.close();
        return data;
    }

    /**
     * Returns only the records that match the date passed in
     * Can return multiple records, WILL NEED TO TEST THIS
     * @param date
     * @return
     */
    public Cursor getItemsForDate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + DATE + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    /*
    public void updateRecordDate(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 +
                " = '" + newName + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }


    *//**
     * Delete from database
     * @param id
     * @param name
     *//*
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
*/
}


