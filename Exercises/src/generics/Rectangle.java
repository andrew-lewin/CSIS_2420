package generics;

public class Rectangle implements Comparable<Rectangle> {
	private int length;
	private int width;
	
	public Rectangle(int length, int width) {
		super();
		this.length = length;
		this.width = width;
	}

	@Override
	public String toString() {
		return length + "x" + width;
	}

	public int compareTo(Rectangle other) {
		if (this.length * this.width > other.length * other.width) {
			return 1;
		} else if ((this.length * this.width < other.length * other.width)) {
			return -1;
		} else 
			return 0;
	}

}
