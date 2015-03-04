package com.teamwagdin.owner.futureproofrel;

import android.os.Handler;

import java.util.Date;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TimeChecker {


    EntryDate currentTime;
    EntryDate targetTime;


    public final int CHECKDELAY = 1000;
    Handler h;
    Runnable r;
    public TimeChecker(EntryDate d1, EntryDate d2 ) {
        currentTime = d1;
        targetTime = d2;


        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                checkTheTime();
                //
                if (true) { // ??? <-- Will run indefinitely-- Needs a break!
                    h.postDelayed(r, CHECKDELAY);
                }
            }
        };
        //
        h.postDelayed(r,CHECKDELAY);
    }

    public boolean timeHasPassed(){
        return timePassed;
    }

    boolean timePassed = false;

    public void checkTheTime(){

        if(currentTime.equals(targetTime) || currentTime.After(targetTime)){
            timePassed = true;
        }else {
            timePassed = false;
        }

    }

}
