package summerProject;

import java.util.Scanner;
import java.text.DecimalFormat;

public class CalculatesGpa {
	public static void main(String[] args) {
		// calculates my gpa from last year.
		Scanner kbd = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.0");
		System.out.println("Enter grade for a class");
		double grade1 = kbd.nextDouble();
		System.out.println("Enter grade for a class");
		double grade2 = kbd.nextDouble();
		System.out.println("Enter grade for a class");
		double grade3 = kbd.nextDouble();
		System.out.println("Enter grade for a class");
		double grade4 = kbd.nextDouble();
		double avg = ((grade1 + grade2 + grade3 + grade4) / 4) / 20;
		if (avg < 2)
			System.out.println("Gpa is " + df.format(avg) + ", that is very low");
		if (avg >= 3.5)
			System.out.println("Gpa is " + df.format(avg) + ", congratulations");
		else if (avg >= 2 && avg < 3.5)
			System.out.println("Gpa is " + df.format(avg));
		kbd.close();
	}
}
