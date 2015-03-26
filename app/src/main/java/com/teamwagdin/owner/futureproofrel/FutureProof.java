package com.teamwagdin.owner.futureproofrel;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 25/02/2015.
 */
public class FutureProof {

    public static FutureProof _futureProof;
    public static FutureProof createInstance() {
        if (_futureProof == null) { _futureProof = new FutureProof(); }
        //
        return _futureProof;
    }



    public final int CHECKDELAY = 1000;
    Handler h;
    Runnable r;
    private FutureProof() {

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
        login(account.getUser());
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
    public void login(SomeAccount thisAccount) {
        if (thisAccount != null) {
            login(thisAccount.getUser());
        }
    }
    public void login(User thisUser) {
        SomeAccount thisAccount = null;
        for (SomeAccount anAccount : allAccounts) {
            if (anAccount.getUser().equals(thisUser)) {
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
    public void antiquateEntry(Entry thisEntry) {
        getCurrentUserAccount().antiquateEntry(thisEntry);
    }


    public SomeAccount getCurrentUserAccount() {
        return loggedAccount;
    }
    public List<Entry> getAllEntries() {
        return loggedAccount.entryDatabase.getAllEntries();
    }



    private ArrayList<SomeAccount> allAccounts = new ArrayList<SomeAccount>();
    private SomeAccount createNewAccount(SomeForm thisForm) {
        SomeAccount sa = new SomeAccount(thisForm);
        //
        allAccounts.add(sa);
        //
        return sa;
    }
    public SomeAccount createNewAccount(String thisName) {
        SomeAccount sa = retrieveAccount(thisName);

        if (sa == null) {
            return createNewAccount(new SomeForm(new User(thisName)));
        }


        return null;
        // throw new Exception("Account by this name already exists.");
    }

    public List<SomeAccount> getAllAccounts() {
        return allAccounts;
    }

    public SomeAccount retrieveAccount(String thisName) {
        for (SomeAccount sa : allAccounts) {
            if (sa.getUsername().equals(thisName)) {
                return sa;
            }
        }


        return null;
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
        // not logged in
        // instead of going there, go to login screen

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
        boolean hasAntiquated = false;

        // Sounds any alarms that are ready to go.
        for (EntryAlarm anAlarm : allAlarms) {
            anAlarm.theChecker.checkTheTime();
            //
            if (anAlarm.theChecker.hasTimePassed()) {
                anAlarm.theNotification.Display(c,i);
                //
                antiquateEntry(anAlarm.theEntry);

                hasAntiquated = true;
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


        if (hasAntiquated) {
            theEvent.onAntiquate();
        }
    }

    private FPEventListener theEvent;
    public void assignAlertResponder(FPEventListener thisListener) {
        theEvent = thisListener;
    }
}
