package ch7Projects;

public class HospitalEmployee {
	private String description;
	private String name;
	public HospitalEmployee(String n){
		name = n;
		description = "works at a hospital";
	}
	public String toString(){
		return name + " " + description;
	}
}
