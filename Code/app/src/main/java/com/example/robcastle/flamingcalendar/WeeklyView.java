package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private DatabaseHelper mDatabaseHelper;

    private Button goToHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /************* ACTIVITY START RELATED THINGS ***************/
        setContentView(R.layout.weekly_view);
        goToHome = (Button) findViewById(R.id.btnHome_Weekly);
        final ListView mListView = (ListView) findViewById(R.id.listView);

        /************* DATABASE RELATED THINGS ***************/
        mDatabaseHelper = new DatabaseHelper(this);
        Log.d(TAG, "WeeklyView onCreate: before loadWeeklyEventList");
        setEventList( mDatabaseHelper.loadWeeklyEventList() );    //get events from SQL database file
        Log.d(TAG, "WeeklyView onCreate: after loadWeeklyEventList");

        /************* WEEKLYVIEW ACTIVITY RELATED THINGS ***************/
        EventListAdapter adapter =  new EventListAdapter(this, R.layout.adaptor_weekly_view, getEventList());
        mListView.setAdapter(adapter);

        /************ BUTTON CLICKS **********************/

        /**
         * @author Robbie
         * @since 11/24/18
         * This is for when a card in WeeklyView gets clicked
         * We open the data in DailyView by passing the SQLite ID tag to DatabaseHelper in DailyView.class
         */
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = mListView.getItemAtPosition(position);
                fpEvent str = (fpEvent) o; //To get our data
                Intent intent5 = new Intent (WeeklyView.this, DailyView.class);

                intent5.putExtra("extraInfo", true);
                intent5.putExtra("specificID", str.getIDnum());

                startActivity(intent5);
            }
        });

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
