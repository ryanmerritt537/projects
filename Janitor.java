package ch7Projects;

public class Janitor extends HospitalEmployee{
	private String d;
	public Janitor(String n){
		super(n);
		d = "Cleans up everything that needs cleaning";
	}
	public String toString(){
		return super.toString() + " as a Janitor, " + d;
	}
}
