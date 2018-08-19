package ch7Projects;

public class Doctor extends HospitalEmployee{
	private String d;
	public Doctor(String n){
		super(n);
		d = "Tells you what is wrong with you";
	}
	public String toString(){
		return super.toString() + " as a doctor, " + d;
	}
}
