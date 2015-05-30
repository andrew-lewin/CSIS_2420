/**	
 * Names:		Andrew Lewin & Spenser Riches
 * Class:		CSIS 2420
 * Assignment:	A06 WordNet
 * Due Date: 	05/01/15
 */

package wordnet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordNetTest {
	WordNet wordnet;
	String[] nouns;

	@Before
	public void setUp() throws Exception {
		wordnet = new WordNet("inputFiles/synsets11.txt", "inputFiles/hypernyms11ManyPathsOneAncestor.txt");
		nouns = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
	}

	@Test (expected = NullPointerException.class)
	public void testWordNet() {
		new WordNet("", null);
		new WordNet(null, "");
		new WordNet(null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWordNetNotRootedDAG() {
		new WordNet("inputFiles/synsets8.txt", "tests/wordnet/hypernyms8TwoAncestorsModified.txt");
		new WordNet("inputFiles/synsets8.txt", "tests/wordnet/hypernyms8Cycle.txt");
	}

	@Test
	public void testNouns() {
		for (String word : wordnet.nouns()) {
			boolean isContained = false;
			for (int i = 0; i < nouns.length; i++) {
				if (word.equals(nouns[i])) isContained = true;
			}
			if (!isContained) fail(word + " is not contained");
		}
	}

	@Test
	public void testIsNoun() {
		for (int i = 0; i < nouns.length; i++)
			if (!wordnet.isNoun(nouns[i])) fail(nouns[i] + " is not a noun");
		String[] badNouns = {"aa", "bb", "bob", "cat", "derp", "a ", "a b"};
		for (int i = 0; i < badNouns.length; i++)
			if (wordnet.isNoun(badNouns[i])) fail(badNouns[i] + " shouldn't be a noun");
	}
	
	@Test (expected = NullPointerException.class)
	public void testIsNounBadInput() {
		wordnet.isNoun(null);
	}

	@Test
	public void testDistance() {
		assertEquals(2, wordnet.distance("b", "c"));
		assertEquals(1, wordnet.distance("a", "d"));
		assertEquals(0, wordnet.distance("f", "f"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testDistanceBadInput() {
		wordnet.distance(null, null);
		wordnet.distance("", null);
		wordnet.distance(null, "");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDistanceNounNotContained() {
		wordnet.distance("derp", "herp");
		wordnet.distance("a", "joe");
		wordnet.distance("aaah", "e");
	}

	@Test
	public void testSap() {
		assertEquals("f", wordnet.sap("b", "c"));
		assertEquals("a", wordnet.sap("a", "a"));
		assertEquals("f", wordnet.sap("a", "k"));
		assertEquals("b", wordnet.sap("a", "b"));
	}
	
	@Test (expected = NullPointerException.class)
	public void testSapBadInput() {
		wordnet.sap(null, null);
		wordnet.sap("", null);
		wordnet.sap(null, "");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSapNounNotContained() {
		wordnet.sap("bob", "a");
		wordnet.sap("b", "aa");
		wordnet.sap("cc", "b ");
	}
}