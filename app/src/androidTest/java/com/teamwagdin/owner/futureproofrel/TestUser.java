package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

/**
 * Created by Owner on 25/02/2015.
 */
public class TestUser extends TestCase {

    User u = new User("User");

    public void testUser() {
        assertNotNull(u);
    }


    public void testUserNotDefault() {
        User newUser = new User("Jessica");
        //
        assertTrue( !newUser.equals(u) );
    }
}
