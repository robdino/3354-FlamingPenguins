package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;


public class WeeklyView extends AppCompatActivity
{
    private static final String TAG = "WeeklyView";
    private static ArrayList<fpEvent> eventList = new ArrayList();

    public void setEventList(ArrayList eventList){
        this.eventList = eventList;
    }

    public static ArrayList <fpEvent> getEventList(){
        Collections.sort(eventList);
        return eventList;
    }

    private Button goToHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_view);
        goToHome = (Button) findViewById(R.id.btnGoBackHome);
        Log.d(TAG, "onCreate, Weekly View");
        ListView mListView = (ListView) findViewById(R.id.listView);


        EventListAdapter adapter =  new EventListAdapter(this, R.layout.adaptor_weekly_view, getEventList());
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
