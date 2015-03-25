package com.teamwagdin.owner.futureproofrel;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by yangjiachang on 15-01-29.
 */
public class TestNotification extends AndroidTestCase {

    public void testNotification(){

        MyNotification mn = new MyNotification();

        assertNotNull(mn);
    }

    public void testDisplayNotification(){

        MyNotification mn = new MyNotification();

        mn.Display(this.getContext(), new Intent(this.getContext(), TestNotification.class));


        assertTrue(mn.hasDisplay());
    }
}
