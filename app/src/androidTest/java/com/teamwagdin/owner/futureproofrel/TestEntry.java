package com.teamwagdin.owner.futureproofrel;

import android.test.AndroidTestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Owner on 28/01/2015.
 */
public class TestEntry extends AndroidTestCase {
    Entry e;


    public void setUp() {
        e = new Entry();
    }


    public void testCreateEntry() {
        assertNotNull(e);
    }


    public void testEntriesAreDifferent() {
        Entry otherEntry = new Entry();

        assertTrue( !e.equals(otherEntry) );
    }

    public void testEntryIsDuplicate() {
        Entry otherEntry = e.duplicate();
        //
        assertTrue( e.resembles(otherEntry) );
    }

    public void testEntryModified() {
        Entry oldEntry = e.duplicate();
        //
        e.modifyDate(FutureProof.getPresentDateTime());
        e.modifyMessage("Ayeeeeeeeeeeeeeeee");
        //
        assertTrue( !e.resembles(oldEntry) );
    }
}
