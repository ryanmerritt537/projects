package moreCh7Projects;

public class Textbook extends Reading {
	String subject;
	public Textbook(int n,String au, String s){
		super("textbook", n,au);
		subject = s;
	}
	public String toString(){
		return super.toString()+ "\nsubject: "+subject;
	}
}
