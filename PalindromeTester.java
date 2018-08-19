package ch4Projects;

import java.util.Scanner;

public class PalindromeTester {
	//removes any punctiation from a string
	public static String removePunctuation(String str) {
		String nStr = "";
		str.toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				nStr += str.charAt(i);
			}
		}
		return nStr;
	}
	//returns a string that is the input backwards
	public static String reverseString(String str) {
		String nStr = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			nStr += str.charAt(i);
		}
		return nStr;
	}
	//returns true if the input is a plaindrome
	//otherwise return false
	public static boolean isPalindrome(String str) {
		str = removePunctuation(str);
		if (str.equals(reverseString(str))) {
			return true;
		}
		return false;
	}
	//main method
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		int on = 1;
		while (on == 1) {
			String str = kbd.nextLine();
			boolean iP = isPalindrome(str);
			if (iP)
				System.out.println("Is palindrome");
			else
				System.out.println("Is not palindrome");
			System.out.println("type 1 to try again," 
				+ "type 0 to quit");
			on = kbd.nextInt();
			String temp = kbd.nextLine();
		}
	}
}
