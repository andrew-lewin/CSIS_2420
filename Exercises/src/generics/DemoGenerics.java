package generics;

import edu.princeton.cs.introcs.StdOut;

public class DemoGenerics {
	public static void main(String[] args) {
		String[] words = {"One", "Two", "Three", "Four"};
		swap(words, 1, 2);
		printArray(words);
		
		Character[] characters = {'a', 'e', 'i', 'o', 'u'};
		swap(characters, 1, 3);
		printArray(characters);
		
	}

	private static <T> void swap(T[] bob, int indexOne, int indexTwo) {
		T temp = bob[indexOne];
		bob[indexOne] = bob[indexTwo];
		bob[indexTwo] = temp;
	}
	
	private static <T> void printArray(T[] arrayToPrint) {
		for (T el : arrayToPrint) {
			StdOut.print(el + " ");
		}
		StdOut.println();
	}

}
