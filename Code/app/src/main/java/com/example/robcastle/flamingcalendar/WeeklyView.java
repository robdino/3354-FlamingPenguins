package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.database.Cursor;
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
//        Collections.sort(eventList);
        return eventList;
    }

    private DatabaseHelper mDatabaseHelper;


    private Button goToHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_view);
        goToHome = (Button) findViewById(R.id.btnGoBackHome);
        ListView mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        Log.d(TAG, "WeeklyView onCreate: before loadEventList");
        loadEventList();    //get events from SQL database file
        Log.d(TAG, "WeeklyView onCreate: after loadEventList");

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

    /**
     * customizable toast (pop up message)
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void loadEventList()
    {
        eventList.clear();


        Cursor data = mDatabaseHelper.getData();    //get all the data

        int limit = data.getCount();
        Log.d(TAG, "WeeklyView loadEventList: limit: " + limit);

        while( data.moveToNext() )
        {
            fpEvent newEvent = new fpEvent(
                data.getString(0), //DATE
                data.getString(1), //DESCRIPTION
                data.getString(2), //START TIME
                data.getString(3)  //END TIME
            );

            Log.d(TAG, "loadEventList:" +
                    " Date: "           + newEvent.getDate() +
                    " Description: "    + newEvent.getDescription() +
                    " Start Time: "     + newEvent.getStartTime() +
                    " End Time: "       + newEvent.getEndTime()
            );

            eventList.add(newEvent);
        }

        data.close();
    }
}
