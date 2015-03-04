package com.teamwagdin.owner.futureproofrel;

import android.test.suitebuilder.TestSuiteBuilder;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by Owner on 28/01/2015.
 */
public class FullTestSuite extends TestCase {
    public static Test suite() {
        return new TestSuiteBuilder(FullTestSuite.class).includeAllPackagesUnderHere().build();
    }

    public FullTestSuite() {
        super();
    }
}
