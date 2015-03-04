package com.teamwagdin.owner.futureproofrel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 25/02/2015.
 */
public class SomeApplication {

    public static SomeApplication _someApplication;
    public static SomeApplication createInstance() {
        if (_someApplication == null) { _someApplication = new SomeApplication(); }
        //
        return _someApplication;
    }

    private SomeApplication() {}



    private boolean _running = false;
    public void Run() {
        if (!isLoggedIn()) { return; }

        _running = true;
    }

    public boolean isRunning() {
        return _running;
    }



    private User loggedUser = null;
    private SomeAccount loggedAccount = null;
    //
    public void login(User thisUser) {
        SomeAccount thisAccount = null;
        for (SomeAccount anAccount : allAccounts) {
            if (anAccount.myForm.myUser == thisUser) {
                thisAccount = anAccount;
                break;
            }
        }
        //

        if (thisAccount == null) { return; }

        //
        loggedUser = thisUser;
        loggedAccount = thisAccount;
    }

    public boolean isLoggedIn() {
        return (loggedUser != null);
    }



    public void sendEntry(Entry thisEntry) {
        loggedAccount.entryDatabase.sendEntry(thisEntry);
    }


    public SomeAccount getUserAccount() {
        return loggedAccount;
    }
    public List<Entry> getAllEntries() {
        return loggedAccount.entryDatabase.getAllEntries();
    }



    private ArrayList<SomeAccount> allAccounts = new ArrayList<SomeAccount>();
    public SomeAccount createNewAccount(SomeForm thisForm) {
        SomeAccount sa = new SomeAccount(thisForm);
        //
        allAccounts.add(sa);
        //
        return sa;
    }

    public List<SomeAccount> getAllAccounts() {
        return allAccounts;
    }


    public static EntryDate getPresentDateTime() {
        return new EntryDate();
    }

}
