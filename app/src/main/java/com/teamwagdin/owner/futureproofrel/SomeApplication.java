package com.teamwagdin.owner.futureproofrel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

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



    public final int CHECKDELAY = 1000;
    Handler h;
    Runnable r;
    private SomeApplication() {

        initializeMe();


        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                applicationUpdate();
                //
                if (true) { // ??? <-- Will run indefinitely-- Needs a break!
                    h.postDelayed(r, CHECKDELAY);
                }
            }
        };
        //
        h.postDelayed(r,CHECKDELAY);
    }


    public void initializeMe() {
        // ??? <-- Temporary for until the login screen works.
        SomeAccount account = createNewAccount(new SomeForm(new User("Khori")));
        //
        login(account.myForm.myUser);


        // sendEntry(new Entry(new EntryDate(EntryDate.FEBRUARY,26,2015,15,58), "Hello World-- From Khori"));
        // sendEntry(new Entry(new EntryDate(EntryDate.MARCH,6,2016,18,10), "Hello World-- From Luke"));
    }



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
        //
        createEntryAlarm(thisEntry);
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


    List<EntryAlarm> allAlarms = new ArrayList<EntryAlarm>();
    public EntryAlarm createEntryAlarm(Entry thisEntry) {
        EntryAlarm ea = new EntryAlarm(thisEntry);
        //
        ea.theChecker.checkTheTime();
        //
        if (!ea.theChecker.hasTimePassed()) {
            allAlarms.add(ea);
        }
        //
        return ea;
    }


    Context c;
    Intent i;
    public void shiftActivity(Context thisContext, Class<?> thisClass) {
        c = thisContext;
        //
        i = new Intent(c, thisClass);
        //
        thisContext.startActivity(i);
    }


    /** The entry point for any application updates
     *
     */
    public void applicationUpdate() {
        checkAllAlarms();
    }

    public void checkAllAlarms() {
        // Sounds any alarms that are ready to go.
        for (EntryAlarm anAlarm : allAlarms) {
            anAlarm.theChecker.checkTheTime();
            //
            if (anAlarm.theChecker.hasTimePassed()) {
                anAlarm.theNotification.Display(c,i);
            }
        }
        //
        // Removes any alarms that have sounded.
        EntryAlarm anAlarm;
        for (int i=0; i<allAlarms.size(); i++) {
            anAlarm = allAlarms.get(i);
            //
            if (anAlarm.theNotification.hasDisplay()) {
                allAlarms.remove(i);
                //
                i--;
            }
        }
    }

}
