package com.example.robcastle.flamingcalendar;

import android.app.AlarmManager;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.os.Bundle;;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
    private static final String ACTION_NOTIFY =
            "com.example.android.standup.ACTION_NOTIFY";
     private static final String TAG = "AddEventActivity";
     private Button goToHome;
     private Button addEventButton;
     private DatePickerDialog.OnDateSetListener dateSetListener;

     String eventName,descriptionEvent,dateEvent,startTime,endTime;
     boolean reminder = false;
     EditText eventNameInput;
     EditText descriptionInput;
     Button dateInput;
     Button startTimeInput;
     Button endTimeInput;
     Switch addReminder;
     DatabaseHelper mDatabaseHelper;
     NotificationCompat.Builder notification;
     Random random = new Random();
     int uniqueID = random.nextInt(9999-1000) + 1000;
     //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

     @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.add_event_screen);
         Log.d(TAG, "onCreate, Add Event");

         mDatabaseHelper = new DatabaseHelper(this);
         notification = new NotificationCompat.Builder(this, "M_CH_ID");
         notification.setAutoCancel((true));
//         Intent notifyIntent = new Intent(ACTION_NOTIFY);
//         final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, uniqueID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

         generateButtons();

     addEventButton.setOnClickListener(new View.OnClickListener () {
     @Override
     public void onClick(View v)
         {
             Log.d(TAG, "onCreate, Adding event");

             getEventStrings();

             ArrayList<fpEvent> eventList = WeeklyView.getEventList();

             fpEvent newEvent = new fpEvent(dateEvent, descriptionEvent, startTime, endTime, eventName, reminder);

             eventList.add(newEvent);
             if(reminder == true) {

                 btnGoToNotificationClicked(newEvent.getName(), newEvent.getDescription());
                 Context context = getApplicationContext();
                 CharSequence text = "Reminder Created";
                 int duration = Toast.LENGTH_SHORT;

                 Toast toast = Toast.makeText(context, text, duration);
                 toast.show();
             }
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
                         startTimeInput.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
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
                         endTimeInput.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                     }
                 }, hour, minute, false);//Yes 24 hour time
                 mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 mTimePicker.show();
             }
         });



         addReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton cb, boolean on) {
                 //Log.d(TAG, "onCreate, Reminder Switch Changed");
                 if (on) {
//                     long triggerTime = SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES;
//                     long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
//                     alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
                     Context context = getApplicationContext();
                     CharSequence text = "Reminder ON";
                     int duration = Toast.LENGTH_SHORT;

                     Toast toast = Toast.makeText(context, text, duration);
                     toast.show();
                 }
                 else {
//                     alarmManager.cancel(notifyPendingIntent);
                     Context context = getApplicationContext();
                     CharSequence text = "Reminder OFF";
                     int duration = Toast.LENGTH_SHORT;

                     Toast toast = Toast.makeText(context, text, duration);
                     toast.show();
                 }
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
         reminder = addReminder.isChecked();
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
         dateInput = (Button) findViewById(R.id.dateInput);
         startTimeInput = (Button) findViewById(R.id.startTimeInput);
         endTimeInput = (Button) findViewById(R.id.endTimeInput);
         addReminder = (Switch) findViewById(R.id.addReminder);
     }

    public void btnGoToNotificationClicked(/*View view,*/ String eventName, String eventDesc) {
        notification.setSmallIcon(R.drawable.flamingpenguin);
        notification.setTicker("This is the ticker");

        notification.setWhen(System.currentTimeMillis());
        notification.setShowWhen(true);
        notification.setContentTitle(eventName);
        notification.setContentText(eventDesc);
        //notification.build();
        Intent intent = new Intent(this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
//        long delay = 10000;
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        createNotificationChannel();
        System.out.println("Notifying User...");
        nmc.notify(uniqueID, notification.build());
    }

    public void createNotificationChannel(){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "M_CH_ID";//getString(R.string.channel_name);
            String description = "M_CH_ID";//getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("M_CH_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

 }

