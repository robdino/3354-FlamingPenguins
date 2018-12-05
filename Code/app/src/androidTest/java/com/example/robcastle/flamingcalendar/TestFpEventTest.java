package com.example.robcastle.flamingcalendar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//Figuring out Junit testing with the FpEvents class
public class TestFpEventTest {
    fpEvent myUnit;
    @Before
    public void setUp() {
        myUnit = new fpEvent("10/29/2018", "Birthday", "12:00am", "12:00pm", "Party");
    }
    @Test
    public void testgetDate() {
        String result = myUnit.getDate();
        assertEquals("10/29/1994", result);
    }
    @Test
    public void testSetDate() {
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
        assertEquals("12:00am", result);
    }
    @Test
    public void testsetStartTime() {
        myUnit.setStartTime("3:00pm");
        String result = myUnit.getStartTime();
        assertEquals("2:00pm", result);
    }

    @Test
    public void testgetEndTime() {
        String result = myUnit.getEndTime();
        assertEquals("12:00pm", result);
    }
    @Test
    public void testsetEndTime() {
        myUnit.setEndTime("2:00pm");
        String result = myUnit.getEndTime();
        assertEquals("2:00pm", result);
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






}