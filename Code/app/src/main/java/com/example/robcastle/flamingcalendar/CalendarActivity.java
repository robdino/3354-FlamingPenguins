package com.example.robcastle.flamingcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.content.*;

/**
 * This class generates the Calendar
 */
public class CalendarActivity extends AppCompatActivity
{
    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private Button goToDate;
    private Button rtnHome;
    private String currDate;

    /**
     * NOTE: this creates the calendar activity itself
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        goToDate = (Button) findViewById(R.id.goToDate);
        rtnHome = (Button) findViewById(R.id.rtnToHome);


        /**
         * This is for when a date is pushed on calendar. We get the new string of the date.
         */
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                currDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                currDate = "Events For " + currDate;
                goToDate.setText(currDate);
            }
        });


        /**
         * If user wants to see events for specific day, then go to daily view for that day
         */
        goToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button pushed goToDate, Going to WeeklyView View");
                Intent intent3 = new Intent(CalendarActivity.this, DailyView.class);
                intent3.putExtra("dateForDaily", currDate);
                startActivity(intent3);
            }
        });


        rtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button pushed rtnHome, Going to HomeScreen View");
                Intent intent3 = new Intent(CalendarActivity.this, DailyView.class);
                startActivity(intent3);
            }
        });

    }
}
