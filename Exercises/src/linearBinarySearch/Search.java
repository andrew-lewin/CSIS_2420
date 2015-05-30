package linearBinarySearch;

import edu.princeton.cs.introcs.StdOut;

/**
 * 
 * @author andrew
 * Date created: 1/21/15
 * Date last modified: 1/21/15
 * 
 * This is a class to demonstrate doing binary searches.
 *
 */
public class Search {
	
	/**
	 * Searches for the key in array a using the
	 * linear search algorithm.
	 * @param a
	 * @param key
	 * @return index of key or -1 if the key was not found
	 */
	public static int linear(int[] a, int key) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == key)
				return i;
		}
		return -1;
	}
	
	/**
	 * Searches for the key in array a using the
	 * binary search algorithm.
	 * Precondition: The array a has to be sorted
	 * @param a
	 * @param key
	 * @return index of key or -1 if the key was not found
	 */
	public static int binary(int[] a, int key) {
		int lo = 0;
		int hi = a.length -1;
		
		while (lo <= hi) {
			int mid = lo + ((hi - lo) / 2);
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] numbers = {1, 2, 3, 5, 7, 9, 10, 20};
		
		StdOut.println(linear(numbers, 5));
		StdOut.println(binary(numbers , 5));

	}

}
