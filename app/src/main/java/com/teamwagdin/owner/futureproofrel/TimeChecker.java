package com.teamwagdin.owner.futureproofrel;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TimeChecker {


    FutureProof myApplication;
    EntryDate targetTime;

    public TimeChecker(EntryDate ed) {
        targetTime = ed;
    }

    public boolean hasTimePassed(){
        return timePassed;
    }

    boolean timePassed = false;

    public boolean checkTheTime(){
        EntryDate currentTime = FutureProof.getPresentDateTime();

        if(currentTime.equals(targetTime) || currentTime.After(targetTime)){
            timePassed = true;
        }else {
            timePassed = false;
        }

        return hasTimePassed();
    }

}
