#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.junit.Test;
import static org.junit.Assert.*;

public class NewTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
    public NewTest() {
    }
    
    public Boolean in_epsilon(Float tolerance, Float a, Float b) {
		Float delta = Math.abs(tolerance);
		//return (a - delta) <= b && (a + delta) >= b;
		return !((a + delta) < b) && !((b + delta) < a);
	}

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    	System.err.println("${symbol_pound}${symbol_pound}${symbol_pound}setup TestCase${symbol_pound}${symbol_pound}${symbol_pound}");
    }
    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    	System.err.println("${symbol_pound}${symbol_pound}${symbol_pound}teardown TestCase${symbol_pound}${symbol_pound}${symbol_pound}");
    }
    
    @org.junit.Before
    public void setUp() {
    	System.err.println("setup Test ...");
    }
    @org.junit.After
    public void tearDown() {
    	System.err.println("... teardown Test");
    }
	
    @Test
    public void test_classExists() {
        try {
            Class.forName(String.format("%s.Library",
                this.getClass().getPackage().getName()));
        } catch(ClassNotFoundException exc) {
            //fail("Class(es) not existent: " + Library.class.getName());
            fail(String.format("%s %s", "Class(es) not existent:",
                java.util.Arrays.toString(new String[]{
                Library.class.getName()})));
        }
    }
    
	@Test
    public void test_method() { assertEquals(4, 2 * 2);
    }
    @Test
    public void test_dblMethod() {
		//assertEquals(100.001f, 100.001f, epsilon);
		assertTrue(in_epsilon(epsilon, 100.001f, 100.001f));
    }
    @Test
    public void test_strMethod() { assertEquals("Hello", "Hello");
    }
    @Test(timeout = 1000)
    public void test_timeoutMethod() { for (int i = 0; 1.0e6f > i; ++i);
    }
    @org.junit.Ignore @Test
    public void test_ignoredMethod() { assertEquals(5, 2 * 2);
    }
    @Test //(expected = AssertionError.class)
    public void test_failMethod() { fail();
    }
    @Test(expected = IllegalArgumentException.class)
    public void test_exceptionMethod() {
        throw new IllegalArgumentException();
    }
}
