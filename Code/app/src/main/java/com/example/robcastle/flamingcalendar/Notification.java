package com.example.robcastle.flamingcalendar;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notification extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 78946;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        notification = new NotificationCompat.Builder(this, "M_CH_ID");
        notification.setAutoCancel((true));

        btnGoToNotificationClicked(getWindow().getDecorView().getRootView());

        System.out.println("End of onCreate>btnGoToNotificationClicked");
    }

    public void btnGoToNotificationClicked(View view) {
        notification.setSmallIcon(R.drawable.fp);
        notification.setTicker("This is the ticker");
        notification.setShowWhen(true);
        notification.setWhen(System.currentTimeMillis());

        notification.setContentTitle("Here is the title");
        notification.setContentText("I am the body text of your notification");

        Intent intent = new Intent(this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

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
