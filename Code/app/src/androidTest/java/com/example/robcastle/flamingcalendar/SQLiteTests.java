package com.example.robcastle.flamingcalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ALL TESTS MUST BE RUN IN SEQUENCE
 * @author Robbie
 * @since 12/7/18
 */

@RunWith(AndroidJUnit4.class)
public class SQLiteTests {

    private DatabaseHelper database;
    fpEvent myUnit;
    fpEvent myUnit2;
    fpEvent myUnit3;

    private int size = 0;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(DatabaseHelper.TABLE_NAME);
        database = new DatabaseHelper(getTargetContext());

        myUnit = new fpEvent("12/6/18", "Demo for the class", "1:00 pm", "2:00 pm", "Demo",0 );
        myUnit2 = new fpEvent("11/7/18", "Some random November day", "3:00 pm",
                                "4:00 pm", "No Shave November",0 );
        myUnit3 = new fpEvent("12/15/18", "King of the Sea",
                            "7:00 pm", "10:00 pm", "Aquaman",0 );
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

    @Test
    public void testAddEvent() {
        boolean result =  database.addData(myUnit);
        size++;
        Assert.assertTrue(result);
    }

    @Test
    public void testGetSizeAfterAdd1Event() {
        ArrayList<fpEvent> data = database.loadWeeklyEventList();
        assertEquals(size, data.size());

    }

    @Test
    public void testAddSecondEvent() {
        boolean result =  database.addData(myUnit2);
        size++;
        Assert.assertTrue(result);
    }


    @Test
    public void testAddThirdEvent() {
        boolean result =  database.addData(myUnit3);
        size++;
        Assert.assertTrue(result);
    }

    @Test
    public void testGetSizeAfterAdd3Events() {
        ArrayList<fpEvent> data = database.loadWeeklyEventList();
        assertEquals(size, data.size());
    }

    @Test
    public void testDelete2Events() {
        database.addData(myUnit);
        database.addData(myUnit2);

        database.deleteRecord(myUnit2.getName(), myUnit2.getDate(), myUnit2.getDescription());
        ArrayList<fpEvent> data = database.loadWeeklyEventList();
        assertEquals(1, data.size());
    }

}
