package recursion;

import edu.princeton.cs.introcs.StdDraw;

public class HTree {
	
	public static void main(String[] args) {
		
		draw(5, 0.5, 0.5, 0.5);

	}
	
	public static void drawH(double x, double y, double size) {
		double x0 = x - size/2;
		double x1 = x + size/2;
		double y0 = y - size/2;
		double y1 = y + size/2;
		
		StdDraw.line(x0, y0, x0, y1);
		StdDraw.line(x1, y0, x1, y1);
		StdDraw.line(x0, y, x1, y);
	}
	
	public static void draw(int n, double x, double y, double size) {
		if (n==0) return;
		drawH(x, y, size);
		
		double x0 = x - size/2;
		double x1 = x + size/2;
		double y0 = y - size/2;
		double y1 = y + size/2;
		
		draw(n-1, x0, y0, size/2);
		draw(n-1, x0, y1, size/2);
		draw(n-1, x1, y0, size/2);
		draw(n-1, x1, y1, size/2);
	}

}
