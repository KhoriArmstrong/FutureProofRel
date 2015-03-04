package com.teamwagdin.owner.futureproofrel;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TestTimeChecker extends TestCase {

    public void testTimeChecker(){

        TimeChecker tc = new TimeChecker(new EntryDate(),new EntryDate());

        assertNotNull(tc);




    }

    public void testCheckIfTimeHasPassed(){



        TimeChecker tc = new TimeChecker(new EntryDate(EntryDate.JANUARY,10,1,1,1)
        ,new EntryDate(EntryDate.JANUARY,1,1,1,1));




        tc.checkTheTime();

        assertTrue(tc.timeHasPassed());



    }




}
