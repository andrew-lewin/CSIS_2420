package randomizedQueuesAndDeques;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.introcs.StdRandom;

/**
 * @author Andrew Lewin
 * @author Joshua Hardesty
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] itemList;	// array of items
	private int size = 0;		// number of elements in array
	private int first = 0;		// index of first element 
    private int last  = 0;		// index of next available slot
    
	/**
	 * construct an empty randomized queue
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		itemList = (Item[]) new Object[2];
	}
	
	/**
	 * is the queue empty?
	 * @return true if the array is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * return the number of items on the queue
	 * @return int: the number of items on the queue
	 */
	public int size() {
		return size;
	}
	
	private void resizeArray(int multiplier) {
		@SuppressWarnings("unchecked")
		Item[] copyItemList = (Item[]) new Object[multiplier];
		for (int i = 0; i < size; i++) {
			copyItemList[i] = itemList[i];
		}
		itemList = copyItemList;
		first = 0;
		last = size;
	}
	
	/**
	 * add an item to the queue
	 * @param item to add
	 */
	public void enqueue(Item item) {
		if (item == null) 				
			throw new NullPointerException();
		if (size == itemList.length) 	
			resizeArray(2 * itemList.length);
		itemList[last++] = item;
		if (last == itemList.length)
			last = 0;
		size++;
	}
	
	/**
	 * Delete and return a random item. If the array is a quarter full,
	 * resize the array to be half its original length.
	 * @return Item randomly chosen to dequeue
	 */
	public Item dequeue() {
		if (isEmpty()) 			
			throw new NoSuchElementException();
		int deleteRandomItem = StdRandom.uniform(size);
		Item item = itemList[deleteRandomItem];
		itemList[deleteRandomItem] = itemList[--size];
		itemList[size] = null; 	// to avoid loitering
		// resize array if needed
		if (size > 0 && size == itemList.length/4) 
			resizeArray(itemList.length/2);
		return item;	
	}
	
	/**
	 * return (but do not delete) a random item
	 * @return Item randomly chosen to sample
	 */
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		int randomItem = StdRandom.uniform(size);
		return itemList[randomItem];
	}
	
	/**
	 * @return an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i = size;
		private Item[] shuffleArray;
		
		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			shuffleArray = (Item[]) new Object[size];
			System.arraycopy(itemList, first, shuffleArray, 0, size);
			StdRandom.shuffle(shuffleArray);
		}
		
		/**
		 * @return true if size is greater than 0
		 */
		@Override
		public boolean hasNext() {
			return i > 0;
		}
		
		// remove is not needed
		public void remove () {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext()) 
				throw new NoSuchElementException();
			return shuffleArray[--i];
		}
	}
	
	/**
	 * unit testing
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
}