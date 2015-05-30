/**	
 * Names:		Andrew Lewin & Spenser Riches
 * Class:		CSIS 2420
 * Assignment:	A06 WordNet
 * Due Date: 	05/01/15
 */

package wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Stack;

public class SAP {
	private Digraph graph;

	/**
	 * Constructor takes a digraph
	 * @param G digraph
	 */
    public SAP(Digraph G){
    	if (G == null) throw new NullPointerException("Digraph G can't be null");
	    graph = new Digraph(G);
    }

    /**
     * Is the digraph a directed acyclic graph?
     * @return boolean true if it is a directed acyclic graph, false otherwise
     */
    public boolean isDAG(){
	    // A digraph has a topological order iff no directed cycle
	    return !new DirectedCycle(graph).hasCycle();
    }

    /**
     * Is the digraph a rooted DAG?
     * @return boolean true if yes, false if no
     */
    public boolean isRootedDAG(){
	    // First, make sure the graph is a DAG (use method above)
	    if(!isDAG()) return false;
	   
	    // Find the potential 'root' - (last vertex in topological order (reversePost), or 1st vertex in Post Order)
	    DepthFirstOrder dfo = new DepthFirstOrder(this.graph);
	    Integer rootVertex = dfo.post().iterator().next();
	   
	    // Check if all vertices have a path to the root
	    // Send DepthFirstDirectedPaths a reverse graph, that way, the root will point to all children
	    DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(graph.reverse(), rootVertex);
	    for(int i=0; i<graph.V(); i++){
		    // return false if one of the vertices does NOT have a path to the root
		    if(!dfdp.hasPathTo(i)) return false;
	    }
	   
	    // All vertices have a path to the rootVertex
	    return true;
    }

    /**
     * length of the shortest ancestral path between v and w; -1 if no such path
     * @param v vertex 1
     * @param w vertex 2
     * @return length of the shortest ancestral apth between v and w; -1 if no such path
     */
    public int length(int v, int w){
    	Stack<Integer> vStack = new Stack<>();
    	vStack.push(v);
    	Stack<Integer> wStack = new Stack<>();
    	wStack.push(w);
	    return ancestorAndLength(vStack, wStack)[1];
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     * @param v vertex 1
     * @param w vertex 2
     * @return a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     */
    public int ancestor(int v, int w){
    	Stack<Integer> vStack = new Stack<>();
    	vStack.push(v);
    	Stack<Integer> wStack = new Stack<>();
    	wStack.push(w);
	    return ancestorAndLength(vStack, wStack)[0];
    }
   
    /**
     * length of the shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v vertex 1
     * @param w vertex 2
     * @return int length of the shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w){
	    return ancestorAndLength(v, w)[1];
    }

    /**
     * length of the shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     * @param v vertex 1
     * @param w vertex 2
     * @return int length of the shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
	    return ancestorAndLength(v, w)[0];
    }

    private int[] ancestorAndLength(Iterable<Integer> v, Iterable<Integer> w){
	    // Data Structures to find the paths
	    BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(graph, v);
	    BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(graph, w);
	   
	    // DepthFirstOrder in a graph
	    DepthFirstOrder DFO = new DepthFirstOrder(graph);
	        
	    // Closest ancestor and length
	    int ancestor = -1;
	    int length = -1;
	   
	    // Start with reverse post order from the DFO
	    for(int i: DFO.reversePost()){
		    if(vPaths.hasPathTo(i) && wPaths.hasPathTo(i)){
			    // Common ancestor found, calculate the current length
			    int currentLength = vPaths.distTo(i) + wPaths.distTo(i);
			   
			    // See if it's closer than the current
			    if(currentLength < length || ancestor == -1){
				    ancestor = i;
				    length = currentLength;
			    }else break;
		    }
	    }
	   
	    // Returns an array where ancestorAndLength[0] = ancestor and ancestorAndLength[1] = length
	    int[] ancestorAndLength = {ancestor, length};
	    return ancestorAndLength;
    }

   /******************************************************************************
	 * 								TESTING										  *
	 ******************************************************************************/
    public static void main(String[] args){
	   // See Unit Tests
    }
}