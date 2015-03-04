package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

import java.util.Date;

/**
 * Created by yangjiachang on 15-02-05.
 */
public class TestEntryDate extends TestCase {

    public void testEntryDate() {
        EntryDate ed = new EntryDate();
        //
        assertNotNull(ed);
    }

    public void testEntryDateNotDefault() {
        EntryDate exp;
        EntryDate def = new EntryDate();
        //
        exp = new EntryDate(06,12,1989,15,00);
        //
        assertTrue(!def.equals(exp));
    }


    public void testEntryDateAfter() {
        EntryDate currentTime;
        EntryDate targetTime;
        //
        currentTime = new EntryDate(EntryDate.FEBRUARY,30,2015,15,58);
        targetTime = new EntryDate(EntryDate.FEBRUARY,26,2015,15,58);
        //
        assertTrue(currentTime.After(targetTime));
    }


    public void testEntryDateDefaultIsCurrentDate() {
        Date d = new Date();
        //
        EntryDate exp = new EntryDate(d.getMonth(),d.getDate(), 1900+d.getYear(),d.getHours(), d.getMinutes());
        EntryDate def = new EntryDate();

        //
        assertTrue(def.equals(exp));
    }
}
