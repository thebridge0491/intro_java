package org.sandbox.intro_java.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DocComment:
 * <p>Brief description.</p> */
public class Classic {
	private static final Logger pracLogger = LoggerFactory.getLogger("prac");
    
    private interface IOp_2Longs { public long op_2longs(long a, long b);
	}
	private interface IBoolOp_2Ints { public boolean boolop_2ints(int a, int b);
	}
   
    public static float expt_lp(final float b, final float n) {
    	float acc = 1.0f;
    	
    	for (float i = n; 0.0f < i; --i)
    		acc *= b;
    	return acc;
    }
    
    private static float expt_iter(final float b, final float n, 
    		final float acc) {
    	return n > 0.0f ? expt_iter(b, n - 1, acc * b) : acc;
    }
    public static float expt_i(final float b, final float n) {
    	return expt_iter(b, n, 1.0f);
    }
    
    private static float fast_expt_iter(final float b, final float n, 
    		final float acc) {
		if (0 >= n)
			return acc;
		else if (0 == n % 2)
			return fast_expt_iter(b, n - 2, acc * (float)Math.pow(b, 2.0f));
    	return fast_expt_iter(b, n - 1, acc * b);
    }
    public static float fast_expt_i(final float b, final float n) {
    	return fast_expt_iter(b, n, 1.0f);
    }
    
    public static float fast_expt_lp(final float b, final float n) {
    	float acc = 1.0f, num = n;
    	
    	while (num > 0.0f) {
			if (0 == num % 2) {
				acc *= b * b;
				num -= 2.0f;
			} else {
				acc *= b;
				num -= 1.0f;
			}
		}
    	return acc;
    }
    
    public static float square_i(final float n) {
		return expt_i(n, 2.0f);
	}
    
    public static float square_lp(final float n) {
		return expt_lp(n, 2.0f);
	}
	
	private static long numSeqMath_iter(IOp_2Longs obj, long acc, 
			final long hi, final long lo) {
		return hi >= lo ? numSeqMath_iter(obj, obj.op_2longs(acc, lo), hi, 
			lo + 1L) : acc;
	}
	private static long numSeqMath_i(IOp_2Longs obj, final long init, 
			final long hi, final long lo) {
		return numSeqMath_iter(obj, init, hi, lo);
	}
	
	public static long numSeqMath_lp(IOp_2Longs obj, final long init, 
			final long hi, final long lo) {
		long acc = init;
		for (long i = lo; hi >= i; ++i)
			acc = obj.op_2longs(acc, i);
		return acc;
	}
	
	public static long sum_to_i(final long hi, final long lo) {
		return numSeqMath_i(new IOp_2Longs(){ public long op_2longs(long a, long b) { return a + b; }}, 0L, hi, lo);
	}
	
	public static long sum_to_lp(final long hi, final long lo) {
		return numSeqMath_lp(new IOp_2Longs(){ public long op_2longs(long a, long b) { return a + b; }}, 0L, hi, lo);
	}
    
    public static long fact_i(final long n) {
		return numSeqMath_i(new IOp_2Longs(){ public long op_2longs(long a, long b) { return a * b; }}, 1L, n, 1L);}
	
    public static long fact_lp(final long n) {
        pracLogger.info("fact_lp()");
        return numSeqMath_lp(new IOp_2Longs(){ public long op_2longs(long a, long b) { return a * b; }}, 1L, n, 1L);
    }
    
    private static int fib_iter(final int s0, final int s1, final int cnt) {
		return 0 >= cnt ? s0 : fib_iter(s1, s0 + s1, cnt - 1);
	}
	public static int fib_i(final int n) {
		return fib_iter(0, 1, n);
	}
	
	public static int fib_lp(final int n) {
		int acc = 0;
		for (int sum0 = 0, sum1 = 1, ct = n; 0 <= ct; --ct) {
			acc = sum0;
			sum0 = sum1;
			sum1 = sum1 + acc;
		}
		return acc;
	}
	
	private static void mkRows(final int n, int[][] arr2d) {
		for (int row = 1; n >= row; ++row) {
			arr2d[row][0] = arr2d[row][row] = 1;
			for (int col = 1; row > col; ++col)
				arr2d[row][col] = arr2d[row - 1][col - 1] + 
					arr2d[row - 1][col];
		}
	}
	public static int[][] pascaltri_add(final int n) {
		int[][] result = new int[n + 1][];
		for (int row = 0; n >= row; ++row)
			for (int col = 0; row >= col; ++col)
				result[row] = new int[row + 1];
		result[0][0] = 1;
		mkRows(n, result);
		return result;
	}
	
	public static void printPascalTri(final int n, final int[][] arr2d) {
		if (null == arr2d)
			return;
		for (int row = 0; n >= row; ++row, System.out.println())
			for (int col = 0; row >= col; ++col)
				System.out.format("%3d ", arr2d[row][col]);
		System.out.println();
	}
	
	public static int quot_m(final int n, final int d) { return n / d;
	}
	
	public static int rem_m(final int n, final int d) { return n % d;
	}
    
    private static int euclid_iter(final int a, final int b) {
		return 0 == b ? a : euclid_iter(b, a % b);
	}
	private static int euclid_i(final int m, final int n) {
		return euclid_iter(m, n);
	}
	
	private static int euclid_lp(final int m, final int n) {
		int acc = m;
		for (int b = n, swap = 0; 0 < b; ) {
			swap = acc;
			acc = b;
			b = swap % b;
		}
		return acc;
	}
    
    private static int gcd_iter(final int acc, final int idx, 
			final int[] arr) {
		return 0 > idx ? acc : gcd_iter(euclid_i(acc, arr[idx]), idx - 1, 
			arr);
	}
	public static int gcd_i(final int[] arr) {
		return 2 > arr.length ? 1 : gcd_iter(arr[0], arr.length - 1, arr);
	}
	
	public static int gcd_lp(final int[] arr) {
		int acc = arr[0];
		for (int i = 1; arr.length > i; ++i)
			acc = euclid_lp(acc, arr[i]);
		return acc;
	}
    
    private static int lcm_iter(final int acc, final int idx, 
			final int[] arr) {
		return 0 > idx ? acc : lcm_iter(
			acc * arr[idx] / euclid_i(acc, arr[idx]), idx - 1, arr);
	}
	public static int lcm_i(final int[] arr) {
		return 2 > arr.length ? 1 : lcm_iter(arr[0], arr.length - 1, arr);
	}
	
	public static int lcm_lp(final int[] arr) {
		int acc = arr[0];
		for (int i = 1; arr.length > i; ++i)
			acc = (acc * arr[i]) / euclid_lp(acc, arr[i]);
		return acc;
	}
    
    private static int[] baseExpand_iter(final int b, final int n, 
			final int idx, int[] arr) {
		if (0 <= idx) {
			arr[idx] = n % b;
			return baseExpand_iter(b, n / b, idx - 1, arr);
		}
		return arr;
	}
	public static int[] baseExpand_i(final int b, final int n) {
		int len_arr = 0;
		for (int i = n; 0 < i; i = i / b)
			len_arr += 1;
		int[] arr = new int[len_arr];
		return baseExpand_iter(b, n, arr.length - 1, arr);
	}
	
	public static int[] baseExpand_lp(final int b, final int n) {
		int len_arr = 0;
		for (int i = n; 0 < i; i = i / b)
			len_arr += 1;
		int[] arr = new int[len_arr];
		for (int i = n, j = len_arr - 1; 0 < i; i = i / b, --j)
			arr[j] = i % b;
		return arr;
	}
    
    private static int baseTo10_iter(final int acc, final int b, 
			final int idx, final int[] arr) {
		return arr.length <= idx ? acc : baseTo10_iter(acc + arr[idx] * 
			(int)Math.pow(b, arr.length - idx - 1), b, idx + 1, arr);
	}
	public static int baseTo10_i(final int b, final int[] arr) {
		return baseTo10_iter(0, b, 0, arr);
	}
	
	public static int baseTo10_lp(final int b, final int[] arr) {
		int acc = 0;
		for (int i = arr.length - 1, ct = 0; 0 <= i; --i, ++ct)
			acc += arr[i] * (int)Math.pow(b, ct);
		return acc;
	}
    
    private static int[] rangeStep_iter(final int step, final int start,
			final int stop, final int idx, int[] arr) {
		if (arr.length > idx) {
			arr[idx] = start;
			return rangeStep_iter(step, start + step, stop, idx + 1, arr);
		}
		return arr;
	}
	public static int[] rangeStep_i(final int step, final int start,
			final int stop) {
		int len_arr = 0;
		IBoolOp_2Ints obj = step > 0 ? new IBoolOp_2Ints(){ 
			public boolean boolop_2ints(int a, int b) {return a > b; }} : 
			new IBoolOp_2Ints(){ 
			public boolean boolop_2ints(int a, int b) {return a < b; }};
		for (int i = start; obj.boolop_2ints(stop, i); i += step)
			len_arr += 1;
		int[] arr = new int[len_arr];
		return rangeStep_iter(step, start, stop, 0, arr);
	}
	
	public static int[] rangeStep_lp(final int step, final int start,
			final int stop) {
		int len_arr = 0;
		IBoolOp_2Ints obj = step > 0 ? new IBoolOp_2Ints(){ 
			public boolean boolop_2ints(int a, int b) {return a > b; }} : 
			new IBoolOp_2Ints(){ 
			public boolean boolop_2ints(int a, int b) {return a < b; }};
		for (int i = start; obj.boolop_2ints(stop, i); i += step)
			len_arr += 1;
		int[] arr = new int[len_arr];
		for (int i = start, j = 0; obj.boolop_2ints(stop, i); i += step, ++j)
			arr[j] = i;
		return arr;
	}
	
	public static int[] range_i(final int start, final int stop) {
		return rangeStep_i(1, start, stop);
	}
	
	public static int[] range_lp(final int start, final int stop) {
		return rangeStep_lp(1, start, stop);
	}
	
	public static void main(String[] args) {
		System.out.format("fact(%d): %d\n", 5, fact_i(5));
	}
}
