/**	
 * Names:		Andrew Lewin & Spenser Riches
 * Class:		CSIS 2420
 * Assignment:	A06 WordNet
 * Due Date: 	05/01/15
 */

package wordnet;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;

public class SAPTest {
	
	/**
	 * This is our unit test for SAP
	 */

	@Test
	public void testIsDag() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(true, sap.isDAG());
	}

	@Test
	public void testIsDagFalse() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(false, sap.isDAG());
	}
	
	@Test
	public void testIsRootedDagTrue() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(true, sap.isRootedDAG());
	}
	
	@Test
	public void testIsRootedDagFalseBecauseGraphisNotaDag() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(false, sap.isRootedDAG());
	}
	
	@Test
	public void testIsRootedDagFalseBecauseGraphisANonRootedDag() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNonRootedDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(true, sap.isDAG());
	       assertEquals(false, sap.isRootedDAG());
	}
	
	@Test
	public void testAncestorwDigraph1() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(1, sap.ancestor(6, 8));
	}
	
	@Test
	public void testAncestorwDigraph2() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph2.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(0, sap.ancestor(1, 5));
	}
	
	@Test
	public void testAncestorwDigraphNoDAG() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(9, sap.ancestor(0, 9));
	}
	
	@Test
	public void testLengthwDigraph1() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(4, sap.length(6, 8));
	}
	
	@Test
	public void testLengthwDigraph2() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph2.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(2, sap.length(1, 5));
	}
	
	@Test
	public void testLengthwDigraphNoDAG() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       assertEquals(1, sap.length(0, 9));
	}
	
	@Test
	public void testAncestorIterablewDigraph1() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(2);
	       q1.enqueue(7);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(8);
	       q2.enqueue(11);
	       // 8 and 7 have the nearest ancestor (1) at length 4
	       assertEquals(1, sap.ancestor(q1, q2));
	       assertEquals(4, sap.length(q1, q2));
	}
	
	@Test
	public void testAncestorIterablewDigraph2() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph2.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(1);
	       q1.enqueue(3);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(5);
	       q2.enqueue(0);
	       // 1 and 0 have the nearest ancestor (0) at length 1
	       assertEquals(0, sap.ancestor(q1, q2));
	       assertEquals(1, sap.length(q1, q2));
	}
	
	@Test
	public void testAncestorIterablewDigraphNoDAG() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(2);
	       q1.enqueue(0);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(8);
	       q2.enqueue(11);
	       // 0 and 11 have the nearest ancestor (9) at length 2
	       assertEquals(9, sap.ancestor(q1, q2));
	       assertEquals(2, sap.length(q1, q2));
	}
	
	@Test
	public void testLengthIterablewDigraph1() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph1.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(0);
	       q1.enqueue(9);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(8);
	       q2.enqueue(3);
	       // TODO: Question: When running the iterable ancestor and length, what happens if there are more than one ancestor with the same length?
	       // TWO possible solutions...both length 2
	       // 3 and 0 have ancestor 0
	       // 8 and 9 have ancestor 5
	       // Code is written so it returns ancestor 5, because it starts at the bottom and works up.
	       assertEquals(2, sap.length(q1, q2));
	       assertEquals(5, sap.ancestor(q1, q2));
	}
	
	@Test
	public void testLengthIterablewDigraph2() {
		 // Create graph from file
		   In in = new In("inputFiles/digraph2.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(2);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(5);
	       q2.enqueue(0);
	       // 2 and 5 have the nearest ancestor (5) at length 3
	       assertEquals(3, sap.length(q1, q2));
	       assertEquals(5, sap.ancestor(q1, q2));
	}
	
	@Test
	public void testLengthwIterableDigraphNoDAG() {
		 // Create graph from file
		   In in = new In("inputFiles/digraphNoDAG.txt");
	       Digraph G = new Digraph(in);
	       SAP sap = new SAP(G);
	       Queue<Integer> q1 = new Queue<>();
	       q1.enqueue(8);
	       Queue<Integer> q2 = new Queue<>();
	       q2.enqueue(0);
	       // TWO possible solutions...both length 3
	       // 0 and 8 have ancestor 5
	       // 0 and 8 have ancestor 0
	       assertEquals(3, sap.length(q1, q2));
	       assertEquals(0, sap.ancestor(q1, q2));
	}
}
