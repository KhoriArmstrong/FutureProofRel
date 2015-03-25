package com.teamwagdin.owner.futureproofrel;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by Owner on 29/01/2015.
 */
public class EntryDatabase {

    private ArrayList<Entry> allPastEntries = new ArrayList<Entry>();
    private ArrayList<Entry> allFutureEntries = new ArrayList<Entry>();



    public List<Entry> getPastEntries() {
        return allPastEntries;
    }
    public List<Entry> getFutureEntries() {
        return allFutureEntries;
    }
    public List<Entry> getAllEntries() {
        ArrayList<Entry> theseEntries = new ArrayList<Entry>();
        //
        theseEntries.addAll(allPastEntries);
        theseEntries.addAll(allFutureEntries);
        //
        return theseEntries;
    }

    public void sendEntry(Entry thisEntry) {
        if (thisEntry.targetDate.After(FutureProof.getPresentDateTime())) {
            allFutureEntries.add(thisEntry);
        }
        else {
            allPastEntries.add(thisEntry);
        }
    }


    public void antiquateEntry(Entry thisEntry) {
        if (allFutureEntries.remove(thisEntry)) {
            allPastEntries.add(thisEntry);
        }
    }
}
