package com.teamwagdin.owner.futureproofrel;

import java.util.List;

/**
 * Created by Owner on 25/02/2015.
 */
public class SomeAccount {
    public EntryDatabase entryDatabase = new EntryDatabase();
    private SomeForm myForm;

    public SomeAccount(SomeForm thisForm) {
        myForm = thisForm;
    } // ??? <-- Should not be instantiable by anything other than the "FutureProof" class


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
    public void antiquateEntry(Entry thisEntry) {
        entryDatabase.antiquateEntry(thisEntry);
    }


    public String getUsername() {
        return myForm.myUser.myName;
    }
    public User getUser() {
        return myForm.myUser;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SomeAccount) {
            SomeAccount sa = (SomeAccount)o;

            if (getUser().equals(sa.getUser())) {
                return true;
            }
        }


        return false;
    }
}
