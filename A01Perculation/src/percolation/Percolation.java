package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class
 * @author Andrew Lewin
 * @author Mark Richardson
 *
 */
public class Percolation {
	private boolean[] grid;
	private WeightedQuickUnionUF percolationChecker;
	private WeightedQuickUnionUF unionFind;
	private int size;
	private int virtualTop;
	private int virtualBottom;
	
	/**
	 * Create N by N grid, with all sites blocked
	 * @param N length and width
	 */
	public Percolation(int N) {
		if (N < 1) throw new IllegalArgumentException("Size must be greater than 0");
		size = N;
		virtualTop = N*N;
		virtualBottom = N*N + 1;
		percolationChecker = new WeightedQuickUnionUF((N*N) + 2);
		unionFind = new WeightedQuickUnionUF((N*N) + 1);
		grid = new boolean[N*N];
		for (int i = 0; i < N*N; i++) {
			grid[i] = false;
		}
	}
	
	/**
	 * Opens the site (row i, column j) if it is not open already
	 * @param i row
	 * @param j column
	 */
	public void open(int i, int j) {
		isValidInput(i, j);
		grid[oneDimensional(i, j)] = true;
		
		// Check if we aren't on the rightmost column and if right is open
		if (j < size - 1  && isOpen(i, j + 1))
			union(oneDimensional(i, j), oneDimensional(i, j) + 1);
		
		// Check if we aren't on the leftmost column and if left is open
		if (j > 0 && isOpen(i, j - 1))
			union(oneDimensional(i, j), oneDimensional(i, j) - 1);
		
		// Check if we aren't on the top row and if the row above is open
		// If false, check if we are on the top row
		if (i > 0 && isOpen(i - 1, j))
			union(oneDimensional(i, j), oneDimensional(i, j) - size);
		else if (i == 0)
			union(oneDimensional(i, j), virtualTop);
		
		// Check if we aren't on the bottom row and if the row below is open
		// If false, check if we are on the bottom row
		if (i < size - 1 && isOpen(i + 1, j))
			union(oneDimensional(i, j), oneDimensional(i, j) + size);
		else if (i == size - 1)
			percolationChecker.union(oneDimensional(i, j), virtualBottom);
	}
	
	/**
	 * Is site (row i, column j) open?
	 * @param i row
	 * @param j column
	 * @return true or false
	 */
	public boolean isOpen(int i, int j) {
		isValidInput(i, j);
		return grid[oneDimensional(i, j)] == true;
	}
	
	/**
	 * Is site (row i, column j) full?
	 * @param i row
	 * @param j column
	 * @return true or false
	 */
	public boolean isFull(int i, int j) {
		isValidInput(i, j);
		return unionFind.connected(oneDimensional(i, j), virtualTop);
	}
	
	/**
	 * Does the system percolate?
	 * @return
	 */
	public boolean percolates() {
		return percolationChecker.connected(virtualTop, virtualBottom);
	}
	
	/**
	 * Converts the 2d coordinates that are given to 1d coordinates.
	 * @param i row
	 * @param j column
	 * @return where the node is in a 1d array
	 */
	private int oneDimensional(int i, int j) {
		return (i * size) + j;
	}
	
	/**
	 * Checks if the inputs are in the proper bounds
	 * @param i row
	 * @param j column
	 */
	private void isValidInput(int i, int j) {
		if (i < 0 || i > (size-1))
			throw new IndexOutOfBoundsException("row index i = " + i + " must be between 0 and " + (size-1));
		if (j < 0 || j > (size-1))
			throw new IndexOutOfBoundsException("column index j = " + j + " must be between 0 and " + (size-1));
	}
	
	/**
	 * Merges the component containing site p with the component containing site q in both WeightedQuickUnionUF objects.
	 * @param i integer representing one site
	 * @param j integer representing other site
	 */
	private void union(int p, int q) {
		percolationChecker.union(p, q);
		unionFind.union(p, q);
	}

}
