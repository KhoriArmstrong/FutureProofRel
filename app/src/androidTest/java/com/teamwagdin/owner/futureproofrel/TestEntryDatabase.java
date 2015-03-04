package com.teamwagdin.owner.futureproofrel;

import android.test.AndroidTestCase;

/**
 * Created by Owner on 29/01/2015.
 */
public class TestEntryDatabase extends AndroidTestCase {
    public void testCreateEntryDatabase() {
        EntryDatabase ed = new EntryDatabase();
        //
        assertNotNull(ed);
    }

    public void testAddNewEntry() {
        EntryDatabase ed = new EntryDatabase();
        Entry e = new Entry();
        //
        ed.sendEntry(e);
        //
        assertTrue(ed.getAllEntries().size() != 0);
    }
}
