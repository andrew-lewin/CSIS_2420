package a048puzzle;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class BoardTest {
	private final Board testBoard = new Board(new int[][]{
													{8, 1, 3},
													{4, 0, 2},
													{7, 6, 5}});
	private final Board testGoalBoard = new Board(new int[][]{
													{ 1,  2,  3,  4},
													{ 5,  6,  7,  8},
													{ 9, 10, 11, 12},
													{13, 14, 15,  0}});
	private final Board test3Board = new Board(new int[][]{{2,0},{1,3}});
	private final Board test1Board = new Board(new int[][]{{0}});
	
	@Test
	public void testBoard() {
		// Test if immutable
		int[][] boardArray = {{1,2,3},{4,5,6},{7,8,0}};
		Board immutableBoard = new Board(boardArray);
		boardArray[2][2] = 9;
		assertEquals("3\n 1  2  3 \n 4  5  6 \n 7  8  0 \n", immutableBoard.toString());
	}

	@Test
	public void testSize() {
		assertEquals(3, testBoard.size());
		assertEquals(4, testGoalBoard.size());
		assertEquals(2, test3Board.size());
		assertEquals(1, test1Board.size());
	}

	@Test
	public void testHamming() {
		assertEquals(5, testBoard.hamming());
		assertEquals(0, testGoalBoard.hamming());
		Board hammingIsOne = new Board(new int[][]{{1,2,3},{4,5,6},{7,0,8}});
		assertEquals(1, hammingIsOne.hamming());
		assertEquals(3, test3Board.hamming());
		assertEquals(0, test1Board.hamming());
	}

	@Test
	public void testManhattan() {
		assertEquals(10, testBoard.manhattan());
		assertEquals(0, testGoalBoard.manhattan());
		assertEquals(3, test3Board.manhattan());
		assertEquals(0, test1Board.manhattan());
	}

	@Test
	public void testIsGoal() {
		assertEquals(false, testBoard.isGoal());
		assertEquals(true, testGoalBoard.isGoal());
		assertEquals(false, test3Board.isGoal());
		assertEquals(true, test1Board.isGoal());
	}

	@Test
	public void testIsSolvable() {
		assertEquals(true, testBoard.isSolvable());
		assertEquals(true, testGoalBoard.isSolvable());
		assertEquals(true, test3Board.isSolvable());
		assertEquals(true, test1Board.isSolvable());
		Board notSolvable = new Board(new int[][]{{1,2,3},{4,5,6},{8,7,0}});
		assertEquals(false, notSolvable.isSolvable());
	}

	@Test
	public void testEqualsObject() {
		assertEquals(false, testBoard.equals(testGoalBoard));
		assertEquals(false, testGoalBoard.equals(testBoard));
		Board equalsTestBoard = new Board(new int[][]{{8, 1, 3},{4, 0, 2},{7, 6, 5}});
		assertEquals(true, testBoard.equals(equalsTestBoard));
		assertEquals(true, equalsTestBoard.equals(testBoard));
		assertEquals(true, test1Board.equals(new Board(new int[][]{{0}})));
	}

	@Test
	public void testNeighbors() {
		Iterable<Board> neighbors = testBoard.neighbors();
		Board[] correctNeighborsTestBoard = {
				new Board(new int[][]{{8, 0, 3},{4, 1, 2},{7, 6, 5}}),
				new Board(new int[][]{{8, 1, 3},{4, 6, 2},{7, 0, 5}}),
				new Board(new int[][]{{8, 1, 3},{0, 4, 2},{7, 6, 5}}),
				new Board(new int[][]{{8, 1, 3},{4, 2, 0},{7, 6, 5}})};
		
		for (Board neighbor : neighbors) {
			if (!Arrays.asList(correctNeighborsTestBoard).contains(neighbor))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" + 
						correctNeighborsTestBoard[0].toString() + correctNeighborsTestBoard[1].toString() + 
						correctNeighborsTestBoard[2].toString() + correctNeighborsTestBoard[3].toString());
		}
		
		neighbors = testGoalBoard.neighbors();
		Board[] correctNeighborsTestGoalBoard = {
				new Board(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,0},{13,14,15,12}}),
				new Board(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}})};
		
		for (Board neighbor : neighbors) {
			if (!Arrays.asList(correctNeighborsTestGoalBoard).contains(neighbor))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" + 
						correctNeighborsTestGoalBoard[0].toString() + correctNeighborsTestGoalBoard[1].toString());
		}
		
		neighbors = test3Board.neighbors();
		Board[] correctNeighborsTest3Board = {
				new Board(new int[][]{{2,3},{1,0}}),
				new Board(new int[][]{{0,2},{1,3}})
		};
		
		for (Board neighbor : neighbors) {
			if (!Arrays.asList(correctNeighborsTest3Board).contains(neighbor))
				fail("Fail.\nGiven:\n" + neighbors.toString() + "Expected:\n" +
						correctNeighborsTest3Board[0].toString() + correctNeighborsTest3Board[1].toString());
		}
		
		neighbors = test1Board.neighbors();
		
		for (Board neighbor : neighbors) {
			fail("Fail. Expected no neighbors but returned:\n" + neighbor);
		}

	}

	@Test
	public void testToString() {
		assertEquals("3\n 8  1  3 \n 4  0  2 \n 7  6  5 \n", testBoard.toString());
		assertEquals("4\n 1  2  3  4 \n 5  6  7  8 \n 9 10 11 12 \n13 14 15  0 \n", testGoalBoard.toString());
		assertEquals("2\n 2  0 \n 1  3 \n", test3Board.toString());
		assertEquals("1\n 0 \n", test1Board.toString());
	}

}
