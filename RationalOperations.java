package ch4Projects;

import java.util.Scanner;

public class RationalOperations {
	static Scanner kbd = new Scanner(System.in);

	public static void s(String str) {
		System.out.println(str);
	}

	public static void add(int n1, int d1, int n2, int d2) {
		int numerator = n1 * d2 + n2 * d1;
		int denominator = d1 * d2;
		System.out.println("result: "+numerator / GCF(numerator, denominator) + "/" + denominator / GCF(numerator, denominator));
	}

	public static void subtract(int n1, int d1, int n2, int d2) {
		int numerator = Math.abs(n1 * d2 - n2 * d1);
		int denominator = d1 * d2;
		System.out.println("result: "+numerator / GCF(numerator, denominator) + "/" + denominator / GCF(numerator, denominator));
	}

	public static void multiply(int n1, int d1, int n2, int d2) {
		int numerator = n1 * n2;
		int denominator = d1 * d2;
		System.out.println("result: "+numerator / GCF(numerator, denominator) + "/" + denominator / GCF(numerator, denominator));
	}

	public static void divide(int n1, int d1, int n2, int d2) {
		int numerator = n1 * d2;
		int denominator = d1 * n2;
		System.out.println("result: "+numerator / GCF(numerator, denominator) + "/" + denominator / GCF(numerator, denominator));
	}

	public static int menu() {
		s("\nEnter 1 to add\nEnter 2 to subtract\nEnter 3 to divide\nEnter 4 to multiply\nEnter 0 to quit");
		int n = kbd.nextInt();
		return n;
	}

	public static int GCF(int num1, int num2) {
		int gcf = 1;
		for (int i = 1; i <= num1; i++) {
			if (num1 % i == 0 && num2 % i == 0) {
				gcf = i;
			}
		}
		return gcf;
	}

	public static void main(String[] args) {
		int n = menu();
		while (n != 0) {
			s("Enter a numerator for the first fraction");
			int p1 = kbd.nextInt();
			s("Enter a denominator for the first fraction");
			int q1 = kbd.nextInt();
			s("Enter a numerator for the second fraction");
			int p2 = kbd.nextInt();
			s("Enter a denominator for the second fraction");
			int q2 = kbd.nextInt();
			if (n == 1) {
				add(p1, q1, p2, q2);
			} else if (n == 2) {
				subtract(p1, q1, p2, q2);
			} else if (n == 3) {
				multiply(p1, q1, p2, q2);
			} else if (n == 4) {
				divide(p1, q1, p2, q2);
			}
			n = menu();
		}
	}
}
