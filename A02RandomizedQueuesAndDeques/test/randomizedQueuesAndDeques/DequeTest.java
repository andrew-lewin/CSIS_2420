package randomizedQueuesAndDeques;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * 
 * @author Joshua Hardesty
 * @author Andrew Lewin
 */
public class DequeTest {

	private Deque<String> testStringDeque = new Deque<>();
	private Deque<Integer> testIntegerDeque = new Deque<>();

	@Test
	public void testIsEmpty() {  
		assertTrue(testStringDeque.isEmpty());
		assertTrue(testIntegerDeque.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {
		testStringDeque.addFirst("test");
		testIntegerDeque.addFirst(1);
		
		assertFalse(testStringDeque.isEmpty());
		assertFalse(testIntegerDeque.isEmpty());
	}

	@Test
	public void testEmptySize() {
		assertEquals(0, testStringDeque.size());
		assertEquals(0, testIntegerDeque.size());
	}
	
	@Test
	public void testEmptySizeAfterUse() {
		for(int i = 0; i < 25; i++) {
			testStringDeque.addFirst("Pi");
		}
		for(int i = 0; i < 25; i++) {
			testStringDeque.removeFirst();
		}
		assertEquals(0, testStringDeque.size());
	}
	
	@Test
	public void testPlusSize() {	
		for (int i = 0; i < 1000; i++) {
			testStringDeque.addFirst("Batman");
		}
		assertEquals(1000, testStringDeque.size());
		
		for (int i = 0; i < 100; i++) {
			testIntegerDeque.addFirst(i);
		}
		assertEquals(100, testIntegerDeque.size());
	}

	@Test
	public void testAddFirst() {
		testIntegerDeque.addFirst(1);
		testIntegerDeque.addFirst(5);	
		testIntegerDeque.addFirst(9);
		testIntegerDeque.addFirst(13);
		testIntegerDeque.addFirst(17);
		
		int[] expectedOutput = {17, 13, 9, 5, 1};
		
		for (int i = 0; i < 5; i++) {
			assertEquals(expectedOutput[i], testIntegerDeque.removeFirst().intValue());
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullFirst() {
		testStringDeque.addFirst(null);
	}
	
	@Test
	public void testAddLast() {
		testStringDeque.addLast("Batman");
		testStringDeque.addLast("is");
		testStringDeque.addLast("cooler");
		testStringDeque.addLast("than");
		testStringDeque.addLast("Superman.");
		
		String[] expectedOutput = {"Batman", "is", "cooler", "than", "Superman."};
		
		for (int i = 0; i < 5; i++) {
			assertEquals(expectedOutput[i], testStringDeque.removeFirst());
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullLast() {
		testStringDeque.addLast(null);
	}

	@Test
	public void testRemoveFirst() {
		testStringDeque.addFirst("LOVE");
		testStringDeque.addFirst("I");
		testStringDeque.addLast("JAVA!");
		
		testStringDeque.removeFirst();
		
		testStringDeque.addFirst("WE");
		
		String[] expectedOutput = {"WE", "LOVE", "JAVA!"};
		
		for (int i = 0; i < 3; i++) {
			assertEquals(expectedOutput[i], testStringDeque.removeFirst());
		}
		
		assertEquals(0, testStringDeque.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveNullFirst() {
		testStringDeque.addFirst("one");
		testStringDeque.removeFirst();
		testStringDeque.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		for (int i = 0; i < 25; i+=5) {
			testIntegerDeque.addLast(i);
		}
		testIntegerDeque.removeLast();
		
		int[] expectedOutput = {15, 10, 5, 0};
		
		for (int i = 0; i < 4; i++) {
			assertEquals(expectedOutput[i], testIntegerDeque.removeLast().intValue());
		}

		assertEquals(0, testIntegerDeque.size());
		
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveNullLast() {
		testStringDeque.addFirst("one");
		testStringDeque.addLast("two");
		testStringDeque.removeFirst();
		testStringDeque.removeFirst();
		testStringDeque.removeLast();
	}


	@Test
	public void testIterator() {
		testStringDeque.addLast("fat");
		testStringDeque.addLast("cat");
		testStringDeque.addLast("wears");
		testStringDeque.addLast("a");
		testStringDeque.addLast("hat");
		
		Iterator<String> catIterator = testStringDeque.iterator();
		
		assertEquals("fat", catIterator.next());
		assertEquals("cat", catIterator.next());
		assertEquals("wears", catIterator.next());
		assertEquals("a", catIterator.next());
		assertEquals("hat", catIterator.next());
		
		Iterator<String> fatCatIterator = testStringDeque.iterator();
		
		// this is a test to see that a second iterator will act indepently of the first
		assertEquals("fat", fatCatIterator.next());
		assertEquals("cat", fatCatIterator.next());
		assertEquals("wears", fatCatIterator.next());
		assertEquals("a", fatCatIterator.next());
		assertEquals("hat", fatCatIterator.next());
	}
	
	@Test
	public void testIteratorHasNext() {
		testIntegerDeque.addFirst(1);
		testIntegerDeque.addFirst(2);
		
		Iterator<Integer> intIterator = testIntegerDeque.iterator();
		
		assertTrue(intIterator.hasNext());
		
		intIterator.next();
		assertTrue(intIterator.hasNext());
		
		intIterator.next();
		assertFalse(intIterator.hasNext());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testIteratorNoSuchElement() {
		Iterator<String> iterator = testStringDeque.iterator();
		iterator.next();
	}
	
	@Test (expected = UnsupportedOperationException.class)
	public void testIteratorRemove() {
		Iterator<Integer> intIterator = testIntegerDeque.iterator();
		intIterator.remove();
	}

}