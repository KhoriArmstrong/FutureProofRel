package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

/**
 * Created by Owner on 25/02/2015.
 */
public class TestSomeApplication extends TestCase {

    FutureProof sa = FutureProof.createInstance();
    User u = new User("Khori");

    public void testSomeApplication() {
        assertNotNull(sa);
    }

    public void setUp() {
        sa.createNewAccount(new SomeForm(u));
        //
        sa.login(u);
    }


    public void testRunSomeApplication() {
        sa.Run();
        //
        assertTrue( sa.isRunning() );
    }


    public void testLoggedIntoApplication() {
        assertTrue( sa.isLoggedIn() );
    }


    public void testIsSingleton() {
        FutureProof sa2 = FutureProof.createInstance();
        //
        assertEquals(sa, sa2);
    }


    public void testAccountWasCreated() {
        int entryCount = sa.getAllAccounts().size();

        sa.createNewAccount(new SomeForm(u));
        //
        assertTrue( sa.getAllAccounts().size() > entryCount );
    }

    public void testAccountWasRetrieved() {
        SomeAccount account = sa.getUserAccount();
        //
        assertNotNull( account );
    }


    public void testHasSentAnEntryToUserAccount() {
        int entryCount = sa.getAllEntries().size();

        Entry e = new Entry();
        //
        sa.sendEntry(e);
        //
        assertTrue( sa.getAllEntries().size() > entryCount );
    }


    public void testEntryReceivedByCorrectAccount() {
        Entry e = new Entry();

        User firstUser = new User("Hamid");
        User otherUser = new User("Nabeela");
        //
        sa.createNewAccount(new SomeForm(firstUser));
        sa.createNewAccount(new SomeForm(otherUser));


        sa.login(firstUser);
        SomeAccount firstAccount = sa.getUserAccount();
        //
        sa.sendEntry(e);


        sa.login(otherUser);
        SomeAccount otherAccount = sa.getUserAccount();



        assertTrue( firstAccount.containsEntry(e) && !otherAccount.containsEntry(e) );
    }


    public void testNewAlarmCreated() {
        Entry e = new Entry(EntryDate.NEVER,"Aaaaaaaaaaa");
        //
        sa.createEntryAlarm(e);


        assertTrue( !sa.allAlarms.isEmpty() );
    }

}
