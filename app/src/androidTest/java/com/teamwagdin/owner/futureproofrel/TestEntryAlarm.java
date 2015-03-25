package com.teamwagdin.owner.futureproofrel;

import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by yangjiachang on 15-02-11.
 */
public class TestEntryAlarm extends AndroidTestCase {
    public void testEntryAlarm() {
        Context c = this.getContext();
        //
        Entry e = new Entry(new EntryDate(EntryDate.AUGUST,19,1990,0,1),"Oh baby!");
        //
        EntryAlarm ea = new EntryAlarm(e);
        //
        assertNotNull(ea);
    }
}
