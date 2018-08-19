package ch7Projects;

public class Nurse extends HospitalEmployee{
	private String d;
	public Nurse(String n){
		super(n);
		d = "Helps patients get around and does all the paperwork for the doctor";
	}
	public String toString(){
		return super.toString() + " as a nurse, " + d;
	}
}
