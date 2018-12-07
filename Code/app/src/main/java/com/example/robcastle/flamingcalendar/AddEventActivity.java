package com.example.robcastle.flamingcalendar;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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


    /**Constants for notification timer delay in scheduler method*/
     public static final long MIN_DELAY_BY_30 = 1200000;
     public static final long MIN_DELAY_BY_15 =  900000;
     public static final long MIN_DELAY_BY_10 =  600000;
     public static final long MIN_DELAY_BY_5  =  300000;
     public static final long MIN_DELAY_BY_1 =    60000;

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

    NotificationCompat.Builder notification;
    Random random = new Random();
    int uniqueID = random.nextInt(9999-1000) + 1000;

    /**
     * @author Robbbie, Anthony, Taylor :: Geoffrey
     * @return void
     * This function deals with the onClickListener of all the buttons and editText fields
     * of of the addEventActivity class
     * :: added setOnCheckedChangeListener/onCheckChanged function to notify user with small popup
     * :: Toast message, whether ReminderSwitch is turned on or off
     * :: Fullscreen allows notification to appear as a heads-up notification
     * @since 12/04/18 :: 12/07/18
     */

     @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         /********************************Notification Instantiation********************************/
         notification = new NotificationCompat.Builder(this, "M_CH_ID");
         notification.setAutoCancel((true));
         Intent intent=getIntent();
         boolean isFullScreen =  intent.getBooleanExtra("isFullScreen", false);

         if(isFullScreen )
         {
             requestWindowFeature(Window.FEATURE_NO_TITLE);
             getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                     WindowManager.LayoutParams.FLAG_FULLSCREEN);
         }
         setContentView(R.layout.add_event_screen);
         Log.d(TAG, "onCreate, Add Event");
         mDatabaseHelper = new DatabaseHelper(this);
         receivingInfo = false;
         generateButtons();
         Log.d(TAG,"End of onCreate>sendNotification");

         /**********************************REMINDER TOAST******************************************/
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

             final fpEvent newEvent = new fpEvent(dateEvent, descriptionEvent, startTime, endTime, eventName, reminder);
             eventList.add(newEvent);
             Context context = getApplicationContext();
             int duration = Toast.LENGTH_LONG;
             CharSequence text = "Reminder will notify in 10 seconds";
             Toast toast = Toast.makeText(context, text, duration);
             toast.show();
//             SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:mm a");
//             Date myDate = new Date();
//             try {
//                 String startTimeForReminder = newEvent.getStartTime();
//                 context = getApplicationContext();
//                 duration = Toast.LENGTH_LONG;
//                 toast = Toast.makeText(context, startTimeForReminder, duration);
//                 toast.setGravity(Gravity.TOP, 0, 0);
//                 toast.show();
//                 myDate = sdf.parse(startTimeForReminder);
//             } catch (ParseException e) {
//                 Log.d(TAG, "Error parsing String time to date time");
//             }
//             long dateTimeInLong = myDate.getTime();
//             System.out.println("date time in long equals to " + dateTimeInLong);
//             long timeDelayForReminder = dateTimeInLong - (dateTimeInLong - MIN_DELAY_BY_1);
             /******************************Notification timer delay******************************/
             ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
             scheduler.scheduleWithFixedDelay(new Runnable() {
                 @Override
                 public void run() {
                     sendNotification(newEvent.getName(), newEvent.getDescription());
                 }
             }, 10 , 10, TimeUnit.SECONDS);

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
     * :: added ReminderSwitch button, sendNotification(String eventName, String eventDesc)
     * :: createNotificationChannel() functions
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

    /** Notification method with eventName and eventDescription, requiring notification channel and uniqueID
     *  to behave a certain way. Added full screen intent to notification to pop up as a heads-up notification
     *
     * @param eventName
     * @param eventDesc
     */
     public void sendNotification(String eventName, String eventDesc) {

        notification.setSmallIcon(R.drawable.flamingpenguin);
        notification.setTicker("This is the ticker");

        notification.setWhen(System.currentTimeMillis());
        notification.setShowWhen(true);
        notification.setContentTitle(eventName);
        notification.setContentText(eventDesc);

        Intent intent = new Intent(this, HomeScreen.class);
        intent.putExtra("isFullScreen", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        final NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        createNotificationChannel();
        System.out.println("Notifying User...");
        nmc.notify(uniqueID, notification.build());

        /**Without this method, the notification would be repeating every so often*/
        Handler h = new Handler();
        long delayInMilliseconds = 5000;
        h.postDelayed(new Runnable() {
            public void run() {
                    nmc.cancel(uniqueID);
                }
            }, 5000);
    }
     /**A notification requires a channel to modify how a notification behaves*/
     public void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "M_CH_ID";//getString(R.string.channel_name);
            String description = "M_CH_ID";//getString(R.string.channel_description);

            /**IMPORTANCE_HIGH sets notification as a heads-up notification*/
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("M_CH_ID", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}




