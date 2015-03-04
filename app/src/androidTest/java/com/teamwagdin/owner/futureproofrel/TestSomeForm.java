package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

/**
 * Created by Owner on 25/02/2015.
 */
public class TestSomeForm extends TestCase {
    SomeForm sf = new SomeForm(new User("Blah"));
    public void testSomeForm() {
        assertNotNull(sf);
    }


    public void testFormIsFilledOut() {
        sf.fillOut();
        //
        assertTrue( sf.isFilledOut() );
    }

}
