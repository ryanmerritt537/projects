package ch5Projects;

public class NotChecker implements Checker{
	private Checker nCheck;
	public NotChecker(Checker c){
		nCheck = c;
	}
	public boolean accept(String str){
		return !nCheck.accept(str);
	}
}
