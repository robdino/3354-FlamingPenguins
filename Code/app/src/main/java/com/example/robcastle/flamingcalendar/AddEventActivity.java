package com.example.robcastle.flamingcalendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.util.Log;
import android.view.View;
import android.widget.*;

 import java.util.ArrayList;
import java.util.Calendar;

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
     private DatePickerDialog.OnDateSetListener dateSetListener;

     String eventName,descriptionEvent,dateEvent,startTime,endTime;
     EditText eventNameInput;
     EditText descriptionInput;
     TextView dateInput;
     EditText startTimeInput;
     EditText endTimeInput;
     DatabaseHelper mDatabaseHelper;


     @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.add_event_screen);
         Log.d(TAG, "onCreate, Add Event");

         mDatabaseHelper = new DatabaseHelper(this);

         generateButtons();

     addEventButton.setOnClickListener(new View.OnClickListener () {
     @Override
     public void onClick(View v)
         {
             Log.d(TAG, "onCreate, Adding event");

             getEventStrings();

             ArrayList<fpEvent> eventList = WeeklyView.getEventList();

             fpEvent newEvent = new fpEvent(dateEvent, descriptionEvent, startTime, endTime, eventName);
             eventList.add(newEvent);
             mDatabaseHelper.addData(newEvent);

             Intent intent6 = new Intent(AddEventActivity.this, WeeklyView.class);
             startActivity(intent6);


         }
     });
     dateInput.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Calendar cal1 = Calendar.getInstance();
             int year = cal1.get(Calendar.YEAR);
             int month = cal1.get(Calendar.MONTH);
             int day = cal1.get(Calendar.DAY_OF_MONTH);

             DatePickerDialog dialog = new DatePickerDialog(
                     AddEventActivity.this,
                     android.R.style.Theme_Holo_Dialog_MinWidth,
                     dateSetListener,
                     year, month, day);
             dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
             dialog.show();
         }
     });

     dateSetListener = new DatePickerDialog.OnDateSetListener() {
         @Override
         public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
             month = month + 1;
             Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);
             String date = month + "/" + dayOfMonth + "/" + year;
             dateInput.setText(date);

         }
     };




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


     /*************** OTHER METHODS ******************/

     
    /**
     * @author Robbbie
     * @return void
     * This function is just to make the .setOnClickListener() function easier to read.
     * We get the inputs of each field here.
     * @since 11/15/18
     */
    void getEventStrings()
     {
         eventName = eventNameInput.getText().toString();
         descriptionEvent = descriptionInput.getText().toString();
         dateEvent = dateInput.getText().toString();
         startTime = startTimeInput.getText().toString();
         endTime = endTimeInput.getText().toString();
     }

    /**
     * @author Robbbie
     * @return void
     * This function is just to make the .onCreate() function easier to read.
     * We build our buttons here.
     * @since 11/15/18
     */
     void generateButtons()
     {
         goToHome = (Button) findViewById(R.id.btnHome_Daily);
         addEventButton = (Button) findViewById(R.id.addEventButton);

         eventNameInput = (EditText) findViewById(R.id.eventNameInput);
         descriptionInput = (EditText) findViewById(R.id.descriptionInput);
         dateInput = (TextView) findViewById(R.id.dateInput);
         startTimeInput = (EditText) findViewById(R.id.startTimeInput);
         endTimeInput = (EditText) findViewById(R.id.endTimeInput);
     }



 }

