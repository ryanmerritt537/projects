package ch4Projects;

import java.util.Scanner;

public class PigGame {
	static Scanner kbd = new Scanner(System.in);
	static int youDice1 = 0;
	static int youDice2 = 0;
	static int compDice1 = 0;
	static int compDice2 = 0;
	static int youDice = 0;
	static int compDice = 0;
	static int roll = 0;
	static int addedCompDice = 0;

	// returns value of computers 1st die
	public static int comp1() {
		int n = (int) (Math.random() * 5) + 1;
		return n;
	}

	// returns value of computers 2nd die
	public static int comp2() {
		int n = (int) (Math.random() * 5) + 1;
		return n;
	}

	// returns value of your 1st die
	public static int you1() {
		int n = (int) (Math.random() * 5) + 1;
		return n;
	}

	// reutrns the value of the you second die
	public static int you2() {
		int n = (int) (Math.random() * 5) + 1;
		return n;
	}

	// method to make strings easier to make
	public static void s(String str) {
		System.out.println(str);
	}

	// code for your turn
	public static void yourTurn() {
		youDice1 = you1();
		youDice2 = you2();
		s("You rolled " + youDice1 + " , " + youDice2);
		int DiceRound = youDice1 + youDice2;
		if (youDice1 != 1 && youDice2 != 1 
				&& !(youDice1 == 1 && youDice2 == 1) 
				&& youDice < 100 && compDice < 100) {
			youDice += youDice1 + youDice2;
			s("Points earned this round: " + DiceRound);
			s("Your total Score: " + youDice + "\n");
			if (youDice < 100) {
				s("Press 1 to roll again. "
				+ " Press 0 to let the computer roll.");
				roll = kbd.nextInt();
			}
		} else if ((youDice1 == 1 || youDice2 == 1) 
				&& (youDice1 != 1 || youDice2 != 1) 
				&& youDice < 100
				&& compDice < 100) {
			s("Points earned this round: " + 0);
			s("Your total Score: " + youDice);
			s("Dice switches sides");
			youDice1 = 0;
			youDice2 = 0;
			roll = 0;
		}
		if (youDice1 == 1 && youDice2 == 1 
				&& youDice < 100 && compDice < 100) {
			s("Points earned this round: " + 0);
			s("Your total Score: " + 0);
			s("Dice switches sides\n");
			youDice1 = 0;
			youDice2 = 0;
			youDice = 0;
			roll = 0;
		}
	}

	// code for the computers turn
	public static void computersTurn() {
		compDice1 = comp1();
		compDice2 = comp2();
		int DiceRound = compDice1 + compDice2;
		if (compDice1 != 1 && compDice2 != 1 
				&& !(compDice1 == 1 && compDice2 == 1) 
				&& youDice < 100
				&& compDice < 100) {
			compDice += compDice1 + compDice2;
			s("Computer rolled " + compDice1 + " , " + compDice2);
			s("Points computer earned this round: " + DiceRound);
			s("Computers total Score: " + compDice + "\n");
		}
		addedCompDice += compDice1 + compDice2;
		if ((compDice1 == 1 || compDice2 == 1) 
				&& !(compDice1 == 1 && compDice2 == 1) 
				&& youDice < 100
				&& compDice < 100) {
			s("Computer rolled " + compDice1 + " , " + compDice2);
			s("Points computer earned this round: " + 0);
			s("Computers total Score: " + compDice);
			s("Dice switches sides\n");
			compDice1 = 0;
			compDice2 = 0;
			roll = -1;
		}
		if (compDice1 == 1 && compDice2 == 1 
				&& youDice < 100 && compDice < 100) {
			s("Computer rolled " + compDice1 + " , " + compDice2);
			s("Points computer earned this round: " + 0);
			s("Computers Score: " + 0);
			s("Dice switches sides\n");
			compDice1 = 0;
			compDice2 = 0;
			compDice = 0;
			roll = -1;
		}
	}

	// this is the main method.
	public static void main(String[] args) {
		while (youDice < 100 && compDice < 100) {
			s("Press 1 to roll");
			roll = kbd.nextInt();
			while (roll == 1 && youDice1 != 1 && youDice2 != 1 
					&& youDice < 100 && compDice < 100) {
				yourTurn();
			}
			addedCompDice = 0;
			while (roll == 0 && addedCompDice < 20 
					&& compDice1 != 1 && compDice2 != 1 
					&& youDice < 100 && compDice < 100) {
				computersTurn();
			}
		}
		if (youDice > compDice)
			s("You won!");
		else
			s("Computer won!");
	}
}
