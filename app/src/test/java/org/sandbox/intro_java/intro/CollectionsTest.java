package org.sandbox.intro_java.intro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void test_deques() {
		Deque<Float> deque1 = new java.util.ArrayDeque<Float>(
			Arrays.asList(new Float[0]));
		assertTrue(deque1.isEmpty(), "isEmpty");
		deque1 = new java.util.ArrayDeque<Float>(Arrays.asList(floats));
		assertEquals(floats.length, deque1.size(), "length");
		assertEquals(floats[0], deque1.peek(), epsilon, "peek");
		assertTrue(deque1.offer(-0.5f), "offer");
		assertEquals(floats[0], deque1.poll(), epsilon, "poll");
		assertEquals(new String("[0.1, 78.5, 52.3, -0.5]"),
			deque1.toString(), "toString");
    }
    
    @Test
    public void test_lists() {
		Integer[] nines = {9, 9, 9, 9};
		List<Integer> lst1 = new java.util.ArrayList<Integer>(
			Arrays.asList(new Integer[0]));
		assertTrue(lst1.isEmpty(), "isEmpty");
		lst1 = new java.util.ArrayList<Integer>(Arrays.asList(ints));
		assertEquals(ints.length, lst1.size(), "length");
		assertEquals(ints[0], lst1.get(0), "first");
		assertEquals(ints[2], lst1.get(2), "nth");
		assertEquals(1, lst1.indexOf(ints[1]), "indexOf");
		lst1.addAll(Arrays.asList(nines));
		assertEquals(nines.length + ints.length, lst1.size(), "append");
		Collections.sort(lst1, new Library.Cmp_rev<Integer>());
		assertEquals(new String("[9, 9, 9, 9, 4, 3, 2, 1, 0]"),
			lst1.toString(), "toString");
	}
	
	@Test
    public void test_maps() {
		String key1 = "";
		Map<String, Character> map1 = 
			new java.util.HashMap<String, Character>();
		assertTrue(map1.isEmpty(), "isEmpty");
		for (int i = 0; chars.length > i; ++i) {
            key1 = "ltr " + (i % 5);
            map1.put(key1, chars[i]);
        }
        assertEquals(5, map1.size(), "length");
        map1.put(new String("ltr 20"), 'Z');
        assertTrue(map1.containsKey(new String("ltr 2")), "contains");
        assertEquals(Character.valueOf('k'),
			map1.get(new String("ltr 2")), "find");
		map1.remove(new String("ltr 2"));
		assertFalse(map1.containsKey(new String("ltr 2")), "remove");
	}
    
    @Test
    public void test_priorqs() {
		Float[] floats2 = {0.0f, 0.0f, 0.0f, 0.0f};
		PriorityQueue<Float> pri_q1 = new java.util.PriorityQueue<Float>();
		PriorityQueue<Float> pri_q2 = new java.util.PriorityQueue<Float>(4,
			new Library.Cmp_rev<Float>());
		assertTrue(pri_q1.isEmpty(), "isEmpty");
		for (int i = 0; floats.length > i; ++i)
			floats2[i] = floats[i];
		for (Float val : floats) {
			pri_q1.offer(val);
			pri_q2.offer(val);
		}
		Arrays.sort(floats2);
		assertEquals(floats2.length, pri_q1.size(), "length");
		assertEquals(floats2[0], pri_q1.peek(), epsilon, "peek");
		assertEquals(floats2[floats2.length - 1], pri_q2.peek(),
			epsilon, "peek (rev)");
		assertEquals(floats2[0], pri_q1.poll(), epsilon, "poll");
		assertEquals(floats2[floats2.length - 1], pri_q2.poll(),
			epsilon, "poll (rev)");
		assertTrue(pri_q1.offer(-0.5f), "offer");
		assertTrue(pri_q2.offer(-0.5f), "offer (rev)");
		assertEquals(new String("[-0.5, 25.7, 78.5, 52.3]"),
			Arrays.toString(pri_q1.toArray()), "toString");
		assertEquals(new String("[52.3, 0.1, 25.7, -0.5]"),
			Arrays.toString(pri_q2.toArray()), "toString (rev)");
    }
	
	@Test
    public void test_trees() {
		String key1 = "";
		Map<String, Character> tree1 = 
			new java.util.TreeMap<String, Character>();
		assertTrue(tree1.isEmpty(), "isEmpty");
		for (int i = 0; chars.length > i; ++i) {
            key1 = "ltr " + (i % 5);
            tree1.put(key1, chars[i]);
        }
        assertEquals(5, tree1.size(), "length");
        tree1.put(new String("ltr 20"), 'Z');
        assertTrue(tree1.containsKey(new String("ltr 2")), "contains");
        assertEquals(Character.valueOf('k'),
			tree1.get(new String("ltr 2")), "find");
		tree1.remove(new String("ltr 2"));
		assertFalse(tree1.containsKey(new String("ltr 2")), "remove");
	}
}
