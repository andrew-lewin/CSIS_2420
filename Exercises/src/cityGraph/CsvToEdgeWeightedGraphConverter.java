package cityGraph;

import java.util.LinkedList;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;

public class CsvToEdgeWeightedGraphConverter {
	
	public static EdgeWeightedGraph generateEdgeWeightedGraph(String csvFile, String graphFile) {
		In in = new In(csvFile);
		Out out = new Out(graphFile);
		ST<String, Integer> st = new ST<>();
		int counter = 0;
		int edges = 0;
		LinkedList<String> linkedList = new LinkedList<>();
		
		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] lineSegment = line.split(",");
			if (!st.contains(lineSegment[0])) st.put(lineSegment[0], counter++);
			if (!st.contains(lineSegment[1])) st.put(lineSegment[1], counter++);
			linkedList.add(line);
			edges++;
		}
		int vertices = st.size();
		
		out.println(vertices);
		out.println(edges);
		
		for (String string : linkedList) {
			String[] lineSegment = string.split(",");
			out.println(st.get(lineSegment[0]) + " " + st.get(lineSegment[1]) + " " + lineSegment[2]);
		}
		
		EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(graphFile));
		return graph;
	}
	
	public static void main(String[] args) {
		EdgeWeightedGraph graph = generateEdgeWeightedGraph("src/cityGraph/cityconnections.csv", "src/cityGraph/cityconnections.txt");
		StdOut.println(graph.toString());
	}

}
