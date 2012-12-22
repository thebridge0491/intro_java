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
	
	private interface ITabulateL {
		public <T> List<T> tabulate(Sequenceops.IOp_1Int<T> obj, int cnt);
	}
	private interface IIndexOfL {
		public <T> int indexOf(T data, List<T> lst, Comparator<T> cmp);
	}
	private interface IFindL {
		public <T> T find(T data, List<T> lst, Comparator<T> cmp);
	}
	private interface IMinL {
		public <T> T min(List<T> lst, Comparator<T> cmp);
	}
	private interface IMaxL {
		public <T> T max(List<T> lst, Comparator<T> cmp);
	}
	private interface IReverseL { public <T> void reverse(List<T> lst);
	}
	private interface ICopyOfL { public <T> List<T> copyOf(List<T> lst);
	}
	private interface IForeachL { public <T> T foreach(
		Sequenceops.IOp_2Items<T> obj, List<T> lst, T acc);
	}
	private interface IIsOrderedL {
		public <T> boolean isOrdered(List<T> lst, Comparator<T> cmp);
	}
	private interface ISortL {
		public <T> void sort(List<T> lst, int lo, int hi, Comparator<T> cmp);
	}
	private interface IAppendL { public <T> List<T> append(List<T> lst1,
		List<T> lst2);
	}
	private interface IInterleaveL { public <T> List<T> interleave(
		List<T> lst1, List<T> lst2);
	}
	
	private interface ITabulateA {
		public <T> T[] tabulate(Sequenceops.IOp_1Int<T> obj, int cnt, 
			T[] arr);
	}
	private interface IIndexOfA {
		public <T> int indexOf(T data, T[] arr, Comparator<T> cmp);
	}
	private interface IFindA {
		public <T> T find(T data, T[] arr, Comparator<T> cmp);
	}
	private interface IMinA { public <T> T min(T[] arr, Comparator<T> cmp);
	}
	private interface IMaxA { public <T> T max(T[] arr, Comparator<T> cmp);
	}
	private interface IReverseA { public <T> void reverse(T[] arr);
	}
	private interface ICopyOfA { public <T> T[] copyOf(T[] arr);
	}
	private interface IForeachA { public <T> T foreach(
		Sequenceops.IOp_2Items<T> obj, T[] arr, T acc);
	}
	private interface IIsOrderedA {
		public <T> boolean isOrdered(T[] arr, Comparator<T> cmp);
	}
	private interface ISortA {
		public <T> void sort(T[] arr, int lo, int hi, Comparator<T> cmp);
	}
	private interface IAppendA { public <T> T[] append(T[] arr1, T[] arr2);
	}
	private interface IInterleaveA { public <T> T[] interleave(
		T[] arr1, T[] arr2);
	}
	
	private Sequenceops.IOp_1Int<Integer> add1 = 
		new Sequenceops.IOp_1Int<Integer>(){
			public Integer op_1int(int n) { return n; }
	};
	private Sequenceops.IOp_1Int<Integer> sub1 = 
		new Sequenceops.IOp_1Int<Integer>(){
			public Integer op_1int(int n) { return 4 - n; }
	};
	private Sequenceops.IOp_2Items<Integer> sum_el_acc = 
		new Sequenceops.IOp_2Items<Integer>(){
			public Integer op_2items(Integer a, Integer b) { return a + b; }
	};

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
    public void test_tabulate() {
        ITabulateL[] objsL = {
        	new ITabulateL(){public <Integer> List<Integer> tabulate(
					Sequenceops.IOp_1Int<Integer> obj, int cnt) {
        		return Sequenceops.<Integer>tabulate_lp(obj, cnt); }}
        };
        ITabulateA[] objsA = {
        	new ITabulateA(){public <Integer> Integer[] tabulate(
					Sequenceops.IOp_1Int<Integer> obj, int cnt, 
					Integer[] arr) {
        		return SequenceopsArray.<Integer>tabulate_lp(obj, cnt, arr); }}
        };
        for (ITabulateL obj : objsL) {
			assertEquals(lst_ints, obj.tabulate(add1, 5));
			assertEquals(lst_ints_rev, obj.tabulate(sub1, 5));
        }
        for (ITabulateA obj : objsA) {
			assertArrayEquals(ints, obj.tabulate(add1, 5, new Integer[0]));
			assertArrayEquals(ints_rev, obj.tabulate(sub1, 5, new Integer[0]));
        }
    }
    
	@Test
    public void test_index_find() {
        IIndexOfL[] objsIdxL = {
        	new IIndexOfL(){public <Integer> int indexOf(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>indexOf_lp(data, lst, cmp); }},
        	new IIndexOfL(){public <Integer> int indexOf(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops_scala.<Integer>indexOf_lp(data, lst, cmp); 
        	}}
        };
        IFindL[] objsFindL = {
        	new IFindL(){public <Integer> Integer find(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>find_lp(data, lst, cmp); }},
        	new IFindL(){public <Integer> Integer find(Integer data,
        		List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>find_lp(data, lst, cmp); }}
        };
        IIndexOfA[] objsIdxA = {
        	new IIndexOfA(){public <Integer> int indexOf(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>indexOf_lp(data, arr, cmp); 
        		}},
        	new IIndexOfA(){public <Integer> int indexOf(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return Sequenceops_scala.<Integer>indexOf_i(data, arr, cmp); 
        		}}
        };
        IFindA[] objsFindA = {
        	new IFindA(){public <Integer> Integer find(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>find_lp(data, arr, cmp); }},
        	new IFindA(){public <Integer> Integer find(Integer data,
        		Integer[] arr, Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>find_lp(data, arr, cmp); }}
        };
        Integer el = 3;
        
        for (int i = 0; objsIdxL.length > i; ++i) {
        	assertEquals(3, objsIdxL[i].indexOf(el, lst_ints, Library.intCmp));
        	assertEquals(1, objsIdxL[i].indexOf(el, lst_ints_rev, 
				Library.intCmp));
        	assertEquals(el, objsFindL[i].find(el, lst_ints, Library.intCmp));
        	assertEquals(el, objsFindL[i].find(el, lst_ints_rev, 
				Library.intCmp));
        }
        for (int i = 0; objsIdxA.length > i; ++i) {
        	assertEquals(3, objsIdxA[i].indexOf(el, ints, Library.intCmp));
        	assertEquals(1, objsIdxA[i].indexOf(el, ints_rev, Library.intCmp));
        	assertEquals(el, objsFindA[i].find(el, ints, Library.intCmp));
        	assertEquals(el, objsFindA[i].find(el, ints_rev, Library.intCmp));
        }
    }
    
	@Test
    public void test_min_max() {
        IMinL[] objsMinL = {
        	new IMinL(){public <Integer> Integer min(List<Integer> lst, 
					Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>min_lp(lst, cmp); }}
        };
        IMaxL[] objsMaxL = {
        	new IMaxL(){public <Integer> Integer max(List<Integer> lst, 
					Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>max_lp(lst, cmp); }}
        };
        IMinA[] objsMinA = {
        	new IMinA(){public <Integer> Integer min(Integer[] arr, 
					Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>min_lp(arr, cmp); }}
        };
        IMaxA[] objsMaxA = {
        	new IMaxA(){public <Integer> Integer max(Integer[] arr, 
					Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>max_lp(arr, cmp); }}
        };
        Integer lo = 0, hi = 4;
        
        for (int i = 0; objsMinL.length > i; ++i) {
        	assertEquals(lo, objsMinL[i].min(lst_ints, Library.intCmp));
        	assertEquals(lo, objsMinL[i].min(lst_ints_rev, Library.intCmp));
        	assertEquals(hi, objsMaxL[i].max(lst_ints, Library.intCmp));
        	assertEquals(hi, objsMaxL[i].max(lst_ints_rev, Library.intCmp));
        }
        for (int i = 0; objsMinA.length > i; ++i) {
        	assertEquals(lo, objsMinA[i].min(ints, Library.intCmp));
        	assertEquals(lo, objsMinA[i].min(ints_rev, Library.intCmp));
        	assertEquals(hi, objsMaxA[i].max(ints, Library.intCmp));
        	assertEquals(hi, objsMaxA[i].max(ints_rev, Library.intCmp));
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
        		SequenceopsArray.<Integer>reverse_lp(arr); }},
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
    
	@Test
    public void test_copyOf() {
        ICopyOfL[] objsL = {
        	new ICopyOfL(){public <Integer> List<Integer> copyOf(
					List<Integer> lst) {
        		return Sequenceops.<Integer>copyOf_lp(lst); }}
        };
        ICopyOfA[] objsA = {
        	new ICopyOfA(){public <Integer> Integer[] copyOf(Integer[] arr) {
        		return SequenceopsArray.<Integer>copyOf_lp(arr); }}
        };
        for (ICopyOfL obj : objsL) {
			List<Integer> tmp = new ArrayList<Integer>(lst_ints);
			List<Integer> tmp_rev = new ArrayList<Integer>(lst_ints_rev);
			assertEquals(tmp, obj.copyOf(lst_ints));
			assertEquals(tmp_rev, obj.copyOf(lst_ints_rev));
        }
        for (ICopyOfA obj : objsA) {
			Integer[] tmp = java.util.Arrays.copyOf(ints, ints.length);
        	Integer[] tmp_rev = java.util.Arrays.copyOf(ints_rev, 
				ints_rev.length);
        	
			assertArrayEquals(tmp, obj.copyOf(ints));
			assertArrayEquals(tmp_rev, obj.copyOf(ints_rev));
        }
    }
    
	@Test
    public void test_foreach() {
        IForeachL[] objsL = {
        	new IForeachL(){public <Integer> Integer foreach(
					Sequenceops.IOp_2Items<Integer> obj, 
					List<Integer> lst, Integer acc) {
        		return Sequenceops.<Integer>foreach_lp(obj, lst, 
					acc); }}
        };
        IForeachA[] objsA = {
        	new IForeachA(){public <Integer> Integer foreach(
					Sequenceops.IOp_2Items<Integer> obj, 
					Integer[] arr, Integer acc) {
        		return SequenceopsArray.<Integer>foreach_lp(obj, arr, 
					acc); }}
        };
        for (IForeachL obj : objsL) {
			assertEquals(10, (int)obj.foreach(sum_el_acc, lst_ints, 0));
			assertEquals(10, (int)obj.foreach(sum_el_acc, lst_ints_rev, 0));
        }
        for (IForeachA obj : objsA) {
			assertEquals(10, (int)obj.foreach(sum_el_acc, ints, 0));
			assertEquals(10, (int)obj.foreach(sum_el_acc, ints_rev, 0));
        }
    }
    
	@Test
    public void test_sort() {
        IIsOrderedL[] objsChkL = {
        	new IIsOrderedL(){public <Integer> boolean isOrdered(
					List<Integer> lst, Comparator<Integer> cmp) {
        		return Sequenceops.<Integer>isOrdered_lp(lst, cmp); }}
        };
        ISortL[] objsSortL = {
        	new ISortL(){public <Integer> void sort(
					List<Integer> lst, int lo, int hi, 
					Comparator<Integer> cmp) {
        		Sequenceops.<Integer>quickSort_lp(lst, lo, hi, cmp); }}
        };
        IIsOrderedA[] objsChkA = {
        	new IIsOrderedA(){public <Integer> boolean isOrdered(
					Integer[] arr, Comparator<Integer> cmp) {
        		return SequenceopsArray.<Integer>isOrdered_lp(arr, cmp); }}
        };
        ISortA[] objsSortA = {
        	new ISortA(){public <Integer> void sort(
					Integer[] arr, int lo, int hi, Comparator<Integer> cmp) {
        		SequenceopsArray.<Integer>quickSort_lp(arr, lo, hi, cmp); }}
        };
        Library.Cmp_rev<Integer> intCmp_rev = new Library.Cmp_rev<Integer>();
        Integer ints1[] = {9, 9, 9, 0, 3, 4}, ints2[] = {4, 0, 9, 9, 9, 3};
        List<Integer> lst_ints1 = new ArrayList<Integer>(
			Arrays.asList(ints1));
         List<Integer> lst_ints2 = new ArrayList<Integer>(
			Arrays.asList(ints2));
        
        for (ISortL objSort : objsSortL) {
			List<Integer> tmp = Sequenceops.<Integer>copyOf_lp(lst_ints1);
			List<Integer> tmp_rev = Sequenceops.<Integer>copyOf_lp(lst_ints2);
			objSort.sort(tmp, 0, tmp.size() - 1, Library.intCmp);
			objSort.sort(tmp_rev, 0, tmp_rev.size() - 1, intCmp_rev);
			
			for (IIsOrderedL objChk : objsChkL) {
				assertEquals(true, objChk.isOrdered(tmp, Library.intCmp));
				assertEquals(false, objChk.isOrdered(tmp_rev, Library.intCmp));
				
				assertEquals(false, objChk.isOrdered(tmp, intCmp_rev));
				assertEquals(true, objChk.isOrdered(tmp_rev, intCmp_rev));
			}
		}
        for (ISortA objSort : objsSortA) {
			Integer tmp[] = SequenceopsArray.<Integer>copyOf_lp(ints1);
			Integer tmp_rev[] = SequenceopsArray.<Integer>copyOf_lp(ints2);
			objSort.sort(tmp, 0, tmp.length - 1, Library.intCmp);
			objSort.sort(tmp_rev, 0, tmp_rev.length - 1, intCmp_rev);
			
			for (IIsOrderedA objChk : objsChkA) {
				assertEquals(true, objChk.isOrdered(tmp, Library.intCmp));
				assertEquals(false, objChk.isOrdered(tmp_rev, Library.intCmp));
				
				assertEquals(false, objChk.isOrdered(tmp, intCmp_rev));
				assertEquals(true, objChk.isOrdered(tmp_rev, intCmp_rev));
			}
		}
    }
    
	@Test
    public void test_append() {
        IAppendL[] objsL = {
        	new IAppendL(){public <Integer> List<Integer> append(
					List<Integer> lst1, List<Integer> lst2) {
        		return Sequenceops.<Integer>append_lp(lst1, lst2); }}
        };
        IAppendA[] objsA = {
        	new IAppendA(){public <Integer> Integer[] append(
					Integer[] arr1, Integer[] arr2) {
        		return SequenceopsArray.<Integer>append_lp(arr1, arr2); }}
        };
        for (IAppendL obj : objsL) {
			List<Integer> tmp1 = new ArrayList<Integer>(lst_ints);
			List<Integer> tmp2 = new ArrayList<Integer>(lst_ints_rev);
			tmp1.addAll(lst_ints_rev);
			tmp2.addAll(lst_ints);
			assertEquals(tmp1, obj.append(lst_ints, lst_ints_rev));
			assertEquals(tmp2, obj.append(lst_ints_rev, lst_ints));
        }
        for (IAppendA obj : objsA) {
			Integer[] tmp1 = java.util.Arrays.copyOf(ints, 
				ints.length + ints_rev.length);
			Integer[] tmp2 = java.util.Arrays.copyOf(ints_rev, 
				ints.length + ints_rev.length);
			for (int i = 0; ints_rev.length > i; ++i)
				tmp1[ints.length + i] = ints_rev[i];
			for (int i = 0; ints.length > i; ++i)
				tmp2[ints_rev.length + i] = ints[i];
			
			assertArrayEquals(tmp1, obj.append(ints, ints_rev));
			assertArrayEquals(tmp2, obj.append(ints_rev, ints));
        }
    }
    
	@Test
    public void test_interleave() {
        IInterleaveL[] objsL = {
        	new IInterleaveL(){public <Integer> List<Integer> interleave(
					List<Integer> lst1, List<Integer> lst2) {
        		return Sequenceops.<Integer>interleave_lp(lst1, lst2); }}
        };
        IInterleaveA[] objsA = {
        	new IInterleaveA(){public <Integer> Integer[] interleave(
					Integer[] arr1, Integer[] arr2) {
        		return SequenceopsArray.<Integer>interleave_lp(arr1, arr2); }}
        };
        Integer[] res1 = {0, 4, 1, 3, 2, 2, 3, 1, 4, 0};
        Integer[] res2 = {4, 0, 3, 1, 2, 2, 1, 3, 0, 4};
        for (IInterleaveL obj : objsL) {
			assertEquals(Arrays.asList(res1), obj.interleave(lst_ints, 
				lst_ints_rev));
			assertEquals(Arrays.asList(res2), obj.interleave(lst_ints_rev, 
				lst_ints));
        }
        for (IInterleaveA obj : objsA) {
			assertArrayEquals(res1, obj.interleave(ints, ints_rev));
			assertArrayEquals(res2, obj.interleave(ints_rev, ints));
        }
    }
}
