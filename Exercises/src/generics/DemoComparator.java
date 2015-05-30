package generics;

import java.util.Arrays;

import edu.princeton.cs.introcs.StdOut;

public class DemoComparator {
	public static void main(String[] args) {
		String[] words = {"Apple", "ape", "C", "Bear", "boy"};
		StdOut.println(Arrays.toString(words));
		
		Arrays.sort(words);
		StdOut.println(Arrays.toString(words));
		
		Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
		StdOut.println(Arrays.toString(words));

	}

}
