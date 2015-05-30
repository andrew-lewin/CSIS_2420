package a048puzzle;

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.StdOut;

/**
 * Board class. Stores a single instance of a slider puzzle board.
 * @author Andrew Lewin
 * @author Jason Lloyd
 */
public class Board {
	private final int N;
	private final int[] tiles;
	private int manhattan = -1;
	private int hamming = -1;
	private int zeroLocation;
	
	/**
	 * construct a board from an N-by-N array of blocks
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks double array of blocks
	 */
	public Board(int[][] blocks){
		if (blocks == null) throw new NullPointerException();
		N = blocks.length;
		
		// Initialize tiles
		tiles = new int[N*N];
		int tileInit = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] == 0) zeroLocation = tileInit;
				tiles[tileInit++] = blocks[i][j];
			}
		}
	}
	  
	/**
	 * Returns the size of the board in N, the length of one side
	 * @return the size of the board
	 */
	public int size(){					
		return N;
	}
	
	/**
	 * returns the number of blocks that are out of place
	 * @return the number of blocks that are out of place
	 */
	public int hamming(){
		if (hamming != -1) return hamming; // if hamming has already been solved no need to solve it again
		
		hamming = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != (i + 1) && tiles[i] != 0) hamming++;
		}
		return hamming;
	}
	
	/**
	 * returns the sum of the Manhattan distance between the blocks and the goal board
	 * @return the sum of the Manhattan distance between the blocks and the goal board
	 */
	public int manhattan(){
		if (manhattan != -1) return manhattan; // if manhattan has already been solved no need to solve it again
		
		manhattan = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == (i + 1) || i == zeroLocation) continue; // if tiles[i] == goal or is 0 we can go to the next iteration.
			manhattan += Math.abs((i / N) - ((tiles[i] - 1) / N)); // count rows displaced; 
			manhattan += Math.abs((i % N) - ((tiles[i] - 1) % N)); // count columns displaced;
		}
		return manhattan;
	}
	  
	/**
	 * returns whether or not the board is the goal board
	 * @return true if goal board, false if not goal board
	 */
	public boolean isGoal(){
		if (tiles[tiles.length - 1] != 0) return false; // if the blank tile isn't in place we know it's not goal
		for (int i = 0; i < tiles.length - 1; i++) // check everything else, excluding the blank tile since it was checked above
			if (tiles[i] != (i + 1))
				return false;
		return true;
	}
	  
	/**
	 * returns whehter or not the board is solvable
	 * @return true if solvable, false if not solvable
	 */
	public boolean isSolvable(){
		int inversions = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == 0)
				continue; // we don't count blank in the inversions, so we can skip the rest
			for (int j = i; j < tiles.length; j++) // check all tiles after i to see if any are larger than j
				if (tiles[j] < tiles[i] && tiles[j] != 0)
					inversions++;
		}
		
		boolean isEvenBoard = (N % 2) == 0; // true if even, false if odd
		if (isEvenBoard) inversions += zeroLocation / N; // if the board is even we must add which row 0 is on
		boolean isEvenInversions = (inversions % 2) == 0; // true if the total number of inversions is even
		
		return isEvenBoard != isEvenInversions; // an odd board is solvable with even inversions. 
											    // an even board is solvable with odd inversions. 
												// return true if the values are opposite, false if they are the same.
	}
	
	/**
	 * returns whether the board equals the given board
	 * @param y the other object to compare this to
	 * @return true if they are the same, false if not
	 */
	@Override
	public boolean equals(Object y){
		if (y == this) return true;
		if (y == null) return false;
		if (this.getClass() != y.getClass()) return false;
		Board other = (Board) y;
		for (int i = 0; i < tiles.length; i++)
			if (this.tiles[i] != other.tiles[i]) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(tiles);
		return result;
	}

	/**
	 * returns all neighboring boards
	 * @return all neightboaring boards
	 */
	public Iterable<Board> neighbors(){
		Stack<Board> neighbors = new Stack<>();
		
		if (zeroLocation / N != 0) // has up neighbor?
			pushNeighborToStack(neighbors, -N);
		if (zeroLocation / N != N - 1) // has down neighor?
			pushNeighborToStack(neighbors, N);
		if (zeroLocation % N != 0) // has left neighbor?
			pushNeighborToStack(neighbors, -1);
		if (zeroLocation % N != N - 1) // has right neighbor?
			pushNeighborToStack(neighbors, 1);
		
		return neighbors;
	}
	  
	/**
	 * returns a string representation of this board
	 * @return a string representation of this board
	 */
	public String toString(){
		StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < tiles.length; i++) {
            s.append(String.format("%2d ", tiles[i]));
            if ((i + 1) % N == 0)
            	s.append("\n");
	    }
	    return s.toString();
	}
	
	// ************** //
	// HELPER METHODS //
	// ************** //
	
	// pushes neighboring boards to the neighbors stack for the neighbors() method
	private void pushNeighborToStack(Stack<Board> neighbors, int displace) {
		swap(tiles, zeroLocation, zeroLocation + displace); // Swap neighbor tile and blank tile
		neighbors.push(new Board(tiles, N, zeroLocation + displace)); // Push neighbor board to stack
		swap(tiles, zeroLocation, zeroLocation + displace); // Switch tiles back to its original state
	}
	
	// swaps two tiles. 
	private void swap(int[] swapBoard, int pos1, int pos2) {
		int swap = swapBoard[pos1];
		swapBoard[pos1] = swapBoard[pos2];
		swapBoard[pos2] = swap;
	}
	
	// constructor for the new neighbors.
	private Board(int[] blocks, int N, int zeroLocation) {
		this.N = N;
		this.zeroLocation = zeroLocation;
		tiles = new int[N*N];
		System.arraycopy(blocks, 0, tiles, 0, tiles.length); // faster than a for loop
	}
	
	public static void main(String[] args) {
		Board testBoard = new Board(new int[][]{
				{8, 1, 3},
				{4, 0, 2},
				{7, 6, 5}});
		StdOut.println(testBoard.toString());
	}
}
