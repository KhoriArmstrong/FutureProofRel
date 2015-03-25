package com.teamwagdin.owner.futureproofrel;

import android.os.Handler;

import java.util.Date;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TimeChecker {


    SomeApplication myApplication;
    EntryDate targetTime;

    public TimeChecker(EntryDate ed) {
        targetTime = ed;
    }

    public boolean hasTimePassed(){
        return timePassed;
    }

    boolean timePassed = false;

    public boolean checkTheTime(){
        EntryDate currentTime = SomeApplication.getPresentDateTime();

        if(currentTime.equals(targetTime) || currentTime.After(targetTime)){
            timePassed = true;
        }else {
            timePassed = false;
        }

        return hasTimePassed();
    }

}
