/**	
 * Names:		Andrew Lewin & Spenser Riches
 * Class:		CSIS 2420
 * Assignment:	A06 WordNet
 * Due Date: 	05/01/15
 */

package wordnet;

import static org.junit.Assert.*;

import org.junit.Test;

public class OutcastTest {

	@Test
	public void test() {
		WordNet wn = new WordNet("tests/wordnet/videoSynsets.txt", "tests/wordnet/videoHypernyms.txt");
		Outcast oc = new Outcast(wn);
		String[] nouns1 = {"slip", "schoolchild", "school-age_child", "pupil", "puppy", "pup", "hobbledehoy", "Frankie_the_urchin"};
		assertEquals("Frankie_the_urchin", oc.outcast(nouns1));
		
		String[] nouns2 = {"young_man", "younker", "rocker", "stripling"};
		assertEquals("younker", oc.outcast(nouns2));
		
		String[] nouns3 = {"sprog", "punk_rock", "rock'n'roll", "popular_music_genre"};
		assertEquals("sprog", oc.outcast(nouns3));
	}
}