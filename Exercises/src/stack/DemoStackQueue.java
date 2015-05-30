package stack;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.StdOut;

public class DemoStackQueue {
	
	public static void main(String[] args) {
		Stack<String> colors = new Stack<>();
		StdOut.println(colors);
		colors.push("blue");
		StdOut.println(colors);
		colors.push("green");
		StdOut.println(colors);
		colors.push("red");
		StdOut.println(colors);
		colors.pop();
		StdOut.println(colors);
	}
}