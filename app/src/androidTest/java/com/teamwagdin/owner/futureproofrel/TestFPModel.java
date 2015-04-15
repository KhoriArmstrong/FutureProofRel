package com.teamwagdin.owner.futureproofrel;

import junit.framework.TestCase;

/**
 * Created by Owner on 09/04/2015.
 */
public class TestFPModel extends TestCase {

    public void testCreated() {
        FPModel fpm = FPModel.createInstance();


        assertNotNull(fpm);
    }

    public void testIsSingleton() {
        FPModel fpm = FPModel.createInstance();
        //
        FPModel fpm1 = FPModel.createInstance();


        assertTrue( fpm.equals(fpm1) );
    }


}
