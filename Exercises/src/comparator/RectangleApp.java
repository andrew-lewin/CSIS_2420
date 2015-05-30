package comparator;

import java.util.Arrays;

import edu.princeton.cs.introcs.StdOut;

public class RectangleApp {

	public static void main(String[] args) {
		Rectangle[] r = {
			new Rectangle(1, 9), 
			new Rectangle(3, 5), 
			new Rectangle(2, 7), 
			new Rectangle(4, 3)
		};
		
		StdOut.println("Original:  " + Arrays.toString(r));
		
		Arrays.sort(r);
		StdOut.println("Natural:   " + Arrays.toString(r));
		
		Arrays.sort(r, Rectangle.BY_WIDTH);
		StdOut.println("By Width:  " + Arrays.toString(r));
		
		Arrays.sort(r, Rectangle.BY_LENGTH);
		StdOut.println("By Length: " + Arrays.toString(r));
	}

}
