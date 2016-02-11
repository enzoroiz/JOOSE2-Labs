import java.util.Scanner;

/**
 * Palindromes class Java lab 2, 2014
 * 
 * @author jsinger
 */

public class Palindromes {

	/**
	 * entrypoint to the program
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String possiblePalindrome = preprocess(sc.nextLine());
		System.out.println("is "
				+ (isPalindrome(possiblePalindrome) ? "" : "not ")
				+ "a palindrome");
		sc.close();
	}

	/**
	 * @author enzoroiz
	 * @return true if the input string is a palindrome, false otherwise.
	 */
	public static boolean isPalindrome(String s) {
		//Pairs chars that walk through the string
		char leftToRight;
		char rightToLeft;

		// Make the paired comparison walking through the string
		for (int i = s.length() - 1; i >= 0; i--) {
			leftToRight = s.charAt(i);
			rightToLeft = s.charAt((s.length() - 1) - i);

			// If pairs are different return false
			if (leftToRight != rightToLeft) {
				return false;
			}

			// If all the string has been compared the flow goes out of the for
			if (i <= s.length() / 2) {
				break;
			}
		}

		return true;
	}

	/**
	 * This method strips out spaces and punctuation. It also normalizes all
	 * characters to lower case.
	 * 
	 * @return a new String with only lower-case characters.
	 */
	public static String preprocess(String s) {
		// Put the string into lower case letters Bab = bab = bAb = BaB and so on. . .
		s = s.toLowerCase();
		
		//Remove the white spaces in the string
		s = s.replaceAll("\\s","");
		
		//Remove all characters that are not word characters
		s = s.replaceAll("\\W", "");
		
		return s;
	}
	
}