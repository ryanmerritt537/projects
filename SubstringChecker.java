package ch5Projects;

public class SubstringChecker implements Checker {
	private String str;
	public SubstringChecker(String text){
		str = text;
	}
	public boolean accept(String text){
		return text.indexOf(str)!=-1;
	}
}
