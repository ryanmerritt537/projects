package ch4Projects;

import java.util.Scanner;
import java.text.DecimalFormat;

public class SystemOfEquation {
	static boolean ask = true;
	static Scanner kbd = new Scanner(System.in);
	static Line l1;
	static Line l2;
	static int choice = 0;
	static DecimalFormat df = new DecimalFormat("0.00");

	public static void s(String str) {
		System.out.println(str);
	}

	// the menu
	public static void displayMenu() {
		s("Enter a number for an equation--");
		s("1: x = y");
		s("2: Ax + By = C");
		s("3: line from two points");
		s("4: point-slope form");
		s("5: y = mx + b");
	}

	// creates lines
	public static void choices() {
		int A= 0;
		int B= 0;
		int C = 0;
		Point P = new Point();
		Point Q = new Point();
		double m = 0;
		double b = 0;
		double x = 0;
		double y = 0;
		double inp = 0;
		displayMenu(); // ask for 1st line
		// creates line
		choice = kbd.nextInt();
		if (choice == 1) {
			l1 = new Line();
		}
		if (choice == 2) {
			s("Enter a value for A");
			A = kbd.nextInt();
			s("Enter a value for B");
			B = kbd.nextInt();
			s("Enter a value for C");
			C = kbd.nextInt();
			l1 = new Line(A, B, C);
		}
		if (choice == 3) {
			s("Enter an x value for 1st point");
			x = kbd.nextDouble();
			s("Enter a y value for 1st point");
			y = kbd.nextDouble();
			P.setXY(x, y);
			s("Enter an x value for 2st point");
			x = kbd.nextDouble();
			s("Enter a y value for 2st point");
			y = kbd.nextDouble();
			Q.setXY(x, y);
			l1 = new Line(P, Q);
		}
		if (choice == 4) {
			s("Enter an x value for point");
			x = kbd.nextDouble();
			s("Enter a y value for point");
			y = kbd.nextDouble();
			P.setXY(x, y);
			s("Enter a slope");
			m = kbd.nextDouble();
			l1 = new Line(P, m);
		}
		if (choice == 5) {
			s("Enter a slope");
			m = kbd.nextDouble();
			s("Enter a y intercept");
			b = kbd.nextDouble();
			l1 = new Line(m, b);
		}
		displayMenu();// ask for 2nd line
		choice = kbd.nextInt();
		// create line
		if (choice == 1) {
			l2 = new Line();
		}
		if (choice == 2) {
			s("Enter a value for A");
			A = kbd.nextInt();
			s("Enter a value for B");
			B = kbd.nextInt();
			s("Enter a value for C");
			C = kbd.nextInt();
			l2 = new Line(A, B, C);
		}
		if (choice == 3) {
			s("Enter an x value for 1st point");
			x = kbd.nextDouble();
			s("Enter a y value for 1st point");
			y = kbd.nextDouble();
			P.setXY(x, y);
			s("Enter an x value for 2st point");
			x = kbd.nextDouble();
			s("Enter a y value for 2st point");
			y = kbd.nextDouble();
			Q.setXY(x, y);
			l2 = new Line(P, Q);
		}
		if (choice == 4) {
			s("Enter an x value for point");
			x = kbd.nextDouble();
			s("Enter a y value for point");
			y = kbd.nextDouble();
			P.setXY(x, y);
			s("Enter a slope");
			m = kbd.nextDouble();
			l2 = new Line(P, m);
		}
		if (choice == 5) {
			s("Enter a slope");
			m = kbd.nextDouble();
			s("Enter a y intercept");
			b = kbd.nextDouble();
			l2 = new Line(m, b);
		}
	}

	// solves for x
	public static Rational xSolution() {
		Point P = new Point();
		if (l2.isHorizontal() == false) {
			P = new Point(l1.intersection(l2).getRX(),l1.intersection(l2).getRY());
		} else {
			P = new Point(l2.intersection(l1).getRX(),l2.intersection(l1).getRY());
		}
		Rational ffff = new Rational(P.getRX().getP(),P.getRX().getQ());
		ffff.reduce();
		return ffff;
	}

	// solves for y
	public static Rational ySolution() {
		Point P = new Point();
		if (l2.isHorizontal() == false) {
			P = new Point(l1.intersection(l2).getRX(),l1.intersection(l2).getRY());
		} else {
			P = new Point(l2.intersection(l1).getRX(),l2.intersection(l1).getRY());
		}
		Rational ffff = new Rational(P.getRY().getP(),P.getRY().getQ());
		ffff.reduce();
		return ffff;
	}

	public static void main(String[] args) {
		while (ask) {
			choices();
			// restarts thing if points are the same
			if ((l2.getPointsEqual() || l1.getPointsEqual())) {
				s("Points for one line were the same, asking for new equations");
				choices();
			}
			if (l1.isVertical() == true && l2.isVertical() == true) {
				s("Lines: \n" + l1.toString());
				s(l2.toString());
				if(!l1.equals(l2)){
					s("No solutions, lines are paralell and vertical");
				}else{
					s("Infinite solutions");
				}
			} else if (!l1.equals(l2) && l1.IsParallel(l2)) {
				s("Lines: \n" + l1.toString());
				s(l2.toString());
				s("No Solution, lines are paralell");
			} else if (l1.equals(l2)) {
				s("Lines: \n" + l1.toString());
				s(l2.toString());
				s("Infinite Solutions");
			} else {
				s("Lines: \n" + l1.toString());
				s(l2.toString());
				s("Solution: ");
				s("x = " + xSolution().toString());
				s("y = " + ySolution().toString());
				s("Solution set:\n( " + xSolution().toString() + " , " + ySolution().toString() + " )");
			}
			s("Press 1 to enter another set of equations");
			s("Press 0 to quit");
			int n = kbd.nextInt();
			if (n == 1) {
				ask = true;
			} else {
				ask = false;
			}
		}
	}
}