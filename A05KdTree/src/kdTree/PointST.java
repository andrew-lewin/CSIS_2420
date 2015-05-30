package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class PointST<Value> {

	private RedBlackBST<Point2D, Value> st;
	
	/**
	 * Construct an empty symbol table of points
	 */
	public PointST() {
		st = new RedBlackBST<>();
	}
	
	/**
	 * is the symbol table empty?
	 * @return boolean true if empty, false if otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * number of points
	 * @return int number of points
	 */
	public int size() {
		return st.size();
	}
	
	/**
	 * associate the value val with the point p
	 * @param p Point2d key for the value
	 * @param val value
	 */
	public void put(Point2D p, Value val) {
		if (p == null) throw new NullPointerException("called put() with null point");
		st.put(p, val);
	}
	
	/**
	 * value associated with point p
	 * @param p Point2d key to get
	 * @return value at point p
	 */
	public Value get(Point2D p) {
		if (p == null) throw new NullPointerException("called get() with null point");
		return st.get(p);
	}
	
	/**
	 * does the symbol table contain point p?
	 * @param p Point2d key to get
	 * @return true if it contains point p, false if otherwise
	 */
	public boolean contains(Point2D p) {
		if (p == null) throw new NullPointerException("called contains() with null point");
		return st.contains(p);
	}
	
	/**
	 * all the point in the symbol table
	 * @return all the points in the symbol table
	 */
	public Iterable<Point2D> points() {
		return st.keys();
	}
	
	/**
	 * all the points that are inside the rectangle
	 * @param rect rectangle we are searching inside
	 * @return iterable containing all the points inside the rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException("called range() with null rectangle");
		Queue<Point2D> que = new Queue<>();
		for (Point2D p : st.keys()) {
			if (rect.contains(p))
				que.enqueue(p);
		}
		return que;
	}
	
	/**
	 * nearest neighbor to point p; null if the symbol table is empty
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) throw new NullPointerException("called nearest() with null point");
		Point2D nearest = st.max();
		for (Point2D point : st.keys()) {
			if (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearest)) {
				nearest = point;
			}
		}
		return nearest;
	}
	
	public static void main(String[] args) {
		String filename = "samplePoints/input1M.txt";
        In in = new In(filename);
        PointST<Integer> brute = new PointST<>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.put(p, i);
        }
        StdOut.println("Starting");
		long starttime = System.currentTimeMillis();
		int numberOfTimesToCalculate = 500;
		for (int i = 0; i < numberOfTimesToCalculate; i++) {
			brute.nearest(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
		}
		long endtime = System.currentTimeMillis();
		double timeTakenInSeconds = (endtime-starttime)/1000.0;
		StdOut.println("Total time: " + timeTakenInSeconds);
		StdOut.println("Average per second: " + numberOfTimesToCalculate / timeTakenInSeconds);
	}

}