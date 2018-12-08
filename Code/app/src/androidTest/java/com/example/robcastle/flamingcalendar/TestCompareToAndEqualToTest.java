package com.example.robcastle.flamingcalendar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//Figuring out Junit testing with the FpEvents class
public class TestCompareToAndEqualToTest {
    fpEvent myUnit; // TESTING DAY: Unit, Unit2, and Unit3 will have the same month and year. Different Day.
    fpEvent myUnit2; // Day is less than myUnit's day
    fpEvent myUnit3; // Day is greater than myUnit's day

    fpEvent myUnit4; // Same Date as myUnit

    // TESTING MONTH: Unit, Unit6, Unit7 will have the same day and year. Different Month.
    fpEvent myUnit5; // myUnit's month is less than myUnit5. (Will myUnit5.compareTo(myUnit))
    fpEvent myUnit6; // myUnit6's month is greater than myUnit5. (Will myUnit5.compareTo(myUnit6))

    // TESTING YEAR
    fpEvent myUnit7; // myUnit's Year is greater. (Will myUnit.compareTo(myUnit7))
    fpEvent myUnit8; // myUnit7's Year is less. (Will myUnit.compareTo(myUnit8))

    fpEvent myUnitSame; // This will be the exact same as myUnit. This will use equalsTo.


    @Before
    public void setUp() {
        myUnit = new fpEvent("12/11/2018", "SE Final Day", "1:00 pm", "2:00 pm", "Poison", 0);
        myUnit2 = new fpEvent("12/10/2018", "After Final", "12:00 am", "12:00 pm", "Party",0 );
        myUnit3 = new fpEvent("12/12/2018", "Before Final", "12:00 am", "12:00 pm", "Suffering",0 );

        myUnit4 = new fpEvent("12/11/2018", "Final", "2:30 pm", "3:00 pm", "Another Final",0 );

        myUnit5 = new fpEvent("11/11/2018", "One month till war", "11:00 am", "2:00 pm", "Before the war",0 );
        myUnit6 = new fpEvent("10/11/2018", "Two month till freedom", "11:00 am", "2:00 pm", "Before-Before the war",0 );

        myUnit7 = new fpEvent("12/11/2017", "One year before war", "11:00 am", "2:00 pm", "After the war",0 );
        myUnit8 = new fpEvent("12/11/2019", "One year after war", "11:00 am", "2:00 pm", "After-After the war",0 );

        myUnitSame = new fpEvent("12/11/2018", "SE Final Day", "1:00 pm", "2:00 pm", "Poison",0 );
    }

    @Test
    public void testDay1CompareTo() {
        int result = myUnit.compareTo(myUnit2);
        assertEquals(1, result);
    }

    @Test
    public void testDay2CompareTo() {
        int result = myUnit.compareTo(myUnit3);
        assertEquals(-1, result);
    }

    @Test
    public void testDay3CompareTo() {
        int result = myUnit.compareTo(myUnit4);
        assertNotEquals(0, result);
    }

    @Test
    public void testMonth1CompareTo() {
        int result = myUnit5.compareTo(myUnit);
        assertEquals(-1, result);
    }

    @Test
    public void testMonth2CompareTo() {
        int result = myUnit5.compareTo(myUnit6);
        assertEquals(1, result);
    }

    @Test
    public void testYear1CompareTo() {
        int result = myUnit.compareTo(myUnit7);
        assertEquals(1, result);
    }

    @Test
    public void testYear2CompareTo() {
        int result = myUnit.compareTo(myUnit8);
        assertEquals(-1, result);
    }

    @Test
    public void testEqualsToFalse() {
        boolean result = myUnit.equalsTo(myUnit7);
        assertEquals(false, result);
    }

    @Test
    public void testEqualsToTrue() {
        boolean result = myUnit.equalsTo(myUnitSame);
        assertEquals(true, result);
    }
}