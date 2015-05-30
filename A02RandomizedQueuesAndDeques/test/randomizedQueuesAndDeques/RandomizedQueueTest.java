package randomizedQueuesAndDeques;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import edu.princeton.cs.introcs.StdRandom;

/**
 * 
 * @author Andrew Lewin
 * @author Joshua Hardesty
 */
public class RandomizedQueueTest {
	private RandomizedQueue<String> stringStuff = new RandomizedQueue<>();
	private RandomizedQueue<Integer> intStuff = new RandomizedQueue<>();

	@Test
	public void testIsEmptyNew() {
		// Tests newly created RandomizedQueue
		assertEquals(true, stringStuff.isEmpty());
		assertEquals(true, intStuff.isEmpty());
	}
	
	@Test
	public void testIsEmptyUsed() {
		// Tests RandomizedQueue that's had objects in it
		stringStuff.enqueue("Dog");
		stringStuff.enqueue("cat");
		stringStuff.dequeue();
		stringStuff.enqueue("Mouse");
		stringStuff.dequeue();
		stringStuff.dequeue();
		assertEquals(true, stringStuff.isEmpty());
		intStuff.enqueue(1);
		intStuff.dequeue();
		assertEquals(true, intStuff.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, stringStuff.size());
		assertEquals(0, intStuff.size());
		for (int i = 0; i < 1000; i++) stringStuff.enqueue("cat");
		for (int i = 0; i < 9999; i++) intStuff.enqueue(i);
		assertEquals(1000, stringStuff.size());
		assertEquals(9999, intStuff.size());
		for (int i = 0; i < 950; i++) stringStuff.dequeue();
		for (int i = 0; i < 9999; i++) intStuff.dequeue();
		assertEquals(50, stringStuff.size());
		assertEquals(0, intStuff.size());
		for (int i = 0; i < 1000; i++) intStuff.enqueue(i);
		assertEquals(1000, intStuff.size());
	}

	@Test
	public void testEnqueue() {
		// add 10 elements to the RandomizedQueue and them remove them. testEnqueue[]
		// acts as a check list to make sure we removed all items that were added.
		// assertEquals checks to make sure all elements are checked off.
		int[] testEnqueue = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		for (int i = 0; i < 10; i++) intStuff.enqueue(i);
		for (int i = 0; i < 10; i++) {
			int num = intStuff.dequeue();
			for (int j = 0; j < 10; j++) {
				if (testEnqueue[j] == num) testEnqueue[j] = 10;
			}
		}
		for (int i = 0; i < 10; i++) assertEquals(10, testEnqueue[i]);
	}
	
	@Test(expected = NullPointerException.class)
	public void testEnqueueNullEntry() {
		stringStuff.enqueue(null);
	}

	@Test
	public void testDequeueRandomness() {
		StdRandom.setSeed(13);
		for (int i = 0; i < 100000; i++) intStuff.enqueue(i);
		assertEquals(40492, (int) intStuff.dequeue());
		StdRandom.setSeed(7);
		assertEquals(79927, (int) intStuff.dequeue());
	}
	
	@Test
	public void testDequeueRemove() {
		for (int i = 0; i < 100000; i++) intStuff.enqueue(i);
		for (int i = 0; i < 50000 ; i++) intStuff.dequeue();
		assertEquals(50000, intStuff.size());
		for (int i = 0; i < 50000 ; i++) intStuff.dequeue();
		assertEquals(0, intStuff.size());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDequeueNoSuchElement() {
		stringStuff.dequeue();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDequeueNoSuchElementUsedObject() {
		for (int i = 0; i < 50; i++) stringStuff.enqueue("a");
		for (int i = 0; i < 50; i++) stringStuff.dequeue();
		stringStuff.dequeue();
	}

	@Test
	public void testSample() {
		for (int i = 0; i < 100000; i++) intStuff.enqueue(i);
		StdRandom.setSeed(47);
		assertEquals(69258, (int) intStuff.sample());
		assertEquals(20555, (int) intStuff.sample());
		assertEquals(96693, (int) intStuff.sample());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testSampleNoSuchElement() {
		stringStuff.sample();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testSampleNoSuchElementUsedObject() {
		for (int i = 0; i < 50; i++) stringStuff.enqueue("a");
		for (int i = 0; i < 50; i++) stringStuff.dequeue();
		stringStuff.sample();
	}

	@Test
	public void testIterator() {
		for (int i = 0; i < 1000000; i++) intStuff.enqueue(i);
		Iterator<Integer> intIterator1 = intStuff.iterator();
		Iterator<Integer> intIterator2 = intStuff.iterator();
		boolean isSameIterator = true;
		
		for (int i = 0; i < 1000000; i++) 
			if (intIterator1.next() != intIterator2.next()) 
				isSameIterator = false;
		
		assertEquals(false, isSameIterator);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorRemove() {
		for (int i = 0; i < 50; i++) stringStuff.enqueue("a");
		Iterator<String> stringIterator = stringStuff.iterator();
		stringIterator.remove();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testIteratorNextException() {
		for (int i = 0; i < 50; i++) stringStuff.enqueue("a");
		Iterator<String> stringIterator = stringStuff.iterator();
		for (int i = 0; i < 51; i++) stringIterator.next();
	}

}
