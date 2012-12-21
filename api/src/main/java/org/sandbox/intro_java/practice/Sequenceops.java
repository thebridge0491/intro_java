package org.sandbox.intro_java.practice;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** DocComment:
 * <p>Brief description.</p> */
public class Sequenceops {
	private static final Logger pracLogger = LoggerFactory.getLogger("prac");
    
    public interface IOp_1Int<T> { public T op_1int(int n);
	}
    
    public interface IOp_2Items<T> { public T op_2items(T a, T b);
	}
	
	public static <T> void swapItems(int a, int b, List<T> lst) {
		T swap = lst.get(a); lst.set(a, lst.get(b)); lst.set(b, swap);
	}
	
	public static <T> List<T> tabulate_lp(IOp_1Int<T> obj, final int cnt) {
		List<T> new_lst = new ArrayList<T>(cnt);
		for (int i = 0; cnt > i; ++i)
			new_lst.add(obj.op_1int(i));
		return new_lst;
	}
	
	private static <T> Object[] index_find_lp(T data, List<T> lst,
			Comparator<? super T> cmp) {
		for (int i = 0; lst.size() > i; ++i)
			if (0 == cmp.compare(data, lst.get(i)))
				return new Object[]{new Integer(i), lst.get(i)};
		return new Object[]{new Integer(-1), null};
	}
	
	public static <T> int indexOf_lp(T data, List<T> lst,
			Comparator<? super T> cmp) {
		return (int)index_find_lp(data, lst, cmp)[0];
	}
	
	public static <T> T find_lp(T data, List<T> lst,
			Comparator<? super T> cmp) {
		@SuppressWarnings("unchecked")
		T item = (T)index_find_lp(data, lst, cmp)[1];
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] minmax_lp(List<T> lst, Comparator<? super T> cmp) {
		Object[] minmax_arr = {lst.get(0), lst.get(0)};
		for (T val : lst)
			if (0 > cmp.compare(val, (T)minmax_arr[0]))
				minmax_arr[0] = val;
			else if (0 < cmp.compare(val, (T)minmax_arr[1]))
				minmax_arr[1] = val;
		return (T[])minmax_arr;
	}
	
	public static <T> T min_lp(List<T> lst, Comparator<? super T> cmp) {
		return minmax_lp(lst, cmp)[0];
	}
	
	public static <T> T max_lp(List<T> lst, Comparator<? super T> cmp) {
		return minmax_lp(lst, cmp)[1];
	}
	
	public static <T> void reverse_lp(List<T> lst) {
		int i = 0, j = lst.size() - 1;
		pracLogger.info("reverse_lp(List)");
		
		while (j > i)
			swapItems(i++, j--, lst);
	}
	
	public static <T> List<T> copyOf_lp(List<T> lst) {
		List<T> new_lst = new ArrayList<T>(lst.size());
		for (T val : lst)
			new_lst.add(val);
		return new_lst;
	}
	
	public static <T> T foreach_lp(IOp_2Items<T> obj, List<T> lst, T acc) {
		for (T val : lst)
			acc = obj.op_2items(val, acc);
		return acc;
	}
	
	public static <T> boolean isOrdered_lp(List<T> lst,
			Comparator<? super T> cmp) {
		for (int i = 1; lst.size() > i; ++i)
			if (0 < cmp.compare(lst.get(i - 1), lst.get(i)))
				return false;
		return true;
	}
	
	
	private static <T> int qpartition(List<T> lst, int lo, int hi, 
			Comparator<? super T> cmp) {
		int lwr = lo, upr = hi;
		
		while (lwr < upr) {
			while (0 >= cmp.compare(lst.get(lwr), lst.get(lo)) && lwr < upr)
				++lwr;
			while (0 < cmp.compare(lst.get(upr), lst.get(lo)))
				--upr;
			if (lwr < upr)
				swapItems(lwr, upr, lst);
		}
		swapItems(lo, upr, lst);
		return upr;
	}
	public static <T> void quickSort_lp(List<T> lst, int lo, int hi,
			Comparator<? super T> cmp) {
		java.util.Random rnd = new java.util.Random(
			System.currentTimeMillis());
		if (hi > lo) {
			int rNdx = rnd.nextInt(hi - lo + 1) + lo;
			swapItems(lo, rNdx, lst);
			int split = qpartition(lst, lo, hi, cmp);
			quickSort_lp(lst, lo, split - 1, cmp);
			quickSort_lp(lst, split + 1, hi, cmp);
		}
	}
	
	
	public static <T> List<T> append_lp(List<T> lst1, List<T> lst2) {
		List<T> new_lst = new ArrayList<T>(lst1.size() + lst2.size());
		for (T val : lst1)
			new_lst.add(val);
		for (T val : lst2)
			new_lst.add(val);
		return new_lst;
	}
	
	public static <T> List<T> interleave_lp(List<T> lst1, List<T> lst2) {
		List<T> new_lst = new ArrayList<T>(lst1.size() + lst2.size());
		for (int i = 0, j = 0; lst1.size() > i || lst2.size() > j; ++i, ++j) {
			if (lst1.size() > i)
				new_lst.add(lst1.get(i));
			if (lst2.size() > j)
				new_lst.add(lst2.get(j));
		}
		return new_lst;
	}
	
	public static void main(String[] args) {
        StringBuilder strBldr = new StringBuilder();
        Integer[] arr = {0, 1, 2, 3};
        List<Integer> ints = new ArrayList<Integer>(Arrays.asList(arr));
        reverse_lp(ints);
        
        for (Integer ival : ints)
            strBldr.append(((0 < strBldr.length()) ? ", " : "") + ival);
        System.out.format("reverse([0, 1, 2, 3]): %s\n", strBldr.toString());
	}
}
