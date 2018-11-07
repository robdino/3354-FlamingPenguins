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


 public class AddEventActivity extends AppCompatActivity {
     private static final String TAG = "AddEventActivity";
     private Button goToHome;
     private Button addEventButton;

     String eventName,descriptionEvent,dateEvent,startTime,endTime;
     EditText eventNameInput;
     EditText descriptionInput;
     EditText dateInput;
     EditText startTimeInput;
     EditText endTimeInput;


     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.add_event_screen);

     goToHome = (Button) findViewById(R.id.btnGoBackHome);
     addEventButton = (Button) findViewById(R.id.addEventButton);

     Log.d(TAG, "onCreate, Add Event");

     eventNameInput = (EditText) findViewById(R.id.eventNameInput);
     descriptionInput = (EditText) findViewById(R.id.descriptionInput);
     dateInput = (EditText) findViewById(R.id.dateInput);
     startTimeInput = (EditText) findViewById(R.id.startTimeInput);
     endTimeInput = (EditText) findViewById(R.id.endTimeInput);


     addEventButton.setOnClickListener(new View.OnClickListener () {
     @Override
     public void onClick(View v)
         {
             Log.d(TAG, "onCreate, Adding event");

             fpEvent newEvent = new fpEvent("dateInput.getText().toString()", "descriptionInput.getText().toString()",
             "startTimeInput.getText().toString()", "endTimeInput.getText().toString()");

             ArrayList<fpEvent> eventList = new ArrayList<>();
             eventList.add(newEvent);

             Intent intent6 = new Intent(AddEventActivity.this, WeeklyView.class);
             startActivity(intent6);
         }
     });


     goToHome.setOnClickListener(new View.OnClickListener () {
         @Override
         public void onClick(View v)
         {
             Log.d(TAG, "onCreate, Going to HomeScreen View");
             Intent intent4 = new Intent(AddEventActivity.this, HomeScreen.class);
             startActivity(intent4);
         }
     });
     }
 }
