package ch7Projects;

public class Surgeon extends HospitalEmployee{
	private String d;
	public Surgeon(String n){
		super(n);
		d = "Actually does the operations that can save lives";
	}
	public String toString(){
		return super.toString() + " as a Surgeon, " + d;
	}
}
