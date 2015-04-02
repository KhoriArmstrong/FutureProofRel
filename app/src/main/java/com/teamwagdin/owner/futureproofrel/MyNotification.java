package com.teamwagdin.owner.futureproofrel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import java.text.ParseException;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class MyNotification {

    public MyNotification() {
        m = "Hello";
    }
    public MyNotification(String thisMessage) {
        m = thisMessage;
    }

    public String m;


    public boolean hasDisplay() {

        return hasdisplayed;
    }

    boolean hasdisplayed = false;

    public void Display(Context c, Intent i) {


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

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmSound == null){
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if(alarmSound == null){
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }
        notification.sound = alarmSound;



        notification.defaults = Notification.DEFAULT_LIGHTS;
// and this
        final int HELLO_ID = 1;
        mNotificationManager.notify(HELLO_ID, notification);


        hasdisplayed = true;

    }

}
