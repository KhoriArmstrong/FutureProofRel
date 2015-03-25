package com.teamwagdin.owner.futureproofrel;

import android.widget.EditText;

/**
 * Created by yangjiachang on 15-02-11.
 */
public class EntryAlarm {
    public Entry theEntry;
    public TimeChecker theChecker;
    public MyNotification theNotification;
    public EntryAlarm(Entry thisEntry) {
        theEntry = thisEntry;
        //
        theChecker = new TimeChecker(theEntry.targetDate);
        theNotification = new MyNotification(theEntry.message);
        //
    }
}
