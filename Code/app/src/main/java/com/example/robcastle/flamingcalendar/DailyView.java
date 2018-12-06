package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyView extends AppCompatActivity {

    private static final String TAG = "DailyView";

    private static ArrayList<fpEvent> eventList = new ArrayList();

    public void setEventList(ArrayList<fpEvent> eventList){
        this.eventList = eventList;
    }
    public static ArrayList <fpEvent> getEventList(){
        return eventList;
    }

    private Button goToHomeButton;
    private TextView datePlate;
    private RecyclerView rv;
    private DatabaseHelper dailyData;
    private String dateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /************* ACTIVITY START RELATED THINGS ***************/
        setContentView(R.layout.daily_view);
        goToHomeButton = (Button) findViewById(R.id.btnHome_Daily);
        datePlate = (TextView) findViewById(R.id.dateHome3);

        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        /************* DATABASE RELATED THINGS ***************/
        dailyData =  new DatabaseHelper(this);
        Log.d(TAG, "DailyView onCreate: before getDailyData");
        setEventList(loadDailyData());
        Log.d(TAG, "DailyView onCreate: after getDailyData");

        DailyViewAdapter adapter = new DailyViewAdapter(getEventList());
        rv.setAdapter(adapter);

        /************* DAILY VIEW ACTIVITY RELATED THINGS ***************/

        goToHomeButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "HomeButton, Going to HomeScreen View");
                Intent intent1 = new Intent(DailyView.this, HomeScreen.class);
                startActivity(intent1);
            }
        });

    }

    private ArrayList<fpEvent> loadDailyData()
    {
        ArrayList<fpEvent> dailyEvents;
        boolean incIDnum = false;
        Intent incomingIntent1 = getIntent();
        incIDnum = incomingIntent1.getBooleanExtra("extraInfo", incIDnum);

        //if we're just getting today's data
        if(!incIDnum) {
            //we get the current date
            Date date = new Date();
            String DATE_FORMAT = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            String theDate = sdf.format(date);
            if(theDate.indexOf("0") >= 0) {
                theDate = theDate.substring(0, 3) + theDate.substring(4);
            }

            Log.d(TAG, "loadDailyData, Going to getDailyData, formatted date is: " + theDate);
            dateFormat = theDate;
            dailyEvents = dailyData.getDailyData(theDate);
        }
        else {
            String givenDate = "";
            givenDate = incomingIntent1.getStringExtra("date");
            Log.d(TAG, "loadDailyData, Going to getDailyData, given date is: " + givenDate);
            dateFormat = givenDate;
            dailyEvents = dailyData.getDailyData(givenDate);
        }

        datePlate.setText(dateFormat);

        return dailyEvents;
    }

}
