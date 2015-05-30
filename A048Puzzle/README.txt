/**********************************************************************
 *  8-Puzzle readme.txt template
 **********************************************************************/


Name: Andrew Lewin
Partner name: Jason Lloyd

Hours to complete assignment (optional): 12 hours


/**********************************************************************
 *  Explain briefly how you implemented the board data type.
 **********************************************************************/
The board data type is represented as an array of ints. It contains many 
optimizations in an attempt to solve more difficult puzzles and reduce the 
memory footprint. For instance, it uses System.arraycopy to make new boards
because that is faster than using a for loop, according to Stack Overflow. It
also caches the manhattan distance so it isn't calculated many times, which will
help speed it up. Also, when checking if a board is solved, we check if the 
0 is in the correct position before checking anything else. This will speed it
up, but also allows us to use a simple for-loop to check the rest of them.

/**********************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 **********************************************************************/
A search node, which we call a move, contains a board, number of moves to
get to that board, and the parent board. It contains the parent so once we
find a solution, we can keep tracing that up to give the full solution and 
it also allows us to avoid putting the same board as the parent back on the 
priority queue.

/**********************************************************************
 *  Explain briefly how you detected unsolvable puzzles. What is the
 *  order of growth of the runtime of your isSolvable() method?
 **********************************************************************/
isSolvable() counts the number of inversions using nested for-loops. Once
I have that, I check if the size of the board is even or odd. If it's even,
I add the row number that the 0 is on. Once that is done, I know if the board
is solvable by comparing the inversions to whether the board is even or odd.
If it's even, the board is solvable with odd inversions. If it's odd, the board
is solvable with even inversions.

/**********************************************************************
 *  For each of the following instances, give the minimal number of 
 *  moves to reach the goal state. Also give the amount of time
 *  it takes the A* heuristic with the Hamming and Manhattan
 *  priority functions to find the solution. If it can't find the
 *  solution in a reasonable amount of time (say, 5 minutes) or memory,
 *  indicate that instead. Note that you may be able to solve
 *  puzzlexx.txt even if you can't solve puzzleyy.txt even if xx > yy.
 **********************************************************************/


                  number of          seconds
     instance       moves      Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt     28			0.284		 0.028
   puzzle30.txt     30			0.573		 0.044
   puzzle32.txt     32			55.691	 	 0.162
   puzzle34.txt     34			168.384	 	 0.085
   puzzle36.txt     36	Not solved in 5 min	 0.647
   puzzle38.txt     38	Not solved in 5 min	 0.384
   puzzle40.txt     40	Not solved in 5 min	 0.137
   puzzle42.txt     42	Not solved in 5 min	 0.552



/**********************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer:  a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 **********************************************************************/
A better priority function. If we have a better way of knowing which board
to be choosing, we'll use far less memory and it will be far faster since
we are having to check for less solutions.  To overcome this limitation
we modified our "move" comparison to emphasize the LONGEST move whenever
the priorities are the same.  This allows the system to bypass poor future
estimates with known past costs.

/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/
The biggest limitation is memory for extremely difficult puzzles, for
instance puzzle4x4-78.txt and puzzle4x4-80.txt.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
The only help Andrew got was that I wasn't sure if System.arraycopy is
faster than a for-loop, so I looked that up on Stack Overflow to get
the answer.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
None.


/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/
Largely, Andrew did the Board class and Jason did the Solver class, but
we both contributed to each other's code.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************
 None.
