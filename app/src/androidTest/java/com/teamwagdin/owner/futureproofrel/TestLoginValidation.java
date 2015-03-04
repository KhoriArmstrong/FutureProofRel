package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

/**
 * Created by Owner on 25/02/2015.
 */
public class TestLoginValidation extends TestCase {

    LoginValidation lv = new LoginValidation();

    public void testLoginValidation() {
        assertNotNull(lv);
    }


    public void testLoginNameExists() {
        lv.addUser();
        //
        assertNotNull(lv.findUser());
    }


    public void testLoginNamePasswordMatches() {
        lv.addUser();
        //
        User u = null;
        u = lv.findUser();
        //
        assertTrue(lv.passwordMatches(u));
    }

}
