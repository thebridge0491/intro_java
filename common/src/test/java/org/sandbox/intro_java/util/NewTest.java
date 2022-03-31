package org.sandbox.intro_java.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NewTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
    public NewTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    	System.err.println("###setup TestCase###");
    }
    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    	System.err.println("###teardown TestCase###");
    }
    
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
    	System.err.println("setup Test ...");
    }
    @org.junit.jupiter.api.AfterEach
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
		assertTrue(Library.in_epsilon(epsilon, 100.001f, 100.001f));
    }
    @Test
    public void test_strMethod() { assertEquals("Hello", "Hello");
    }
    @org.junit.jupiter.api.Timeout(value = 1000000,
      unit = java.util.concurrent.TimeUnit.MILLISECONDS)
    @Test
    public void test_timeoutMethod() { for (int i = 0; 1.0e6f > i; ++i);
    }
    @org.junit.jupiter.api.Disabled @Test
    public void test_ignoredMethod() { assertEquals(5, 2 * 2);
    }
    @Test //(expected = AssertionError.class)
    public void test_failMethod() { fail();
    }
    @Test //(expected = IllegalArgumentException.class)
    public void test_exceptionMethod() {
        //throw new IllegalArgumentException();
        assertThrows(IllegalArgumentException.class,
          () -> { throw new IllegalArgumentException(); });
    }
}
