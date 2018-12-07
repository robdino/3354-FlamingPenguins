package com.example.robcastle.flamingcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.nio.charset.Charset;
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
     private boolean receivingInfo;

     String eventName,descriptionEvent,dateEvent,startTime,endTime;
     int reminder;
     EditText eventNameInput;
     EditText descriptionInput;
     Button dateInput;
     Button startTimeInput;
     Button endTimeInput;
     DatabaseHelper mDatabaseHelper;
     Switch ReminderSwitch;
    ArrayList<fpEvent> item;

    /**
     * @author Robbbie, Anthony, Taylor :: Geoffrey
     * @return void
     * This function deals with the onClickListener of all the buttons and editText fields
     * of of the addEventActivity class
     * :: added setOnCheckedChangeListener/onCheckChanged function to notify user with small popup
     * :: Toast message, whether ReminderSwitch is turned on or off
     * @since 12/04/18 :: 12/07/18
     */

     @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.add_event_screen);
         Log.d(TAG, "onCreate, Add Event");
         mDatabaseHelper = new DatabaseHelper(this);
         receivingInfo = false;
         generateButtons();
         if (ReminderSwitch != null) {
             ReminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     Context context = getApplicationContext();
                     CharSequence text;
                     int duration = Toast.LENGTH_SHORT;
                     if(isChecked) {
                         //do stuff when Switch is ON
                         reminder = 1;
                         text = "Reminder ON";
                         Toast toast = Toast.makeText(context, text, duration);
                         toast.show();
                     } else {
                         //do stuff when Switch if OFF
                         reminder = 0;
                         text = "Reminder OFF";
                         Toast toast = Toast.makeText(context, text, duration);
                         toast.show();
                     }
                 }
             });
         }
         Intent incomingIntent1 = getIntent();
         receivingInfo = incomingIntent1.getBooleanExtra("gettingInfo", receivingInfo);

         if(receivingInfo)
         {
             String name, date, desc;
             name = incomingIntent1.getStringExtra("name");
             date = incomingIntent1.getStringExtra("date");
             desc = incomingIntent1.getStringExtra("desc");

             //we will pull info from DB
             item = mDatabaseHelper.getDailyData(date, desc, name);

             dateInput.setText(item.get(0).getDate());
             dateEvent = item.get(0).getDate();

             eventNameInput.setText(item.get(0).getName());
             eventName = item.get(0).getName();

             descriptionInput.setText(item.get(0).getDescription());
             descriptionEvent = item.get(0).getDescription();

             startTimeInput.setText(item.get(0).getStartTime());
             startTime = item.get(0).getStartTime();

             endTimeInput.setText(item.get(0).getEndTime());
             endTime = item.get(0).getEndTime();

             ReminderSwitch.setChecked(item.get(0).getBoolReminder());
             reminder = item.get(0).getReminder();
         }

     addEventButton.setOnClickListener(new View.OnClickListener () {
     @Override
     public void onClick(View v)
         {
             Log.d(TAG, "onCreate, Adding event");

             if(receivingInfo) {
                 mDatabaseHelper.deleteRecord(item.get(0).getIDnum());
                 receivingInfo = false;
             }

             getEventStrings();

             ArrayList<fpEvent> eventList = WeeklyView.getEventList();

             fpEvent newEvent = new fpEvent(dateEvent, descriptionEvent, startTime, endTime, eventName, reminder);

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

         startTimeInput.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar mcurrentTime = Calendar.getInstance();
                 int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                 int minute = mcurrentTime.get(Calendar.MINUTE);

                 TimePickerDialog mTimePicker;
                 mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                         int hour = selectedHour % 12;
                         startTimeInput.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                                 selectedMinute, selectedHour < 12 ? "am" : "pm"));
                     }
                 }, hour, minute, false);//Yes 24 hour time
                 mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 mTimePicker.show();
             }
         });

         endTimeInput.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Calendar mcurrentTime = Calendar.getInstance();
                 int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                 int minute = mcurrentTime.get(Calendar.MINUTE);

                 TimePickerDialog mTimePicker;
                 mTimePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                         int hour = selectedHour % 12;
                         endTimeInput.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour,
                                 selectedMinute, selectedHour < 12 ? "am" : "pm"));
                     }
                 }, hour, minute, false);//Yes 24 hour time
                 mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 mTimePicker.show();
             }
         });




     goToHome.setOnClickListener(new View.OnClickListener () {
         @Override
         public void onClick(View v)
         {
             receivingInfo = false;
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
     * @author Robbbie :: Geoffrey
     * @return void
     * This function is just to make the .onCreate() function easier to read.
     * We build our buttons here
     * :: added ReminderSwitch button
     * @since 11/15/18 :: 12/07/18
     */
     void generateButtons()
     {
         goToHome = (Button) findViewById(R.id.btnHome_Daily);
         addEventButton = (Button) findViewById(R.id.addEventButton);

         eventNameInput = (EditText) findViewById(R.id.eventNameInput);
         descriptionInput = (EditText) findViewById(R.id.descriptionInput);
         dateInput = (Button) findViewById(R.id.dateInput);
         startTimeInput = (Button) findViewById(R.id.startTimeInput);
         endTimeInput = (Button) findViewById(R.id.endTimeInput);
         ReminderSwitch = (Switch) findViewById(R.id.ReminderSwitch);
     }





 }

