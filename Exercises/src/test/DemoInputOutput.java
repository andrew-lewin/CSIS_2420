package test;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class DemoInputOutput {
	
	/**
	 * Demo how to use StdIn to read user input and write to the screen
	 */
	private static void demoReadingUserInput() {
		StdOut.println("Reading user input: ");
		StdOut.print("Please enter 3 numbers separated by a space: ");
		double n1 = StdIn.readDouble();
		double n2 = StdIn.readDouble();
		double n3 = StdIn.readDouble();
		StdOut.printf("%.1f + %.1f + %.1f = %.1f %n", n1, n2, n3, n1 + n2 + n3 );
	}
	
	/**
	 * Writes letters a - z to the file specified in the argument
	 * @param fileName
	 */
	public static void demoWritingToFile(String fileName) {
		StdOut.println("Writing to a File.");
		Out out = new Out(fileName);
		for (char c = 'a'; c <= 'z'; c++) {
			out.print(c + " ");
		}
		out.println();
	}
	
	/**
	 * Reads in text from a file and prints it to the screen
	 * @param fileName
	 */
	public static void demoReadingFromFile(String fileName) {
		StdOut.print("Reading from a File: ");
		In in = new In(fileName);
		while (in.hasNextLine()) {
			StdOut.println(in.readLine());
		}
		StdOut.println();
	}
	
	/**
	 * Demonstrates how to use StdIn, StdOut, In, and Out
	 * @param args
	 */
	public static void main(String[] args) {		
		String fileName = "src/test/Letters.txt";
	   
		demoReadingUserInput();		    
	    demoWritingToFile(fileName);	    	    
	    demoReadingFromFile(fileName);
	}
}
