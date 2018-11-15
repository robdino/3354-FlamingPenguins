package com.example.robcastle.flamingcalendar;

import org.junit.Test;

import static org.junit.Assert.*;
//Figuring out Junit testing with the FpEvents class
public class TestFpEventTest {
    fpEvent myUnit = new fpEvent("10/29/1994", "Birthday", "12:00am", "12:00pm");

    @Test
    public void testgetDate() {
        String result = myUnit.getDate();
        assertEquals("10/29/1994", result);
    }

    @Test
    public void testgetDescription() {
        String result = myUnit.getDescription();
        assertEquals("Birthday", result);
    }



}