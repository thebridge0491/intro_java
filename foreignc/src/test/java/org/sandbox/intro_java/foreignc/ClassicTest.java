package org.sandbox.intro_java.foreignc;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Array;

import org.sandbox.intro_java.util.Library;

public class ClassicTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
    public ClassicTest() {
    }
	
	private interface IFact { public long factorial(long n);
	}
	private interface IExpt { public float expt(float b, float n);
	}

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    	System.err.println("###setup TestCase###");
    }
    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    	System.err.println("###teardown TestCase###");
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
    public void test_fact() {
        IFact[] funcs = {
        	new IFact(){public long factorial(long n) {
        		return Classic.fact_i(n); }},
        	new IFact(){public long factorial(long n) {
        		return Classic.fact_lp(n); }}
        };
        for (IFact f : funcs)
        	assertEquals(120L, f.factorial(5));
    }
    @Test
    public void test_expt() {
        IExpt[] funcs = {
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_i(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_lp(b, n); }}
        };
        Float[] param1 = {2.0f, 11.0f, 20.0f}, param2 = {3.0f, 6.0f, 10.0f};
        Float[][] prod_params = Library.cartesian_prod(param1, param2);
        
        for (IExpt f : funcs) {
        	for (int j = 0; prod_params.length > j; ++j) {
        		double exp = Math.pow(prod_params[j][0], prod_params[j][1]);
        		float res = f.expt(prod_params[j][0], prod_params[j][1]);
        		assertEquals(exp, res, exp * epsilon);
        	}
        }
    }
}
