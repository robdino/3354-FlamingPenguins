package com.example.robcastle.flamingcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class DailyView extends AppCompatActivity {

    private static final String TAG = "DailyView";
    private static ArrayList<fpEvent> eventList = new ArrayList();

    public void setEventList(ArrayList eventList){
        this.eventList = eventList;
    }

    public static ArrayList <fpEvent> getEventList(){
        return eventList;
    }
    private Button goToHomeButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /************* ACTIVITY START RELATED THINGS ***************/
        setContentView(R.layout.daily_view);
        goToHomeButton = (Button) findViewById(R.id.returnbutton);
        ListView mListView = (ListView) findViewById(R.id.dailyListView);



        //Daily View activity stuff
        goToHomeButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onCreate, Going to HomeScreen View");
                Intent intent1 = new Intent(DailyView.this, HomeScreen.class);
                startActivity(intent1);
            }
        });
    }







}
