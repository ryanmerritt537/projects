package ch5Projects;

public class CheckerRunner {

	public static void main(String[] args) {
		Checker bChecker = new SubstringChecker("beets");
		Checker aChecker = new SubstringChecker("architokes");
		Checker cChecker = new SubstringChecker("carrots");	
		Checker bothChecker = new AndChecker(bChecker, cChecker);
		Checker veggies = new AndChecker(bothChecker, aChecker);
		//s is a class that I made to be a shortcut to System.out.println()
		//p is a static method within the class
		//this shortcut helps a lot
		s.p(""+bothChecker.accept("I love beets and carrots"));//should be true
		s.p(""+bothChecker.accept("beets are great"));//should be false
		s.p(""+veggies.accept("architokes, beets, and carrots"));//should be true
		s.p(""+bothChecker.accept("lalalalalalalalalalalala"));//should be false
		s.p(""+bothChecker.accept(" "));//should be false
		s.p(""+veggies.accept("architokes, beets, ... and nothing else "));//should be false
		s.p(""+veggies.accept("architokes, beets, but not carrots"));//should be true
	}

}
