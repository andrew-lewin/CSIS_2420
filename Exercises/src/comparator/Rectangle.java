package comparator;

import java.util.Comparator;

public class Rectangle implements Comparable<Rectangle> {
	public static final Comparator<Rectangle> BY_WIDTH = new ByWidth();
	public static final Comparator<Rectangle> BY_LENGTH = new ByLength();
	private int length;
	private int width;
	private int area;
	
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
		this.area = length * width;
	}
	
	@Override
	public String toString() {
		return length + "x" + width + "(" + area + ")";
	}

	@Override
	public int compareTo(Rectangle other) {
		return this.area - other.area;
	}
	
	private static class ByWidth implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle r1, Rectangle r2) {
			return r1.width - r2.width;
		}
	}
	
	private static class ByLength implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle r1, Rectangle r2) {
			return r1.length - r2.length;
		}
	}
}