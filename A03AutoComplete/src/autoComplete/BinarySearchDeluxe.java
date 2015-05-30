package autoComplete;

import java.util.Collections;
import java.util.Comparator;

public class BinarySearchDeluxe {
	
	/**
	 * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
	 * @param a - the array to search through. Must be sorted with respect to supplied comparator
	 * @param key - the key we are searching for the first occurance of
	 * @param comparator - the comparator the key is sorted with
	 * @return the index of the first occurence of an element matching key in a[] or -1 if no such key.
	 */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) throw new IllegalArgumentException("arguments cannot be null");
    	int lo = 0;
    	int hi = a.length - 1;
    	if (comparator.compare(a[0], key) == 0) return 0;
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;
    		if (comparator.compare(key, a[mid]) < 0) hi = mid - 1;
    		else if (comparator.compare(key, a[mid]) > 0) lo = mid + 1;
    		else if (comparator.compare(a[mid - 1], a[mid]) == 0) hi = mid - 1;
    		else return mid;
    	}
		return -1;
    }

    /**
     * Return the index of the last key in a[] that equals the search key, or -1 if no such key.
     * @param a - the array to search through. Must be sorted with respect to supplied comparator
     * @param key - the key we are searching for the last occurance of
     * @param comparator - the comparator the key is sorted with
     * @return the index of the last occurence of an element matching key in a[] or -1 if no such key.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
    	if (a == null || key == null || comparator == null) throw new IllegalArgumentException("arguments cannot be null");
    	int lo = 0;
    	int hi = a.length - 1;
    	if (comparator.compare(a[hi], key) == 0) return hi;
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;
    		if (comparator.compare(key, a[mid]) < 0) hi = mid - 1;
    		else if (comparator.compare(key, a[mid]) > 0) lo = mid + 1;
    		else if (comparator.compare(a[mid + 1], a[mid]) == 0) lo = mid + 1;
    		else return mid;
    	}
		return -1;
    }
    
    public static void main(String[] args) {
    	Integer[] numbers = {10, 10, 10, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 1, 1, 1};
    	System.out.print(BinarySearchDeluxe.firstIndexOf (numbers, 10, Collections.reverseOrder()) + "\t");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(numbers, 10, Collections.reverseOrder()));
    	System.out.print(BinarySearchDeluxe.firstIndexOf (numbers, 9, Collections.reverseOrder()) + "\t");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(numbers, 9, Collections.reverseOrder()));
    	System.out.print(BinarySearchDeluxe.firstIndexOf (numbers, 4, Collections.reverseOrder()) + "\t");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(numbers, 4, Collections.reverseOrder()));
    	System.out.print(BinarySearchDeluxe.firstIndexOf (numbers, 0, Collections.reverseOrder()) + "\t");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(numbers, 0, Collections.reverseOrder()));
    	System.out.print(BinarySearchDeluxe.firstIndexOf (numbers, 11, Collections.reverseOrder()) + "\t");
    	System.out.println(BinarySearchDeluxe.lastIndexOf(numbers, 11, Collections.reverseOrder()));
    }
}
