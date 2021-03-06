package org.sandbox.intro_java.intro;

import java.util.Comparator;
import java.util.Arrays;

/** DocComment:
 * <p>Brief description.</p> */
public final class Library {
    public static class Cmp<T extends Comparable<T>> implements
            Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
    
    public static class Cmp_rev<T extends Comparable<T>> implements
            Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return -(o1.compareTo(o2));
            //return (o1.key < o2.key) ? -1 : ((o1.key > o2.key) ? 1 : 0);
        }
    }
    
	public static Comparator<Float> floatCmp = new Cmp<Float>();
	public static Comparator<Integer> intCmp = new Cmp<Integer>();
    
    public static <T> String mkStringInit(java.util.Collection<T> coll,
            String beg, String sep, String stop) {
        StringBuilder strBldr = new StringBuilder();
        
        for (T ival : coll)
            strBldr.append(((0 < strBldr.length()) ? sep : "") + ival);
        return beg + strBldr.toString() + stop;
    }
    
    public static <T> String mkString(java.util.Collection<T> coll) {
        return mkStringInit(coll, "[", ", ", "]");
    }
    
    public static <K, V> String mkStringInit(java.util.Map<K, V> map,
            String beg, String sep, String stop, String mapsep) {
        java.util.Collection<String> coll = new java.util.ArrayList<String>();
        
        for (java.util.Map.Entry<K, V> entry : map.entrySet())
            coll.add(entry.getKey() + mapsep + entry.getValue());
        return mkStringInit(coll, beg, sep, stop);
    }
    
    public static Boolean in_epsilon(Float tolerance, Float a, Float b) {
		Float delta = Math.abs(tolerance);
		//return (a - delta) <= b && (a + delta) >= b;
		return !((a + delta) < b) && !((b + delta) < a);
	}
	
	//public static float[][] cartesian_prod_floats(float[] arr1,
	//		float[] arr2) {
		//float[][] arr_prod = new float[arr1.length * arr2.length][2];
	public static <T> T[][] cartesian_prod(T[] arr1, T[] arr2) {
		@SuppressWarnings("unchecked")
		T[][] arr_prod = (T[][]) java.lang.reflect.Array.newInstance(
			arr1.getClass().getComponentType(), arr1.length * arr2.length, 2);
		for (int i = 0, idxX = 0; arr1.length > i; ++i)
			for (int j = 0; arr2.length > j; ++j) {
				idxX = j + (i * arr2.length);
				arr_prod[idxX][0] = arr1[i];
				arr_prod[idxX][1] = arr2[j];
			}
		return arr_prod;
	}
	
	public static void main(String[] args) {
		Integer[] arr1 = {0, 1, 2}, arr2 = {10, 20, 30};
        StringBuilder strBldr = new StringBuilder();
		
        for (Integer[] arr : cartesian_prod(arr1, arr2))
            strBldr.append(((0 < strBldr.length()) ? ", " : "") + 
                Arrays.toString(arr));
		System.out.format("cartesian_prod(%s, %s): [%s]\n",
            Arrays.toString(arr1), Arrays.toString(arr2), strBldr.toString());
	}
}
