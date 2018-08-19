package ch7Projects;

public class Receptionist extends HospitalEmployee{
	private String d;
	public Receptionist(String n){
		super(n);
		d = "Asks you some stuff, then tells you to sit in the waiting room";
	}
	public String toString(){
		return super.toString() + " as a Receptionist, " + d;
	}
}
