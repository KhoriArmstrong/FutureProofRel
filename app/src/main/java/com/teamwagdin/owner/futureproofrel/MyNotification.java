package com.teamwagdin.owner.futureproofrel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class MyNotification {

    public MyNotification(Context context, Intent intent) {

        c = context;
        i = intent;
        m = "Hello";
    }

    Context c;
    Intent i;
    public String m;


    public boolean hasDisplay() {

        return hasdisplayed;
    }

    boolean hasdisplayed = false;

    public void Display() {


        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) c.getSystemService(ns);

        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = m;
        long when = System.currentTimeMillis();
        Context context = c.getApplicationContext();
        CharSequence contentTitle = "FutureProof Message!";
        CharSequence contentText = m;
        Intent notificationIntent = i; // new Intent(this, Example.class);
        PendingIntent contentIntent = PendingIntent.getActivity(c, 0, notificationIntent, 0);
        Notification notification = new Notification(icon, tickerText, when);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

// and this
        final int HELLO_ID = 1;
        mNotificationManager.notify(HELLO_ID, notification);


        hasdisplayed = true;

    }

}
