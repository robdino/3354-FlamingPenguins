package com.example.robcastle.flamingcalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteTests {

    private DatabaseHelper mDatabaseHelper;
    fpEvent myUnit;
    fpEvent myUnit2;
    fpEvent myUnit3;

    private Context context;
    private int size = 0;
    private SQLiteDatabase db;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
        mDatabaseHelper = new DatabaseHelper(this.context);
        myUnit = new fpEvent("12/6/18", "Demo for the class", "1:00 pm", "2:00 pm", "Demo");
        myUnit2 = new fpEvent("11/7/18", "Some random November day", "3:00 pm",
                                "4:00 pm", "No Shave November");
        myUnit3 = new fpEvent("12/15/18", "King of the Sea",
                            "7:00 pm", "10:00 pm", "Aquaman");
    }

    @Test
    public void testOpenDB() {
        db = mDatabaseHelper.getWritableDatabase();
        assertTrue(db.isOpen());
    }

    @Test
    public void testAddEvent() {
        boolean result =  mDatabaseHelper.addData(myUnit);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetSizeAfterAddEvent() {
        ArrayList<fpEvent> data = mDatabaseHelper.loadWeeklyEventList();
        assertEquals(data.size(), size + 1);
        size++;
    }

    @Test
    public void testAddSecondEvent() {
        boolean result =  mDatabaseHelper.addData(myUnit2);
        Assert.assertTrue(result);
    }


    @Test
    public void testAddThirdEvent() {
        boolean result =  mDatabaseHelper.addData(myUnit3);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetSizeAfterAdd3Events() {
        ArrayList<fpEvent> data = mDatabaseHelper.loadWeeklyEventList();
        assertEquals(data.size(), size + 2);
        size += 2;
    }

    @Test
    public void testCloseDB() {
        db.close();
        assertFalse(db.isOpen());
    }
}
