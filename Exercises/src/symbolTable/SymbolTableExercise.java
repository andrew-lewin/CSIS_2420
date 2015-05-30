package symbolTable;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdOut;

public class SymbolTableExercise {
	public static void main(String[] args) {
		ST<String, String> colors = new ST<>();
		colors.put("red", "990000");
		colors.put("blue", "000099");
		colors.put("lime", "66CC00");
		colors.put("yellow", "FFFF00");
		colors.put("orange", "FF6600");
		colors.put("white", "000000");
		
		for (String color : colors.keys()) {
			StdOut.println(color + "\t" + colors.get(color));
		}
		
		colors.put("white", "FFFFFF");
		StdOut.println("\nWhite has been updated");
		
		colors.delete("yellow");
		StdOut.println("Yellow has been removed\n");
		
		for (String color : colors.keys())
			StdOut.println(color + "\t" + colors.get(color));
		
		StdOut.println("\nMin: " + colors.min());
		StdOut.println("Max: " + colors.max());
		StdOut.println("Floor(\"magenta\"): " + colors.floor("magenta"));
		StdOut.println("Ceiling(\"magenta\"): " + colors.ceiling("magenta"));
	}
}