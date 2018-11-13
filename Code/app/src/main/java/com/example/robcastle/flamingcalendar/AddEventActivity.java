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

/**
 *@author Anthony Huynh
 *@since 11/08/18
 * This AddEventActivity class generates events for the application.
 * It gets the event's name, description, date, start time, and end time inputs from the users.
 * The Add Event button will create an event and display it in WeeklyView listed layout.
 * The return to home button with send user back to the HomeScreen layout.
 *
 */
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
     DatabaseHelper mDatabaseHelper;


     @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.add_event_screen);
         Log.d(TAG, "onCreate, Add Event");

         mDatabaseHelper = new DatabaseHelper(this);

         goToHome = (Button) findViewById(R.id.btnGoBackHome);
         addEventButton = (Button) findViewById(R.id.addEventButton);

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
             eventName = eventNameInput.getText().toString();
             descriptionEvent = descriptionInput.getText().toString();
             dateEvent = dateInput.getText().toString();
             startTime = startTimeInput.getText().toString();
             endTime = endTimeInput.getText().toString();

             ArrayList<fpEvent> eventList = WeeklyView.getEventList();
             if (eventName == null || eventName == "" || eventName.equals("")) {
                 fpEvent newEvent = new fpEvent(dateEvent, "Description: " + descriptionEvent, startTime, endTime, eventName);
                 eventList.add(newEvent);
                 mDatabaseHelper.addData(newEvent);
             }
             else{
                 fpEvent newEvent = new fpEvent(dateEvent, descriptionEvent, startTime, endTime, "");
                 eventList.add(newEvent);
                 mDatabaseHelper.addData(newEvent);
             }

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

