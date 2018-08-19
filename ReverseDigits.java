package summerProject;

import java.util.Scanner;

public class ReverseDigits {
	public static void main(String[] args) {
		//reverses the digits in a number
		Scanner kbd = new Scanner(System.in);
		System.out.println("Put in an integer");
		int num = kbd.nextInt();
		int rev = 0;
		String f = "";
		do {
			rev = num % 10;
			num /= 10;
			f += Integer.toString(rev);
		} while (num > 0);
		System.out.println(f);
		kbd.close();
	}
}
