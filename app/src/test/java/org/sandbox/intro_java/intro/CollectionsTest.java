package org.sandbox.intro_java.intro;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;

import org.sandbox.intro_java.util.Library;

public class CollectionsTest {
	//private float tolerance = 2.0f * Float.MIN_VALUE;
	private float epsilon = 1.0e-7f;
	
	private Float[] floats = {25.7f, 0.1f, 78.5f, 52.3f};
	private Integer[] ints = {2, 1, 0, 4, 3};
	private Character[] chars = {'a', 'e', 'k', 'p', 'u', 'k', 'a'};
	
    public CollectionsTest() {
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
    public void test_deques() {
		Deque<Float> deque1 = new java.util.ArrayDeque<Float>(
			Arrays.asList(new Float[0]));
		assertTrue("isEmpty", deque1.isEmpty());
		deque1 = new java.util.ArrayDeque<Float>(Arrays.asList(floats));
		assertEquals("length", floats.length, deque1.size());
		assertEquals("peek", floats[0], deque1.peek(), epsilon);
		assertTrue("offer", deque1.offer(-0.5f));
		assertEquals("poll", floats[0], deque1.poll(), epsilon);
		assertEquals("toString", new String("[0.1, 78.5, 52.3, -0.5]"),
			deque1.toString());
    }
    
    @Test
    public void test_lists() {
		Integer[] nines = {9, 9, 9, 9};
		List<Integer> lst1 = new java.util.ArrayList<Integer>(
			Arrays.asList(new Integer[0]));
		assertTrue("isEmpty", lst1.isEmpty());
		lst1 = new java.util.ArrayList<Integer>(Arrays.asList(ints));
		assertEquals("length", ints.length, lst1.size());
		assertEquals("first", ints[0], lst1.get(0));
		assertEquals("nth", ints[2], lst1.get(2));
		assertEquals("indexOf", 1, lst1.indexOf(ints[1]));
		lst1.addAll(Arrays.asList(nines));
		assertEquals("append", nines.length + ints.length, lst1.size());
		Collections.sort(lst1, new Library.Cmp_rev<Integer>());
		assertEquals("toString", new String("[9, 9, 9, 9, 4, 3, 2, 1, 0]"),
			lst1.toString());
	}
	
	@Test
    public void test_maps() {
		String key1 = "";
		Map<String, Character> map1 = 
			new java.util.HashMap<String, Character>();
		assertTrue("isEmpty", map1.isEmpty());
		for (int i = 0; chars.length > i; ++i) {
            key1 = "ltr " + (i % 5);
            map1.put(key1, chars[i]);
        }
        assertEquals("length", 5, map1.size());
        map1.put(new String("ltr 20"), 'Z');
        assertTrue("contains", map1.containsKey(new String("ltr 2")));
        assertEquals("find", new Character('k'),
			map1.get(new String("ltr 2")));
		map1.remove(new String("ltr 2"));
		assertFalse("remove", map1.containsKey(new String("ltr 2")));
	}
    
    @Test
    public void test_priorqs() {
		Float[] floats2 = {0.0f, 0.0f, 0.0f, 0.0f};
		PriorityQueue<Float> pri_q1 = new java.util.PriorityQueue<Float>();
		PriorityQueue<Float> pri_q2 = new java.util.PriorityQueue<Float>(4,
			new Library.Cmp_rev<Float>());
		assertTrue("isEmpty", pri_q1.isEmpty());
		for (int i = 0; floats.length > i; ++i)
			floats2[i] = floats[i];
		for (Float val : floats) {
			pri_q1.offer(val);
			pri_q2.offer(val);
		}
		Arrays.sort(floats2);
		assertEquals("length", floats2.length, pri_q1.size());
		assertEquals("peek", floats2[0], pri_q1.peek(), epsilon);
		assertEquals("peek (rev)", floats2[floats2.length - 1], pri_q2.peek(),
			epsilon);
		assertEquals("poll", floats2[0], pri_q1.poll(), epsilon);
		assertEquals("poll (rev)", floats2[floats2.length - 1], pri_q2.poll(),
			epsilon);
		assertTrue("offer", pri_q1.offer(-0.5f));
		assertTrue("offer (rev)", pri_q2.offer(-0.5f));
		assertEquals("toString", new String("[-0.5, 25.7, 78.5, 52.3]"),
			Arrays.toString(pri_q1.toArray()));
		assertEquals("toString (rev)", new String("[52.3, 0.1, 25.7, -0.5]"),
			Arrays.toString(pri_q2.toArray()));
    }
	
	@Test
    public void test_trees() {
		String key1 = "";
		Map<String, Character> tree1 = 
			new java.util.TreeMap<String, Character>();
		assertTrue("isEmpty", tree1.isEmpty());
		for (int i = 0; chars.length > i; ++i) {
            key1 = "ltr " + (i % 5);
            tree1.put(key1, chars[i]);
        }
        assertEquals("length", 5, tree1.size());
        tree1.put(new String("ltr 20"), 'Z');
        assertTrue("contains", tree1.containsKey(new String("ltr 2")));
        assertEquals("find", new Character('k'),
			tree1.get(new String("ltr 2")));
		tree1.remove(new String("ltr 2"));
		assertFalse("remove", tree1.containsKey(new String("ltr 2")));
	}
}
