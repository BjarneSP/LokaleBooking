/**
 * Class "AllTests": Contains information about tests.
 * The class contains the following attributes for email: emailID and description.
 * @author Bjarne, Frederik, Kristoffer, Ramanan (Gruppe 2)
 * @version 0.1
 * @since 19-12-2016
 */

package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import junit.framework.Test;

import junit.framework.TestSuite;

//@RunWith(Suite.class)
//@SuiteClasses({})
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(BookingTest.class);
		suite.addTestSuite(DiverseTests.class);
		
		// $JUnit-END$
		return suite;
	}
}