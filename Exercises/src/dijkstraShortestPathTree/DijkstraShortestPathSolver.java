package dijkstraShortestPathTree;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class DijkstraShortestPathSolver {
	private static SeparateChainingHashST<String, Integer> verticeNumbers;
	private static SeparateChainingHashST<Integer, String> stringNumbers;
	
	public static void main(String[] args) {
		int s = 0;
		EdgeWeightedDigraph G = createEWD("src/dijkstraShortestPathTree/shortestPathDijkstra.txt");
		DijkstraSP dsp = new DijkstraSP(G, s);
		for (DirectedEdge e : dsp.pathTo(verticeNumbers.get("End"))) {
			StdOut.println(stringNumbers.get(e.from()) + "->" + stringNumbers.get(e.to()) + " " + e.weight());
		}
	}

	private static EdgeWeightedDigraph createEWD(String file) {
		In in = new In(file);
		int vertices = Integer.parseInt(in.readLine());
		in.readLine();
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(vertices);
		verticeNumbers = new SeparateChainingHashST<>();
		stringNumbers = new SeparateChainingHashST<>();
		for (int i = 0; in.hasNextLine(); ) {
			String lineString = in.readLine();
			String[] line = lineString.split(" ");
			if (!verticeNumbers.contains(line[0])) {
				int temp = i++;
				verticeNumbers.put(line[0], temp);
				stringNumbers.put(Integer.valueOf(temp), line[0]);
			}
			if (!verticeNumbers.contains(line[1])) {
				int temp = i++;
				verticeNumbers.put(line[1], temp);
				stringNumbers.put(Integer.valueOf(temp), line[1]);
			}
			G.addEdge(new DirectedEdge(
					verticeNumbers.get(line[0]).intValue(), 
					verticeNumbers.get(line[1]).intValue(), 
					Integer.parseInt(line[2])));
		}
		return G;
	}
	
}
