package generics;

import java.util.Arrays;

import edu.princeton.cs.introcs.StdOut;

public class RectangleApp {
	public static void main(String[] args) {
		Rectangle[] rectangles = {
				new Rectangle(1,2),
				new Rectangle(7,8),
				new Rectangle(5,6),
				new Rectangle(3,4)	
		};
		
		StdOut.println(Arrays.toString(rectangles));
		
		Arrays.sort(rectangles);
		
		StdOut.println(Arrays.toString(rectangles));

	}

}
