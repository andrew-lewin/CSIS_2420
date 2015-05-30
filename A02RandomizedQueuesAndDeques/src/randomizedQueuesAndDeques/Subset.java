package randomizedQueuesAndDeques;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Subset only runs in the command line exactly how Sedgewick put in the instructions.
 * @author Andrew Lewin
 * @author Joshua Hardesty
 *
 */
public class Subset {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> input = new RandomizedQueue<>();
		
		for (String s : StdIn.readAllStrings()) {
			input.enqueue(s);
		}
		
		for (int i = 0; i < k; i++) StdOut.println(input.dequeue());
	}
}