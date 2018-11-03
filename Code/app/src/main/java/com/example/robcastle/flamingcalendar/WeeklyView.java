package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;


public class WeeklyView extends AppCompatActivity
{
    private static final String TAG = "WeeklyView";

    private Button goToHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_view);
        goToHome = (Button) findViewById(R.id.btnGoBackHome);
        Log.d(TAG, "onCreate, Weekly View");
        ListView mListView = (ListView) findViewById(R.id.listView);

        //create two test cases
        fpEvent one = new fpEvent("10/31/18", "halloween", "12:00AM", "11:59PM");
        fpEvent two = new fpEvent("11/3/18", "collect the infinity stones", "9:00AM", "12:00PM");


        ArrayList<fpEvent> eventList = new ArrayList<>();
        eventList.add(one);
        eventList.add(two);

        EventListAdapter adapter =  new EventListAdapter(this, R.layout.adaptor_weekly_view, eventList);
        mListView.setAdapter(adapter);

        goToHome.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onCreate, Going to HomeScreen View");
                Intent intent4 = new Intent(WeeklyView.this, HomeScreen.class);
                startActivity(intent4);
            }
        });
    }
}
