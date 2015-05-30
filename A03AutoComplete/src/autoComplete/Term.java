package autoComplete;

import java.util.Arrays;
import java.util.Comparator;

public final class Term implements Comparable<Term> {
	private final String query;
	private final double weight;
	
	/**
	 * Initialize a term with the given query and weight.
	 * @param query - term to be assigned
	 * @param weight - weight the term will have
	 */
	public Term(String query, double weight) {
		if (query == null) throw new NullPointerException("Query cannot be null");
		if (weight < 0) throw new IllegalArgumentException("weight must not be less than 0");
		this.query = query;
		this.weight = weight;
	}
	
	/**
	 * Compare the terms in decending order by weight.
	 * @return a Comparator that will compare the terms in decending order by weight
	 */
	public static Comparator<Term> byReverseOrder() {
		return new ComparatorByReverseOrderWeight();
	}
	
	/**
	 * Compare the terms in lexicographic order by using only the first r characters of the query.
	 * @param r - number of characters to consider in the compare
	 * @return a Comparator that will compare the first r terms in lexicographic order
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		if (r < 0) throw new IllegalArgumentException("r must not be less than 0");
		return new ComparatorByPrefixOrderQuery(r);
	}
	
	/**
	 * Compare the terms in lexicographic order by query.
	 * @return whether the term is smaller, the same, or bigger than the one it's being compared to
	 */
	@Override
	public int compareTo(Term o) {
		return this.query.compareTo(o.query);
	}
	
	/**
	 * Return a string representation of the Term in the format weight + tab + query.
	 * @return a string representation of the Term.
	 */
	@Override
	public String toString() {
		return weight + "\t" + query;
	}
	
	private static class ComparatorByReverseOrderWeight implements Comparator<Term> {
		@Override
		public int compare(Term a, Term b) {
			if (a.weight == b.weight) return 0;
			if (a.weight > b.weight) return -1;
			return 1;
		}
	}
	
	private static class ComparatorByPrefixOrderQuery implements Comparator<Term> {
		private int r;
		
		private ComparatorByPrefixOrderQuery(int r) {
			this.r = r;
		}

		@Override 
		public int compare(Term a, Term b) {
			String prefixA;
			String prefixB;
			
			if (a.query.length() < r) prefixA = a.query;
			else prefixA = a.query.substring(0, r);
			
			if (b.query.length() < r) prefixB = b.query;
			else prefixB = b.query.substring(0, r);

			return prefixA.compareTo(prefixB);
		}
	}
	
	public static void main(String[] args) {
		Term[] terms = {new Term("Debbie", 3), new Term("Abcd", 8), new Term("Cathy", 1.55555), new Term("Abbcd", 0.1)};
		for (Term term : terms) System.out.println(term);
		System.out.println();
		
		Arrays.sort(terms, Term.byReverseOrder());
		for (Term term : terms) System.out.println(term);
		System.out.println();
		
		Arrays.sort(terms, Term.byPrefixOrder(2));
		for (Term term : terms) System.out.println(term);
		System.out.println();
		
		Arrays.sort(terms);
		for (Term term : terms) System.out.println(term);
	}
}
