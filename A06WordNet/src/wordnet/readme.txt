/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name:    			Spenser Riches
Login:   
Precept: 

Partner name:     	Andrew Lewin
Partner login:    
Partner precept:  


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/
	We used two Separate Chaining Hash Symbol Tables because the performance
	guarantee for search, insert, and delete is N.


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/
	Hypernymns data was stored in a Digraph. We used a digraph so we could
	represent 'is a' relationships effectively to make a logical WordNet.


/**********************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortesetCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the
 *  number of edges E in the digraph?
 **********************************************************************/

Description:
	Our isRootedDAG method first checks if the the digraph is a DAG
	(Which checks that there are no directed cycles). It then uses a
	DepthFirstOrder's post method and grabs the first vertex in the
	post order iteration. This vertex is the "root". The program then
	iterates through all vertices in the graph. If it finds a vertex
	that does not have a path to the root, then isRootedDAG is false.

Order of growth of running time:
	Runs through isDAG : V + E
	Creates a DepthFirstOrder object and returns the post order : 2V + E
	Cycles through all vertices in the graph to check hasPath : V
	TOTAL : 4V + 2E  (Big O: V + E)
	

/**********************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. What is the order of growth of
 *  the running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  If you use hashing, you should assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 **********************************************************************/

Description:

	To find the shortest common ancestor, two BreadthFirstDirectedPaths
	are created (one for each vertex or Iterable of vertices). Also, a
	DepthFirstOrder is created based on the graph. A loop is created
	that searches for an ancestor in reversePost order. If both vertices
	have a path to the current iteration, then that vertex is an ancestor.
	The length to that ancestor is computed, and the loop runs again till it
	finds another ancestor. If the new ancestor is farther away, the search
	ends, because by using reversePost order, that means we are now getting
	farther away from the closet ancestor. Worst case, all vertices will be 
	iterated.

                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)				 V + E				  V + E

ancestor(int v, int w)				 V + E				  V + E

length(Iterable<Integer> v,			 V + E				  V + E
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,		 V + E				  V + E
         Iterable<Integer> w)



Notes:
	BreadthFirstDirectedPaths (x2):		2V + 2E
	DepthFirstOrder:					2V + E
	Iterate to find ancestor:			best = constant		worst = V
	Total:								best = 4V + 3E		worst = 5V + 3E
	

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/
	None that we are aware of.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
	Talked about general concepts with classmates at our table.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
	The ancestor method was tricky getting started. Other than that
	everything seemed to come together well.

/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/
	We followed the protocol. We each did all parts of the assignment
	simultaneously, then compared code and re-factored to clean things
	up. We went with Spenser's implementation of SAP and Andrews implementation
	of WordNet. We both created unit tests for all classes.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
	It helped me to better understand symbol tables and graphs.