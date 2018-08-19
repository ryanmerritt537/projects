package ch5Projects;

public class AndChecker implements Checker {
	Checker a;
	Checker b;
	public AndChecker(Checker aInst, Checker bInst){
		a = aInst;
		b = bInst;
	}
	public boolean accept(String str){
		return a.accept(str) && b.accept(str);
	}
}
