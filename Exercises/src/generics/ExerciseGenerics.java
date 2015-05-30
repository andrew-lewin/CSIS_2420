package generics;

import edu.princeton.cs.introcs.StdOut;

public class ExerciseGenerics {
	public static void main(String[] args) {
		Integer[] numbers = {1, 2, 3, 2, 1, 2, 3, 2, 1};
		String[] words = {"One", "Two", "One", "Two", "one", "two"};
		Character[] chars = {'a', 'e', 'i', 'a', 'e'};
		//List<Integer> myList = Arrays.asList(1, 2, 3, 2, 1);
		
		StdOut.print(countOccurences(numbers, 2));
		StdOut.println();
		StdOut.print(countOccurences(words, "One"));
		StdOut.println();
		StdOut.print(countOccurences(chars, 'i'));
		StdOut.println();
		
		//StdOut.print(countOccurences(myList, 1));

	}

	private static <T> int countOccurences(T[] numbers, T i) {
		int counter = 0;
		for (T number : numbers) {
			if (number == i) {
				counter += 1;
			}
		}
		return counter;
	}

}
