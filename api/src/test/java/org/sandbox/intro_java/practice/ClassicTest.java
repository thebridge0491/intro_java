package org.sandbox.intro_java.practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.sandbox.intro_java.util.Library;

public class ClassicTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
    public ClassicTest() {
    }
	
	private interface ISquare { public float square(float n);
	}
	private interface IExpt { public float expt(float b, float n);
	}
	private interface ISum_to { public long sum_to(long hi, long lo);
	}
	private interface IFact { public long factorial(long n);
	}
	private interface IFib { public int fib(int n);
	}
	private interface IGcd { public int gcd(int[] arr);
	}
	private interface ILcm { public int lcm(int[] arr);
	}
	private interface IBaseExpand { public int[] baseExpand(int b, int n);
	}
	private interface IBaseTo10 { public int baseTo10(int b, int[] arr);
	}
	private interface IRangeStep {
		public int[] rangeStep(int step, int start, int stop);
	}
	private interface IRange { public int[] range(int start, int stop);
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
    public void test_square() {
		ISquare[] objs = {
        	new ISquare(){public float square(float n) {
        		return Classic.square_i(n); }},
        	new ISquare(){public float square(float n) {
        		return Classic.square_lp(n); }}
        };
        Float[] param1 = {2.0f, 11.0f, 20.0f};
        
		for (Float val : param1) {
			double exp = Math.pow(val, 2.0f);
			for (ISquare obj : objs)
				assertEquals(exp, obj.square(val), exp * epsilon);
		}
    }
    
    @Test
    public void test_expt() {
        IExpt[] objs = {
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_i(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.expt_lp(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.fast_expt_i(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic.fast_expt_lp(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic_scala.expt_i(b, n); }},
        	new IExpt(){public float expt(float b, float n) {
        		return Classic_scala.expt_r(b, n); }}
        };
        Float[] param1 = {2.0f, 11.0f, 20.0f}, param2 = {3.0f, 6.0f, 10.0f};
        Float[][] prod_params = Library.<Float>cartesian_prod(param1, param2);
        
		for (Float[] params : prod_params) {
			double exp = Math.pow(params[0], params[1]);
			for (IExpt obj : objs) {
				float res = obj.expt(params[0], params[1]);
				assertEquals(exp, res, exp * epsilon);
			}
		}
    }
    
    @Test
    public void test_sum_to() {
        ISum_to[] objs = {
        	new ISum_to(){public long sum_to(long hi, long lo) {
        		return Classic.sum_to_i(hi, lo); }},
        	new ISum_to(){public long sum_to(long hi, long lo) {
        		return Classic.sum_to_lp(hi, lo); }}
        };
        for (ISum_to obj : objs) {
			long exp1 = 15L, exp2 = 75L;
			assertEquals(exp1, obj.sum_to(5L, 0L));
			assertEquals(exp2, obj.sum_to(15L, 10L));
		}
    }
    
    @Test
    public void test_fact() {
        IFact[] objs = {
        	new IFact(){public long factorial(long n) {
        		return Classic.fact_i(n); }},
        	new IFact(){public long factorial(long n) {
        		return Classic.fact_lp(n); }},
        	new IFact(){public long factorial(long n) {
        		return Classic_scala.fact_i(n); }},
        	new IFact(){public long factorial(long n) {
        		return Classic_scala.fact_r(n); }}
        };
        for (IFact obj : objs)
        	assertEquals(120L, obj.factorial(5L));
    }
    
    @Test
    public void test_fib() {
        IFib[] objs = {
        	new IFib(){public int fib(int n) {
        		return Classic.fib_i(n); }},
        	new IFib(){public int fib(int n) {
        		return Classic.fib_lp(n); }}
        };
        for (IFib obj : objs)
        	assertEquals(13, obj.fib(7));
    }
    
    @Test
    public void test_quot_rem() {
		Integer[] param1 = {10, -10}, param2 = {3, -3};
        Integer[][] prod_params = Library.<Integer>cartesian_prod(param1, 
			param2);
        
		for (Integer[] params : prod_params) {
			int expQ = params[0] / params[1], expR = params[0] % params[1];
			assertEquals(expQ, Classic.quot_m(params[0], params[1]));
			assertEquals(expR, Classic.rem_m(params[0], params[1]));
		}
	}
	
	@Test
    public void test_gcd_lcm() {
        IGcd[] objsGcd = {
        	new IGcd(){public int gcd(int[] arr) {
        		return Classic.gcd_i(arr); }},
        	new IGcd(){public int gcd(int[] arr) {
        		return Classic.gcd_lp(arr); }}
        };
        ILcm[] objsLcm = {
        	new ILcm(){public int lcm(int[] arr) {
        		return Classic.lcm_i(arr); }},
        	new ILcm(){public int lcm(int[] arr) {
        		return Classic.lcm_lp(arr); }}
        };
        int[] arr1 = {24, 16}, arr2_gcd = {24, 16, 12},
			arr2_lcm = {24, 16, 32};
		int exp1_gcd = 8, exp1_lcm = 48, exp2_gcd = 4, exp2_lcm = 96;
        for (int i = 0; objsGcd.length > i; ++i) {
        	assertEquals(exp1_gcd, objsGcd[i].gcd(arr1));
        	assertEquals(exp1_lcm, objsLcm[i].lcm(arr1));
        	
        	assertEquals(exp2_gcd, objsGcd[i].gcd(arr2_gcd));
        	assertEquals(exp2_lcm, objsLcm[i].lcm(arr2_lcm));
		}
    }
	
	@Test
    public void test_baseExpand() {
        IBaseExpand[] objs = {
        	new IBaseExpand(){public int[] baseExpand(int b, int n) {
        		return Classic.baseExpand_i(b, n); }},
        	new IBaseExpand(){public int[] baseExpand(int b, int n) {
        		return Classic.baseExpand_lp(b, n); }}
        };
        int[] exp1 = {1, 0, 1, 1}, exp2 = {1, 1, 0, 1};
        int b1 = 2, b2 = 4, n1 = 11, n2 = 81;
        
        for (IBaseExpand obj : objs) {
        	assertArrayEquals(exp1, obj.baseExpand(b1, n1));
        	assertArrayEquals(exp2, obj.baseExpand(b2, n2));
		}
    }
	
	@Test
    public void test_baseTo10() {
        IBaseTo10[] objs = {
        	new IBaseTo10(){public int baseTo10(int b, int[] arr) {
        		return Classic.baseTo10_i(b, arr); }},
        	new IBaseTo10(){public int baseTo10(int b, int[] arr) {
        		return Classic.baseTo10_lp(b, arr); }}
        };
        int[] arr1 = {1, 0, 1, 1}, arr2 = {1, 1, 0, 1};
        int b1 = 2, b2 = 4, exp1 = 11, exp2 = 81;
        
        for (IBaseTo10 obj : objs) {
        	assertEquals(exp1, obj.baseTo10(b1, arr1));
        	assertEquals(exp2, obj.baseTo10(b2, arr2));
		}
    }
    
    @Test
    public void test_range() {
		IRangeStep[] objsStep = {
			new IRangeStep() {public int[] rangeStep(int step, int start,
					int stop) {
				return Classic.rangeStep_i(step, start, stop); }},
			new IRangeStep() {public int[] rangeStep(int step, int start,
					int stop) {
				return Classic.rangeStep_lp(step, start, stop); }}
		};
		IRange[] objs = {
			new IRange() {public int[] range(int start, int stop) {
				return Classic.range_i(start, stop); }},
			new IRange() {public int[] range(int start, int stop) {
				return Classic.range_lp(start, stop); }}
		};
		int start1 = 0, stop1 = 5, exp1[] = {0, 1, 2, 3, 4};
		for (IRange obj : objs)
			assertArrayEquals(exp1, obj.range(start1, stop1));
		int step2 = -1, start2 = 4, stop2 = -1, exp2[] = {4, 3, 2, 1, 0};
		for (IRangeStep obj : objsStep)
			assertArrayEquals(exp2, obj.rangeStep(step2, start2, stop2));
	}
}
