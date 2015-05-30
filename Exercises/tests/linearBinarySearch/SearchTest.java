package linearBinarySearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchTest {
	private int[] numbers = {2,6,4,1,8,0,45,67};
	private int[] sortedNumbers = {-6,1,3,4,8,61,91};

	@Test
	public void testLinear() {
		assertEquals(0, Search.linear(numbers, 2));
		assertEquals(1, Search.linear(numbers, 6));
		assertEquals(2, Search.linear(numbers, 4));
		assertEquals(3, Search.linear(numbers, 1));
		assertEquals(4, Search.linear(numbers, 8));
		assertEquals(5, Search.linear(numbers, 0));
		assertEquals(6, Search.linear(numbers, 45));
		assertEquals(7, Search.linear(numbers, 67));
	}
	
	@Test
	public void testLinearNotFound() {
		assertEquals(-1, Search.linear(numbers, 22));
		assertEquals(-1, Search.linear(numbers, -6));
		assertEquals(-1, Search.linear(numbers, 48));
		assertEquals(-1, Search.linear(numbers, 111));
	}
	

	@Test
	public void testBinary() {
		assertEquals(0, Search.binary(sortedNumbers, -6));
		assertEquals(1, Search.binary(sortedNumbers, 1));
		assertEquals(2, Search.binary(sortedNumbers, 3));
		assertEquals(3, Search.binary(sortedNumbers, 4));
		assertEquals(4, Search.binary(sortedNumbers, 8));
		assertEquals(5, Search.binary(sortedNumbers, 61));
		assertEquals(6, Search.binary(sortedNumbers, 91));
	}
	
	@Test
	public void testBinaryNotFound() {
		assertEquals(-1, Search.binary(sortedNumbers, -1));
		assertEquals(-1, Search.binary(sortedNumbers, 12));
		assertEquals(-1, Search.binary(sortedNumbers, 1000));
		assertEquals(-1, Search.binary(sortedNumbers, -1000));
		assertEquals(-1, Search.binary(sortedNumbers, 7));
	}

}
