package test;

import static org.junit.Assert.*;
import kdTree.KdTreeST;
import kdTree.RectHV;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Point2D;

public class KdTreeSTTest {
	KdTreeST<String> tree = new KdTreeST<>();
	KdTreeST<String> emptyTree = new KdTreeST<>();

	@Before
	public void setUp() throws Exception {
		Point2D[] points = {new Point2D(0,0), new Point2D(1,1), new Point2D(2,2), new Point2D(1,-1), new Point2D(2,-2),
				new Point2D(-1,1), new Point2D(-2,2), new Point2D(-1,-1), new Point2D(-2,-2)};
		for (Point2D point : points) tree.put(point, point.toString());
	}

	@Test
	public void testKdTreeST() {
		KdTreeST<Integer> testTree = new KdTreeST<>();
		assertEquals(0, testTree.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(true, emptyTree.isEmpty());
		assertEquals(false, tree.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, emptyTree.size());
		assertEquals(9, tree.size());
	}

	@Test
	public void testPut() {
		tree.put(new Point2D(5,5), "bob");
		assertEquals(10, tree.size());
		assertEquals("bob", tree.get(new Point2D(5,5)));
		tree.put(new Point2D(5,5), "notBob");
		assertEquals(10, tree.size());
		assertEquals("notBob", tree.get(new Point2D(5,5)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testPutNull() {
		tree.put(null, null);
		tree.put(null, "bob");
		tree.put(new Point2D(1,5), null);
	}

	@Test
	public void testGet() {
		Point2D[] points = {new Point2D(0,0), new Point2D(1,1), new Point2D(2,2), new Point2D(1,-1), new Point2D(2,-2),
				new Point2D(-1,1), new Point2D(-2,2), new Point2D(-1,-1), new Point2D(-2,-2)};
		for (Point2D point : points) assertEquals(point.toString(), tree.get(point));
		assertEquals(null, tree.get(new Point2D(Double.MAX_VALUE, Double.MAX_VALUE)));
		assertEquals(null, tree.get(new Point2D(-Double.MAX_VALUE,-Double.MAX_VALUE)));
		assertEquals(null, tree.get(new Point2D(Double.MIN_VALUE, Double.MIN_VALUE)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetNull() {
		tree.get(null);
	}

	@Test
	public void testContains() {
		Point2D[] points = {new Point2D(0,0), new Point2D(1,1), new Point2D(2,2), new Point2D(1,-1), new Point2D(2,-2),
				new Point2D(-1,1), new Point2D(-2,2), new Point2D(-1,-1), new Point2D(-2,-2)};
		for (Point2D point : points) assertEquals(true, tree.contains(point));
		assertEquals(false, tree.contains(new Point2D(Double.MAX_VALUE, Double.MAX_VALUE)));
		assertEquals(false, tree.contains(new Point2D(-Double.MAX_VALUE,-Double.MAX_VALUE)));
		assertEquals(false, tree.contains(new Point2D(Double.MIN_VALUE, Double.MIN_VALUE)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsNull() {
		tree.contains(null);
	}

	@Test
	public void testPoints() {
		Iterable<Point2D> emptyQueue = emptyTree.points();
		for (Point2D point : emptyQueue) fail("Queue was not empty and contained " + point + ". Queue was " + emptyQueue.toString());
		Point2D[] treeInLevelOrder = {new Point2D(0,0), new Point2D(-1,1), new Point2D(1,1), new Point2D(-1,-1),
				new Point2D(-2,2), new Point2D(1,-1), new Point2D(2,2), new Point2D(-2,-2), new Point2D(2,-2)};
		int i = 0;
		for (Point2D point: tree.points()) assertEquals(treeInLevelOrder[i++], point);
	}

	@Test
	public void testRange() {
		Iterable<Point2D> noPoint = tree.range(new RectHV(10,10,11,11));
		Iterable<Point2D> onePoint = tree.range(new RectHV(-0.5, -0.5, 0.5, 0.5));
		Point2D[] onePointAnswer = {new Point2D(0,0)};
		Iterable<Point2D> fivePoints = tree.range(new RectHV(-1, -1, 1, 1));
		Point2D[] fivePointsAnswer = {new Point2D(0,0), new Point2D(-1,1), new Point2D(-1,-1), new Point2D(1,1), new Point2D(1,-1)};
		for (Point2D point : noPoint) fail("Queue was not empty and contained " + point + ". Queue was " + noPoint.toString());
		int i = 0;
		for (Point2D point : onePoint) assertEquals(onePointAnswer[i++], point);
		i = 0;
		for (Point2D point : fivePoints) assertEquals(fivePointsAnswer[i++], point);
	}
	
	@Test (expected = NullPointerException.class)
	public void testRangeNull() {
		tree.range(null);
	}

	@Test
	public void testNearest() {
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(0,0)));
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(Double.MIN_VALUE, Double.MIN_VALUE)));
		assertEquals(new Point2D(2,2), tree.nearest(new Point2D(10000000, 10000000)));
		assertEquals(new Point2D(-2,-2), tree.nearest(new Point2D(-10000000, -10000000)));
		assertEquals(new Point2D(-2, 2), tree.nearest(new Point2D(-10000000, 10000000)));
		assertEquals(new Point2D(2, -2), tree.nearest(new Point2D(10000000, -10000000)));
		assertEquals(new Point2D(0,0), tree.nearest(new Point2D(0.4, 0.6)));
		assertEquals(new Point2D(1,1), tree.nearest(new Point2D(0.40000000001, 0.6)));
		assertEquals(new Point2D(1,1), tree.nearest(new Point2D(0.4, 0.60000000001)));
	}
	
	@Test (expected = NullPointerException.class)
	public void testNearestNull() {
		tree.nearest(null);
	}

}
