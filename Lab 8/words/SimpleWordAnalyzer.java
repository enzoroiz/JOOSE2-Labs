package words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to analyse words read from a file
 * 
 * @author enzoroiz
 */
public class SimpleWordAnalyzer implements WordAnalyzer {
	private String fileName;

	/**
	 * Constructor
	 * 
	 * @param fileName
	 */
	public SimpleWordAnalyzer(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the file name
	 */
	@Override
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Read a file and search for the longest word starting with a certain
	 * character
	 * 
	 * @param character
	 * @return longest word starting with the character
	 */
	@Override
	public String longestWordStartingWith(char character) {
		if (Character.isUpperCase(character) || !Character.isLetter(character)) {
			throw new IllegalArgumentException(
					"Upper case or not letter character! Expected lower case character.");
		}

		ArrayList<String> words = readFile();
		String longestWord = "";

		// For each of words read, checks if it is the longest for the given
		// character
		for (String word : words) {
			if (word.charAt(0) == character) {
				if (word.length() > longestWord.length()) {
					longestWord = word;
				}
			}
		}

		return longestWord;
	}

	/**
	 * Read a file and search for the word with more occurrences of a certain
	 * character
	 * 
	 * @param character
	 * @return word with more occurrences of the character
	 */
	@Override
	public String wordWithMostOccurrencesOf(char character) {
		if (Character.isUpperCase(character) || !Character.isLetter(character)) {
			throw new IllegalArgumentException(
					"Upper case or not letter character! Expected lower case character.");
		}

		ArrayList<String> words = readFile();
		String wordWithMostOccurrences = "";
		int occurrences;
		int highestOccurrences = 0;

		// For each of words read, checks if it is the one with more occurrences
		// for the given character
		for (String word : words) {
			if ((occurrences = charOccurrences(word, character)) > highestOccurrences) {
				highestOccurrences = occurrences;
				wordWithMostOccurrences = word;
			}
		}

		return wordWithMostOccurrences;
	}

	/**
	 * Read a file and stores words found in an ArrayList<String>
	 * 
	 * @return an array list containing all the words read
	 */
	private ArrayList<String> readFile() {
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		String currentLine = null;
		ArrayList<String> words = new ArrayList<>();
		try {
			fileReader = new FileReader(new File(fileName));
			bufferedReader = new BufferedReader(fileReader);

			// Iterates over each line and adds it to the ArrayList
			while ((currentLine = bufferedReader.readLine()) != null) {
				currentLine = currentLine.toLowerCase();
				words.add(currentLine);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		return words;
	}

	/**
	 * Iterate over an word and counts the occurrence of a certain character
	 * 
	 * @param word
	 *            to iterate over
	 * @param character
	 *            to count occurrences
	 * @return number of occurrences of the given character
	 */
	private int charOccurrences(String word, char character) {
		int counter = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == character) {
				counter++;
			}
		}

		return counter;
	}

}
