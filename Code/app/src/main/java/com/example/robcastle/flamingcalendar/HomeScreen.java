package com.example.robcastle.flamingcalendar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;
import java.util.Calendar;


/**
*@author Robbie Castillo
*@since 10/30/18
* This HomeScreen class generates the default page for the application.
* First it creates the home screen.
* If the "Go to Calendar" button is clicked, it goes to a CalendarView
* The user can select a date, and the app will return to this default screen.
* This time however, the default screen will display the date selected (ex. 10/31/18)
*
 */
public class HomeScreen extends AppCompatActivity  {

    private static final String TAG = "MainActivity";
    public TextView date;
    public TextView greetingHome;
    private Button btnGoToMonthly;
    private Button btnGoToWeek;
    private Button btnGoToAddEvent;
    private Button btnGoToDaily;

    //create base app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        generateButtons();

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        //Get info from calendar button click & display the date clicked
        Intent incomingIntent1 = getIntent();
        String incDate1 = incomingIntent1.getStringExtra("date");
        date.setText(incDate1);


        //Change the home screen greeting depending on the time of day.
        if (hour >= 0 && hour < 12){
            greetingHome.setText("Good Morning");
        }
        else if(hour >= 12 && hour < 16){
            greetingHome.setText("Good Afternoon");
        }
        else if(hour >= 16 && hour < 21){
            greetingHome.setText("Good Evening");
        }
        else if(hour >= 21 && hour < 24){
            greetingHome.setText("Good Night");
        }

        //handles the "Go To Calendar" button
        btnGoToMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCreate, Going to CalendarActivity View");
                Intent intent1 = new Intent(HomeScreen.this, CalendarActivity.class);
                startActivity(intent1);
            }
        });

        //handles the "Go To Week" button
          btnGoToWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCreate, Going to WeeklyView View");
                Intent intent3 = new Intent(HomeScreen.this, WeeklyView.class);
                startActivity(intent3);
            }
        });

        //handles the "Go To Add Event" button
        btnGoToAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCreate, Going to Add Events");
                Intent intent14 = new Intent(HomeScreen.this, AddEventActivity.class); //Testing by Anthony
                startActivity(intent14);
            }
        });

        //handles the "Go To Daily View" button
        btnGoToDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCreate, Going to Daily View");
                Intent intent15 = new Intent(HomeScreen.this, DailyView.class); //Testing by Anthony
                startActivity(intent15);
            }
        });
    }

    /**
     * @author Robbie Castillo
     * @since 11/15/18
     * Refactored these similar lines of code into a simple method
     * (Improve readability).
     */
    void generateButtons()
    {
        //generate button for calendar and date text

        date = (TextView) findViewById(R.id.date);
        greetingHome = (TextView) findViewById(R.id.greetingHome);
        btnGoToMonthly = (Button) findViewById(R.id.btnGoToMonthly);
        btnGoToWeek = (Button) findViewById(R.id.btnGoToWeek);
        btnGoToAddEvent = (Button) findViewById(R.id.btnGoToAddEvent);
        btnGoToDaily = (Button) findViewById(R.id.btnGoToDaily);
    }

}
