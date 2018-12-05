package com.example.robcastle.flamingcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.content.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        currDate = sdf.format(new Date(mCalendarView.getDate()));
        //i do this to get rid of 0 in, for example, "12/03/18"
        //the dates we used are formatted "12/3/18"
        if(currDate.indexOf("0") >= 0) {
            currDate = currDate.substring(0, 3) + currDate.substring(4);
        }
        goToDate.setText( "Events For " + currDate);

        /**
         * This is for when a date is pushed on calendar. We get the new string of the date.
         */
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
            {
                currDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                goToDate.setText("Events For " + currDate);
            }
        });


        /**
         * If user wants to see events for specific day, then go to daily view for that day
         */
        goToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button pushed goToDate, Going to WeeklyView View " + currDate);
                Intent intent4 = new Intent(CalendarActivity.this, DailyView.class);
                intent4.putExtra("extraInfo", true);
                intent4.putExtra("specificID", -1);
                intent4.putExtra("date", currDate);
                startActivity(intent4);
            }
        });

        rtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button pushed rtnHome, Going to HomeScreen View");
                Intent intent3 = new Intent(CalendarActivity.this, HomeScreen.class);
                startActivity(intent3);
            }
        });

    }
}
