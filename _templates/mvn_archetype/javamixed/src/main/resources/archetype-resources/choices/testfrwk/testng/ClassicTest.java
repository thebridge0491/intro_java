#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.lang.reflect.Array;

public class ClassicTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
    public ClassicTest() {
    }
    
    public Boolean in_epsilon(Float tolerance, Float a, Float b) {
		Float delta = Math.abs(tolerance);
		//return (a - delta) <= b && (a + delta) >= b;
		return !((a + delta) < b) && !((b + delta) < a);
	}
	
	public <T> T[][] cartesian_prod(T[] arr1, T[] arr2) {
		@SuppressWarnings("unchecked")
		T[][] new_arr = (T[][]) Array.newInstance(
			arr1.getClass().getComponentType(),
			arr1.length * arr2.length, 2);
		for (int i = 0, idxX = 0; arr1.length > i; ++i)
			for (int j = 0; arr2.length > j; ++j) {
				idxX = j + (i * arr2.length);
				new_arr[idxX][0] = arr1[i];
				new_arr[idxX][1] = arr2[j];
			}
		return new_arr;
	}
	
	private interface IFact { public long factorial(long n);
	}
	private interface IExpt { public float expt(float b, float n);
	}

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    	System.err.println("${symbol_pound}${symbol_pound}${symbol_pound}setup TestCase${symbol_pound}${symbol_pound}${symbol_pound}");
    }
    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    	System.err.println("${symbol_pound}${symbol_pound}${symbol_pound}teardown TestCase${symbol_pound}${symbol_pound}${symbol_pound}");
    }
    
    @org.testng.annotations.BeforeMethod
    public void setUp() {
    	System.err.println("setup Test ...");
    }
    @org.testng.annotations.AfterMethod
    public void tearDown() {
    	System.err.println("... teardown Test");
    }
    
    @Test(groups = {"grp1"})
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
    
    @Test(groups = {"grp1"})
    public void test_expt() {
        IExpt[] funcs = {
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_i(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_lp(b, n); }}
        };
        Float[] param1 = {2.0f, 11.0f, 20.0f}, param2 = {3.0f, 6.0f, 10.0f};
        Float[][] prod_params = cartesian_prod(param1, param2);
        
        for (IExpt f : funcs) {
        	for (int j = 0; prod_params.length > j; ++j) {
        		double exp = Math.pow(prod_params[j][0], prod_params[j][1]);
        		float res = f.expt(prod_params[j][0], prod_params[j][1]);
        		assertEquals(exp, res, exp * epsilon);
        	}
        }
    }
}
