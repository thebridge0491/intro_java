package org.sandbox.intro_java.practice;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

import org.sandbox.intro_java.util.Library;

public class SequenceopsTest {
	private Integer[] ints = {0, 1, 2, 3, 4}, ints_rev = {4, 3, 2, 1, 0};
	private List<Integer> lst_ints = new ArrayList<Integer>(
		Arrays.asList(ints));
	private List<Integer> lst_ints_rev = new ArrayList<Integer>(
		Arrays.asList(ints_rev));
	
    public SequenceopsTest() {
    }
	
	private interface IIndexOfL {
		public <T> int indexOf(T data, List<T> lst, Comparator<T> cmp);
	}
	private interface IIndexOfA {
		public <T> int indexOf(T data, T[] arr, Comparator<T> cmp);
	}
	private interface IReverseL { public <T> void reverse(List<T> lst);
	}
	private interface IReverseA { public <T> void reverse(T[] arr);
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
    public void test_index() {
        IIndexOfL[] objsIdxL = {
        	new IIndexOfL(){public <Integer> int indexOf(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>indexOf_lp(data, lst, cmp); }},
        	new IIndexOfL(){public <Integer> int indexOf(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops_scala.<Integer>indexOf_lp(data, lst, cmp); 
        	}}
        };
        IIndexOfA[] objsIdxA = {
        	new IIndexOfA(){public <Integer> int indexOf(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>indexOf_lp(data, arr, cmp); 
        		}},
        	new IIndexOfA(){public <Integer> int indexOf(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return Sequenceops_scala.<Integer>indexOf_i(data, arr, cmp); 
        		}}
        };
        Integer el = 3;
        
        for (int i = 0; objsIdxL.length > i; ++i) {
        	assertEquals(3, objsIdxL[i].indexOf(el, lst_ints, Library.intCmp));
        	assertEquals(1, objsIdxL[i].indexOf(el, lst_ints_rev, 
				Library.intCmp));
        }
        for (int i = 0; objsIdxA.length > i; ++i) {
        	assertEquals(3, objsIdxA[i].indexOf(el, ints, Library.intCmp));
        	assertEquals(1, objsIdxA[i].indexOf(el, ints_rev, Library.intCmp));
        }
    }
    
	@Test
    public void test_reverse() {
        IReverseL[] objsL = {
        	new IReverseL(){public <Integer> void reverse(List<Integer> lst) {
        		Sequenceops.<Integer>reverse_lp(lst); }},
        	new IReverseL(){public <Integer> void reverse(List<Integer> lst) {
        		Sequenceops_scala.<Integer>reverse_i(lst); }}
        };
        IReverseA[] objsA = {
        	new IReverseA(){public <Integer> void reverse(Integer[] arr) {
        		Sequenceops.<Integer>reverse_lp(arr); }},
        	new IReverseA(){public <Integer> void reverse(Integer[] arr) {
        		Sequenceops_scala.<Integer>reverse_lp(arr); }}
        };
        for (IReverseL obj : objsL) {
			List<Integer> tmp = new ArrayList<Integer>(lst_ints);
			Collections.copy(tmp, lst_ints);
        	obj.reverse(tmp);
        	
        	for (int j = 0; lst_ints.size() > j; ++j)
        		assertEquals(lst_ints_rev.get(j), tmp.get(j));
        }
        for (IReverseA obj : objsA) {
			//Integer[] tmp = (Integer[])Array.newInstance(
			//	ints.getClass().getComponentType(), ints.length);
			Integer[] tmp = new Integer[ints.length];
			for (int i = 0; ints.length > i; ++i)
				tmp[i] = ints[i];
        	obj.reverse(tmp);
        	
        	for (int j = 0; ints.length > j; ++j)
        		assertEquals(ints_rev[j], tmp[j]);
        }
    }
}
