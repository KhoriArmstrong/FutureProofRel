package com.teamwagdin.owner.futureproofrel;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TestTimeChecker extends TestCase {

    public void testTimeChecker(){

        TimeChecker tc = new TimeChecker(new EntryDate());

        assertNotNull(tc);




    }

    public void testCheckIfTimeHasPassed(){
        TimeChecker tc = new TimeChecker(new EntryDate(EntryDate.MARCH,20,2015,9,50));
        //
        tc.checkTheTime();


        assertTrue(tc.hasTimePassed());
    }




}
