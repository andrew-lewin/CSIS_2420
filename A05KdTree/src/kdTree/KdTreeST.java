package kdTree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class KdTreeST <Value> {
	private int size;
	private Node root;
	
	// private helper class
	private class Node {
		private Point2D p; // the point
		private Value val; // the symbol table maps the point to this value
		private RectHV rect; // the axis-aligned rectangle corresponding to this node
		private Node leftBottom, rightTop; // the left/bottom and right/top subtrees
		
		public Node(Point2D p, Value val, RectHV rect) {
			this.p = p;
			this.val = val;
			this.rect = rect;
		}
	}
	
	/**
	 * Construct an empty symbol table of points
	 */
	public KdTreeST() {
		size = 0;
		root = null;
	}
	
	/**
	 * is the symbol table empty?
	 * @return boolean true if yes, false is no
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * number of points in the symbol table
	 * @return int number of points
	 */
	public int size() {
		return size;
	}
	
	/**
	 * associate the value with point p
	 * @param p Point2D key for the val
	 * @param val associated with Point2D
	 */
	public void put(Point2D p, Value val) {
		if (p == null || val == null) throw new NullPointerException("arguments can't be null");
		root = put(null, root, p, val, true);
	}
	
	// private helper method for put
	private Node put(Node parent, Node node, Point2D p, Value val, boolean isVertical) {
		if (node == null) {
			size++;
			return new Node(p, val, createNewRect(parent, p, isVertical));
		}
		
		double compare = compareXOrY(node, p, isVertical);
		if 	    (compare < 0) node.leftBottom = put(node, node.leftBottom, p, val, !isVertical);
		else if (compare > 0) node.rightTop   = put(node, node.rightTop,   p, val, !isVertical);
		else if (node.p.equals(p)) node.val = val;
		else node.rightTop = put(node, node.rightTop, p, val, !isVertical);
		
		return node;
	}
	
	/**
	 * value associated with point p
	 * @param p Point2D used to search for value
	 * @return value assocated with point p
	 */
	public Value get(Point2D p) {
		if (p == null) throw new NullPointerException("arguments can't be null");
		return get(root, p, true);
	}
	
	// private helper method for get
	private Value get(Node node, Point2D p, boolean isVertical) {
		if (node == null) return null;
		
		double compare = compareXOrY(node, p, isVertical); 
		if (compare < 0) return get(node.leftBottom, p, !isVertical);
		else if (compare > 0) return get(node.rightTop, p, !isVertical);
		else if (node.p.equals(p)) return node.val;
		else return get(node.rightTop, p, !isVertical);
	}
	
	/**
	 * Does the symbol table contain point p?
	 * @param p key to check
	 * @return true if it does contain it, false otherwise
	 */
	public boolean contains(Point2D p) {
		if (p == null) throw new NullPointerException("arguments can't be null");
		return get(p) != null;
	}
	
	/**
	 * all points in the symbol table
	 * @return Iterable all points in the symbol table in level order
	 */
	public Iterable<Point2D> points() {
		Queue<Point2D> queueToReturn = new Queue<>();
		if (size == 0) return queueToReturn;
		Queue<Node> traversalQueue = new Queue<>();
		
		traversalQueue.enqueue(root);
		while (!traversalQueue.isEmpty()) {
			Node temp = traversalQueue.dequeue();
			queueToReturn.enqueue(temp.p);
			if (temp.leftBottom != null) traversalQueue.enqueue(temp.leftBottom);
			if (temp.rightTop != null) traversalQueue.enqueue(temp.rightTop);
		}
		return queueToReturn;
	}
	
	/**
	 * all points that are inside the rectangle
	 * @param rect rectangle to search inside
	 * @return all points that are inside the rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException("arguments can't be null");
		Queue<Point2D> pointsInsideRect = new Queue<>();
		range(rect, pointsInsideRect, root);
		return pointsInsideRect;
	}
	
	private void range(RectHV rect, Queue<Point2D> pointsInsideRect, Node node) {
		if (node == null) return;
		if (!rect.intersects(node.rect)) return;
		if (rect.contains(node.p)) pointsInsideRect.enqueue(node.p);
		range(rect, pointsInsideRect, node.leftBottom);
		range(rect, pointsInsideRect, node.rightTop);
	}
	
	/**
	 * A nearest neighbor to point p; null if the symbol table is empty
	 * @param p neighbor point to check against
	 * @return point closest to p
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) throw new NullPointerException("arguments can't be null");
		return nearest(p, root, root.p);
	}
	
	private Point2D nearest(Point2D p, Node node, Point2D winningPoint) {
		if (node == null) return winningPoint;
		if (node.rect.distanceSquaredTo(p) > winningPoint.distanceSquaredTo(p)) return winningPoint;//distanceSquaredTo() is faster and doesn't use Math.sqrt()
		if (p.distanceSquaredTo(node.p) < p.distanceSquaredTo(winningPoint)) winningPoint = node.p;
		
		if (node.leftBottom != null && node.leftBottom.rect.contains(p)) { // We always want to be moving toward the point
			winningPoint = nearest(p, node.leftBottom, winningPoint);
			winningPoint = nearest(p, node.rightTop, winningPoint);
		} else {
			winningPoint = nearest(p, node.rightTop, winningPoint);
			winningPoint = nearest(p, node.leftBottom, winningPoint);
		}
		return winningPoint;
	}
	
	// Compare either x or y depending on if it's vertical or horizontal
	private double compareXOrY(Node node, Point2D p, boolean isVertical) {
		if (isVertical) return p.x() - node.p.x(); 
		else		    return p.y() - node.p.y();
	}
	
	// private helper method to calculate the rectangle for put
	private RectHV createNewRect(Node parent, Point2D p, boolean isVertical) {
		if (parent == null) return new RectHV(-Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		double compare = compareXOrY(parent, p, !isVertical);
		// use parent's ymin and then copy the rest of the rect
		if (isVertical  && compare >= 0) return new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
		// use parent's ymax and then copy the rest of the rect
		if (isVertical  && compare <  0) return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
		// use parent's xmin and then copy the rest of the rect
		if (!isVertical && compare >= 0) return new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
		// use parent's xmax and then copy the rest of the rect
		if (!isVertical && compare <  0) return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
		return null;
	}
	
	public static void main(String[] args) {
		String filename = "samplePoints/input1M.txt";
        In in = new In(filename);
        KdTreeST<Integer> kdtree = new KdTreeST<>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.put(p, i);
        }
        StdOut.println("Starting");
		long starttime = System.currentTimeMillis();
		int numberOfTimesToCalculate = 10000000;
		for (int i = 0; i < numberOfTimesToCalculate; i++) {
			kdtree.nearest(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
		}
		long endtime = System.currentTimeMillis();
		double timeTakenInSeconds = (endtime-starttime)/1000.0;
		StdOut.println("Total time: " + timeTakenInSeconds);
		StdOut.println("Average per second: " + numberOfTimesToCalculate / timeTakenInSeconds);
	}
}
