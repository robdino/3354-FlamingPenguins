package com.example.robcastle.flamingcalendar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Figuring out Junit testing with the FpEvents class
public class TestFpEventTest {
    fpEvent myUnit;
    fpEvent myUnit2;
    fpEvent myUnit3;
    fpEvent myUnit4;
    fpEvent myUnit5;

    @Before
    public void setUp() {
        myUnit = new fpEvent("10/29/2018", "Birthday", "12:00 am", "12:00 pm", "Party");
        myUnit2 = new fpEvent("10/29/2018", "Birthday", "12:00 am", "12:00 pm", "Party");
        myUnit3 = new fpEvent("10/29/2019", "Manual Code Review", "11:00 am", "2:00 pm", "Meeting");
        myUnit4 = new fpEvent("11/29/2019", "Manual Code Review", "11:00 am", "2:00 pm", "Meeting");
        myUnit5 = new fpEvent("10/30/2019", "Manual Code Review", "11:00 am", "2:00 pm", "Meeting");
    }

    @Test
    public void testgetDate() {
        String result = myUnit.getDate();
        assertEquals("10/29/2018", result);
    }

    @Test
    public void testsetDate() {
        myUnit.setDate("11/1/2018");
        String result = myUnit.getDate();
        assertEquals("11/1/2018", result);
    }

    @Test
    public void testgetDescription() {
        String result = myUnit.getDescription();
        assertEquals("Birthday", result);
    }

    @Test
    public void testsetDescription() {
        myUnit.setDescription("Manual Code Review");
        String result = myUnit.getDescription();
        assertEquals("Manual Code Review", result);
    }

    @Test
    public void testgetStartTime() {
        String result = myUnit.getStartTime();
        assertEquals("12:00 am", result);
    }

    @Test
    public void testsetStartTime() {
        myUnit.setStartTime("3:00 pm");
        String result = myUnit.getStartTime();
        assertNotEquals("2:00 pm", result);
    }

    @Test
    public void testgetEndTime() {
        String result = myUnit.getEndTime();
        assertEquals("12:00 pm", result);
    }

    @Test
    public void testsetEndTime() {
        myUnit.setEndTime("2:00 pm");
        String result = myUnit.getEndTime();
        assertEquals("2:00 pm", result);
    }

    @Test
    public void testgetName() {
        String result = myUnit.getName();
        assertEquals("Party", result);
    }

    @Test
    public void testsetName() {
        myUnit.setName("Company Meeting");
        String result = myUnit.getName();
        assertEquals("Company Meeting", result);
    }

    @Test
    public void testCompareTo() {
        int result = myUnit.compareTo(myUnit3);
        assertEquals(-1, result);
    }

    @Test
    public void test1CompareTo() {
        int result = myUnit3.compareTo(myUnit);
        assertEquals(1, result);
    }

    @Test
    public void test2CompareTo() {
        int result = myUnit.compareTo(myUnit2);
        assertEquals(0, result);
    }

    @Test
    public void test3CompareTo() {
        int result = myUnit.compareTo(myUnit4);
        assertEquals(-1, result);
    }

    @Test
    public void test4CompareTo() {
        int result = myUnit4.compareTo(myUnit);
        assertEquals(1, result);
    }

    @Test
    public void test5CompareTo() {
        int result = myUnit.compareTo(myUnit5);
        assertEquals(-1, result);
    }

    @Test
    public void test6CompareTo() {
        int result = myUnit5.compareTo(myUnit);
        assertEquals(1, result);
    }

}