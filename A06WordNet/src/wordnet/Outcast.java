/**	
 * Names:		Andrew Lewin & Spenser Riches
 * Class:		CSIS 2420
 * Assignment:	A06 WordNet
 * Due Date: 	05/01/15
 */

package wordnet;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.introcs.StdOut;

public class Outcast {

	private WordNet wn;
	
	/**
	 * Constructor takes a WordNet object
	 * @param wordnet
	 */
	public Outcast(WordNet wordnet){
		this.wn = wordnet;
	}
 
	/**
	 * Given an array of WordNet nouns, return an outcast
	 * @param nouns
	 * @return
	 */
	public String outcast(String[] nouns){
		// Symbol table to store the total distance with the corresponding noun
		ST<Integer, String> st = new ST<>();
		// Max priority queue to keep track of the greatest distance noun
		MaxPQ<Integer> maxPQ = new MaxPQ<>();
		
		for(int i=0; i<nouns.length; i++){
			int distance = 0;
			for(int j=0; j<nouns.length; j++){
				distance += wn.distance(nouns[i], nouns[j]);
			}
			// Add total to maxPQ
			maxPQ.insert(distance);
			
			// Add total and noun to symbol table
			st.put(distance, nouns[i]);
		}
		// Return the noun with the greatest distance
		return st.get(maxPQ.max());
	}
   
	public static void main(String[] args){
		String[] nounList = {"dog", "cat", "bird", "car"};
		WordNet wordnet = new WordNet("inputFiles/synsets.txt", "inputFiles/hypernyms.txt");
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 0; t < nounList.length; t++) {
	        StdOut.println(nounList[t] + ": " + outcast.outcast(nounList));
	    }
	}
}