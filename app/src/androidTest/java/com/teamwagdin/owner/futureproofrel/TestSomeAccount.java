package com.teamwagdin.owner.futureproofrel;

import android.accounts.Account;

import junit.framework.TestCase;

/**
 * Created by Owner on 25/02/2015.
 */
public class TestSomeAccount extends TestCase {

    SomeAccount sa = new SomeAccount(new SomeForm(new User("Joe")));

    public void testSomeAccount() {
        assertNotNull(sa);
    }

}
