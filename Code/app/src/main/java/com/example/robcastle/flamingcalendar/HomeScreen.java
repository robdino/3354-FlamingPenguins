package com.example.robcastle.flamingcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;


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
    private Button btnGoToMonthly;
    private Button btnGoToWeek;


    //create base app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //generate button for calendar and date text
        date = (TextView) findViewById(R.id.date);
        btnGoToMonthly = (Button) findViewById(R.id.btnGoToMonthly);
        btnGoToWeek = (Button) findViewById(R.id.btnGoToWeek);

        //Get info from calendar button click & display the date clicked
        Intent incomingIntent1 = getIntent();
        String incDate1 = incomingIntent1.getStringExtra("date");
        date.setText(incDate1);

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
    }

}
