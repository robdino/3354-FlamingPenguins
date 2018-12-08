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
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the getDate() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testgetDate() {
        String result = myUnit.getDate();
        assertEquals("10/29/2018", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the setDate() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testsetDate() {
        myUnit.setDate("11/1/2018");
        String result = myUnit.getDate();
        assertEquals("11/1/2018", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the getDescription() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testgetDescription() {
        String result = myUnit.getDescription();
        assertEquals("Birthday", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the setDescription() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testsetDescription() {
        myUnit.setDescription("Manual Code Review");
        String result = myUnit.getDescription();
        assertEquals("Manual Code Review", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the getStartTime() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testgetStartTime() {
        String result = myUnit.getStartTime();
        assertEquals("12:00 am", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the setStartTime() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testsetStartTime() {
        myUnit.setStartTime("3:00 pm");
        String result = myUnit.getStartTime();
        assertNotEquals("2:00 pm", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the getEndTime() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testgetEndTime() {
        String result = myUnit.getEndTime();
        assertEquals("12:00 pm", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the setTime() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testsetEndTime() {
        myUnit.setEndTime("2:00 pm");
        String result = myUnit.getEndTime();
        assertEquals("2:00 pm", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the getName() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testgetName() {
        String result = myUnit.getName();
        assertEquals("Party", result);
    }
    /**
     * @author Taylor Kettle
     * @return void
     * This function tests the setName() method
     * of the fpEvent class.
     * @since 12/7/18
     */
    @Test
    public void testsetName() {
        myUnit.setName("Company Meeting");
        String result = myUnit.getName();
        assertEquals("Company Meeting", result);
    }
/*
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
*/

}