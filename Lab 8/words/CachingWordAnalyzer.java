package words;

import java.util.HashMap;

/**
 * Class that extends SimpleWordAnalyzer and caches the words analyzed in order
 * to not reprocess the whole file
 * 
 * @author enzoroiz
 *
 */
public class CachingWordAnalyzer extends SimpleWordAnalyzer {
	private HashMap<Character, String> longestWords;
	private HashMap<Character, String> wordsWithMostOccurrences;

	/**
	 * Constructor
	 * 
	 * @param fileName
	 *            which contains the words to be analysed
	 */
	public CachingWordAnalyzer(String fileName) {
		super(fileName);
		longestWords = new HashMap<>();
		wordsWithMostOccurrences = new HashMap<>();
	}

	/**
	 * Checks if the longest word starting certain character was computed
	 * before, if not, computes it and stores in a hash map representing the
	 * cache, if yes, takes it from the cache
	 * 
	 * @param character
	 * @return longest word starting with the character
	 */
	@Override
	public String longestWordStartingWith(char character) {
		if (longestWords.get(character) == null) {
			longestWords.put(character,
					super.longestWordStartingWith(character));
		}

		return longestWords.get(character);
	}

	/**
	 * Checks if the words with most occurrence of certain character was
	 * computed before, if not, computes it and stores in a hash map
	 * representing the cache, if yes, takes it from the cache
	 * 
	 * @param character
	 * @return word with more occurrences of the character
	 */
	@Override
	public String wordWithMostOccurrencesOf(char character) {
		if (wordsWithMostOccurrences.get(character) == null) {
			wordsWithMostOccurrences.put(character,
					super.wordWithMostOccurrencesOf(character));
		}

		return wordsWithMostOccurrences.get(character);
	}
}
