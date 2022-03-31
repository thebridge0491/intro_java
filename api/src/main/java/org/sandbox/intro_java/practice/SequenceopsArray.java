package org.sandbox.intro_java.practice;

import java.util.*;
import java.lang.reflect.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DocComment:
 * <p>Brief description.</p> */
public class SequenceopsArray {
	private static final Logger pracLogger = LoggerFactory.getLogger("prac");
	
	public static <T> void swapItems(int a, int b, T[] arr) {
		T swap = arr[a]; arr[a] = arr[b]; arr[b] = swap;
	}
	
	public static <T> T[] tabulate_lp(Sequenceops.IOp_1Int<T> obj,
			final int cnt, final T[] empty) {
		@SuppressWarnings("unchecked")
		T[] new_arr = (T[])Array.newInstance(
			empty.getClass().getComponentType(), cnt);
		for (int i = 0; new_arr.length > i; ++i)
			new_arr[i] = obj.op_1int(i);
		return new_arr;
	}
	
	public static <T> Object[] index_find_lp(T data, T[] arr,
			Comparator<? super T> cmp) {
		for (int i = 0; arr.length > i; ++i)
			if (0 == cmp.compare(data, arr[i]))
				return new Object[]{Integer.valueOf(i), arr[i]};
		return new Object[]{Integer.valueOf(-1), null};
	}
	
	public static <T> int indexOf_lp(T data, T[] arr,
			Comparator<? super T> cmp) {
		return (int)index_find_lp(data, arr, cmp)[0];
	}
	
	public static <T> T find_lp(T data, T[] arr,
			Comparator<? super T> cmp) {
		@SuppressWarnings("unchecked")
		T item = (T)index_find_lp(data, arr, cmp)[1];
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] minmax_lp(T[] arr, Comparator<? super T> cmp) {
		Object[] minmax_arr = {arr[0], arr[0]};
		for (T val : arr)
			if (0 > cmp.compare(val, (T)minmax_arr[0]))
				minmax_arr[0] = val;
			else if (0 < cmp.compare(val, (T)minmax_arr[1]))
				minmax_arr[1] = val;
		return (T[])minmax_arr;
	}
	
	public static <T> T min_lp(T[] arr, Comparator<? super T> cmp) {
		return minmax_lp(arr, cmp)[0];
	}
	
	public static <T> T max_lp(T[] arr, Comparator<? super T> cmp) {
		return minmax_lp(arr, cmp)[1];
	}
	
	public static <T> void reverse_lp(T[] arr) {
		int i = 0, j = arr.length - 1;
		pracLogger.info("reverse_lp()");
		
		while (j > i)
			swapItems(i++, j--, arr);
	}
	
	public static <T> T[] copyOf_lp(T[] arr) {
		@SuppressWarnings("unchecked")
		T[] new_arr = (T[])Array.newInstance(
			arr.getClass().getComponentType(), arr.length);
		for (int i = 0; arr.length > i; ++i)
			new_arr[i] = arr[i];
		return new_arr;
	}
	
	public static <T> T foreach_lp(Sequenceops.IOp_2Items<T> obj, T[] arr, 
			T acc) {
		for (T val : arr)
			acc = obj.op_2items(val, acc);
		return acc;
	}
	
	public static <T> boolean isOrdered_lp(T[] arr,
			Comparator<? super T> cmp) {
		for (int i = 1; arr.length > i; ++i)
			if (0 < cmp.compare(arr[i - 1], arr[i]))
				return false;
		return true;
	}
	
	
	private static <T> int qpartition(T[] arr, int lo, int hi, 
			Comparator<? super T> cmp) {
		int lwr = lo, upr = hi;
		
		while (lwr < upr) {
			while (0 >= cmp.compare(arr[lwr], arr[lo]) && lwr < upr)
				++lwr;
			while (0 < cmp.compare(arr[upr], arr[lo]))
				--upr;
			if (lwr < upr)
				swapItems(lwr, upr, arr);
		}
		swapItems(lo, upr, arr);
		return upr;
	}
	public static <T> void quickSort_lp(T[] arr, int lo, int hi,
			Comparator<? super T> cmp) {
		java.util.Random rnd = new java.util.Random(
			System.currentTimeMillis());
		if (hi > lo) {
			int rNdx = rnd.nextInt(hi - lo + 1) + lo;
			swapItems(lo, rNdx, arr);
			int split = qpartition(arr, lo, hi, cmp);
			quickSort_lp(arr, lo, split - 1, cmp);
			quickSort_lp(arr, split + 1, hi, cmp);
		}
	}
	
	
	public static <T> T[] append_lp(T[] arr1, T[] arr2) {
		@SuppressWarnings("unchecked")
		T[] new_arr = (T[])Array.newInstance(
			arr1.getClass().getComponentType(), arr1.length + arr2.length);
		for (int i = 0; arr1.length > i; ++i)
			new_arr[i] = arr1[i];
		for (int i = 0; arr2.length > i; ++i)
			new_arr[arr1.length + i] = arr2[i];
		return new_arr;
	}
	
	public static <T> T[] interleave_lp(T[] arr1, T[] arr2) {
		@SuppressWarnings("unchecked")
		T[] new_arr = (T[])Array.newInstance(
			arr1.getClass().getComponentType(), arr1.length + arr2.length);
		for (int i = 0, j = 0, k = 0; arr1.length > i || arr2.length > j; 
				++i, ++j) {
			if (arr1.length > i)
				new_arr[k++] = arr1[i];
			if (arr2.length > j)
				new_arr[k++] = arr2[j];
		}
		return new_arr;
	}
	
	public static void main(String[] args) {
        StringBuilder strBldr = new StringBuilder();
        Integer[] ints = {0, 1, 2, 3};
        reverse_lp(ints);
        
        for (Integer ival : Arrays.asList(ints))
            strBldr.append(((0 < strBldr.length()) ? ", " : "") + ival);
        System.out.format("reverse([0, 1, 2, 3]): %s\n", strBldr.toString());
	}
}
