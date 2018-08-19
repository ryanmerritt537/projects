package ch4Projects;

public class RationalTester {

	public static void main(String[] args){
		Rational r = new Rational(1.5);
		System.out.println(r.toString());
		r = new Rational(.5);
		System.out.println(r.toString());
		r = new Rational(1);
		System.out.println(r.toString());
		r = new Rational(1,2);
		Rational f = new Rational(2,3);
		r = r.multiply(f);
		System.out.println(r.toString());
		
	}
}
