package com.teamwagdin.owner.futureproofrel;

import android.test.AndroidTestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Owner on 28/01/2015.
 */
public class TestEntry extends AndroidTestCase {
    Entry e = new Entry();

    public void testCreateEntry() {
        assertNotNull(e);
    }


    public void testEntriesAreDifferent() {
        Entry otherEntry = new Entry();

        assertTrue( !e.equals(otherEntry) );
    }
}
