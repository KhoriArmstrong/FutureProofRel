package com.teamwagdin.owner.futureproofrel;

/**
 * Created by Owner on 29/01/2015.
 */
public class Entry {
    public final int id;
    public String message;
    public EntryDate createDate;
    public EntryDate targetDate;



    // ??? <-- This IS NOT a permanent solution! This is only to rectify some current problems until Parse is running...
    private static int _pollNum = 0;
    private static int poll() {
        int thisNum = _pollNum;
        //
        _pollNum++;
        //
        return thisNum;
    }

    public Entry() {
        id = poll();
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
    public Entry(EntryDate thisCreate, EntryDate thisTarget, String thisMessage) {
        this(thisTarget,thisMessage);
        //
        createDate = thisCreate;
    }




    public void modifyDate(EntryDate thisDate) {
        if (!thisDate.equals(targetDate)) {
            targetDate = thisDate;
        }
    }
    public void modifyMessage(String thisString) {
        if (!thisString.equals(message)) {
            message = thisString;
        }
    }


    /**
     * Creates a soft-clone of the entry
     * @return
     */
    public Entry duplicate() {
        Entry dup = new Entry();
        //
        dup.modifyDate(targetDate);
        dup.modifyMessage(message);
        //
        return dup;
    }

    /**
     * Determines if two entries have similar values (but NOT that they are the same!)
     * @param that
     * @return
     */
    public boolean resembles(Entry that) {
        if (this.targetDate.equals(that.targetDate)) {
            if (this.message.equals(that.message)) {
                return true;
            }
        }


        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Entry) {
            Entry that = (Entry)o;
            //
            if (this.id == that.id) {
                return true;
            }
            /*
            if (this.createDate.equals(that.createDate)) {
                if (this.targetDate.equals(that.targetDate)) {
                    if (this.message.equals(that.message)) {
                        return true;
                    }
                }
            }
            */
        }


        return false;
    }

    @Override
    public String toString() {
        return message+" | "+targetDate;
    }
}
