package com.teamwagdin.owner.futureproofrel;

import java.util.List;

/**
 * Created by Owner on 25/02/2015.
 */
public class SomeAccount {
    public EntryDatabase entryDatabase = new EntryDatabase();
    public SomeForm myForm;

    public SomeAccount(SomeForm thisForm) {
        myForm = thisForm;
    } // ??? <-- Should not be instantiable by anything other than the "SomeApplication" class


    public List<Entry> getPastEntries() {
        return entryDatabase.getPastEntries();
    }
    public List<Entry> getFutureEntries() {
        return entryDatabase.getFutureEntries();
    }
    public List<Entry> getAllEntries() {
        return entryDatabase.getAllEntries();
    }
    public boolean containsEntry(Entry thisEntry) {
        return entryDatabase.getAllEntries().contains(thisEntry);
    }
}
