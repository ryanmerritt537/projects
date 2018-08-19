package ch7Projects;

public class HospitalEmployeeRunner {
	public static void main(String[] args) {
		HospitalEmployee bob = new Doctor("bob");
		HospitalEmployee joe = new Janitor("joe");
		HospitalEmployee bobjoe = new Nurse("bobjoe");
		HospitalEmployee rob = new Receptionist("rob");
		HospitalEmployee robbob = new Surgeon("robbob");
		System.out.println(bob);
		System.out.println(joe);
		System.out.println(bobjoe);
		System.out.println(rob);
		System.out.println(robbob);
	}
}
