/**********************************************************************
 *  readme.txt template                                                   
 *  Kd-tree
**********************************************************************/

Name: Andrew Lewin
Partner name: Ben Anderl   


/**********************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **********************************************************************/
The node data type in our implementation initially has 3 values. A Point2D, a value, and
a RectHV rectangle. The Point2D value is simply the point in question. The value is the 
the result of the symbol table operation. The rectangle is the axis-aligned rectangle correspodning to this node. 


/**********************************************************************
 *  Describe your method for range search in a kd-tree.
 **********************************************************************/
Our range search method uses a Queue to search through all the rectangles, searching for a point 
contained in the rectangles.


/**********************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **********************************************************************/
Our nearest method compares all the points availiable to find the point closest to the initial point. 


/**********************************************************************
 *  Using the 64-bit memory cost model from the textbook and lecture,
 *  give the total memory usage in bytes of your 2d-tree data structure
 *  as a function of the number of points N. Use tilde notation to
 *  simplify your answer (i.e., keep the leading coefficient and discard
 *  lower-order terms).
 *
 *  Include the memory for all referenced objects (including
 *  Node, Point2D, and RectHV objects) except for Value objects
 *  (because the type is unknown). Also, include the memory for
 *  all referenced objects.
 *
 *  Justify your answer below.
 *
 **********************************************************************/

bytes per Point2D: 32 bytes

bytes per RectHV: 56 bytes

bytes per Node: 3(32 + 56) = 264 bytes

bytes per KdTree of N points:   ~ N*264 bytes

/**********************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 **********************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt           192                    534759

input1M.txt              9                     268096



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/
None.


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
None.


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
None.


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/
I worked with Andrew Lewin. We both worked on each of the classes overall plan of how we would complete this assignemnt. 
We were able to collaberate using Git which made this assignment easy to work and stay current on. 





/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
None.