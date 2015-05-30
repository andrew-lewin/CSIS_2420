package autoComplete;

import java.util.Arrays;
import edu.princeton.cs.algs4.Quick3way;

public final class Autocomplete {
	private final Term[] terms;
	
	/**
	 * Initialize the data structure from the given array of terms.
	 * @param terms - array of terms to initialize with
	 */
    public Autocomplete(Term[] terms) {
    	if (terms == null) throw new NullPointerException("Terms can't be null");
    	this.terms = new Term[terms.length];
    	for (int i = 0; i < terms.length; i++) 
    		this.terms[i] = terms[i];
    	Quick3way.sort(this.terms);
    }

    /**
     * Return all terms that start with the given prefix, in descending order of weight.
     * @param prefix the prefix you wish to search for
     * @return an array containing all terms that match the given prefix
     */
    public Term[] allMatches(String prefix) {
    	if (prefix == null) throw new NullPointerException("Prefix can't be null");
    	
    	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	if (firstIndex == -1) return new Term[0];
    	int lastIndex  = BinarySearchDeluxe.lastIndexOf (terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    	Term[] matchTerms = new Term[1 + lastIndex - firstIndex];
    	
    	for (int i = 0; i < matchTerms.length; i++)
    		matchTerms[i] = terms[firstIndex++];

    	Arrays.sort(matchTerms, Term.byReverseOrder());
    	
		return matchTerms;
    }

    /**
     * Return the number of terms that start with the given prefix.
     * @param prefix the prefix you wish to search for
     * @return number of terms that start with the given prefix
     */
    public int numberOfMatches(String prefix) {
    	if (prefix == null) throw new NullPointerException("Prefix can't be null");
		return 1 + 
				BinarySearchDeluxe.lastIndexOf (terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length())) - 
				BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    }
    
    public static void main (String[] args) {
    	Term[] terms = {new Term("Aaa", 10), new Term("Aab", 1), new Term("Abc", 2), new Term("Aab", 3), new Term("Aaa", 3),
    					new Term("Bbb", 0), new Term("Bbc", 0), new Term("Bcd", 0),
    					new Term("Ccc", 0), new Term("Ccd", 0), new Term("Cde", 0),
    					new Term("Ddd", 0), new Term("Dde", 0), new Term("Def", 0),
    					new Term("Eee", 0), new Term("Eef", 0), new Term("Efg", 0)};
    	Autocomplete autocomplete = new Autocomplete(terms);
    	System.out.println(autocomplete.numberOfMatches("E"));
    	
    	Term[] stuff = autocomplete.allMatches("A");
    	
    	for (Term term : stuff) System.out.println(term);
    }
}
