package com.example.robcastle.flamingcalendar;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**A JUnit test class to test the class DailyViewAdapter
 *   for method, getItemCount().
 */
public class DailyViewAdapterTestJUnit {

    private DailyViewAdapter tester;
    private static ArrayList<fpEvent> testEventList;
    private fpEvent testFpEventA, testFpEventB, testFpEventC;

    @Before /**Setup method executed before each test*/
    public void setup() {
        testEventList = new ArrayList<>();
        tester = new DailyViewAdapter(testEventList);
        testFpEventA = new fpEvent("12/11/2018", "he was number one", "2:00pm", "4:00pm", "Smitty Webenjagermangensen memorial");
        testFpEventB = new fpEvent("12/20/2018", "plays the xylophone", "7:00pm", "10:00pm", "Patrick Moore concert");
        testFpEventC = new fpEvent("12/25/2018", "Badger Badger mushroom", "12:00am", "2:00am", "Mushrooms Picking");
    }

    /**JUnit test methods to test GetItemCount().*/
    @Test
    public void testOneGetItemCount() {
        testEventList.add(testFpEventA);
        assertEquals("test item count is 1", 1, tester.getItemCount());
    }
    @Test
    public void testTwoGetItemCount() {
        testEventList.add(testFpEventA);
        testEventList.remove(testFpEventA);
        assertEquals("test item count is 0", 0, tester.getItemCount());
    }
    @Test
    public void testThreeGetItemCount() {
        testEventList.add(testFpEventA);
        testEventList.add(testFpEventB);
        testEventList.remove(testFpEventA);
        assertEquals("test item count is 1", 1, tester.getItemCount());
    }
    @Test
    public void testFourGetItemCount() {
        testEventList.add(testFpEventA);
        testEventList.add(testFpEventB);
        testEventList.add(testFpEventC);
        assertEquals("test item count is 3", 3, tester.getItemCount());
    }
    @Test
    public void testFiveGetItemCount() {
        assertEquals("test item count is 0", 0, tester.getItemCount());
    }
}
