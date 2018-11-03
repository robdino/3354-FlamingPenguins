package com.example.robcastle.flamingcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.content.*;

/**
 * This class generates the Calendar
 */
public class CalendarActivity extends AppCompatActivity
{
    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;

    /**
     * NOTE: this creates the calendar activity itself
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);


        /**
         * NOTE: This is for when we select a new date in the calendar (the pink highlight)
         *       We go back to our main page (HomeScreen) to show the date and any pertinent info.
         */

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Intent intent2 = new Intent(CalendarActivity.this, HomeScreen.class);
                intent2.putExtra("date", date);
                startActivity(intent2);
            }
        });


    }
}
