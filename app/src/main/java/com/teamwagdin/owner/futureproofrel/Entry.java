package com.teamwagdin.owner.futureproofrel;

/**
 * Created by Owner on 29/01/2015.
 */
public class Entry {
    public String message;
    public EntryDate createDate;
    public EntryDate targetDate;



    public Entry() {
        message = "";
        createDate = FutureProof.getPresentDateTime();
        targetDate = FutureProof.getPresentDateTime();
    }
    public Entry(EntryDate thisTarget, String thisMessage) {
        this();
        //
        message = thisMessage;
        targetDate = thisTarget;
    }

    /*
    public Entry(String thisMessage, new Date()) {
        Date Q = new Date(System.currentTimeMillis());
    }
    */


    @Override
    public String toString() {
        return message+" | "+targetDate;
    }
}
