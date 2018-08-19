package ch4Projects;

public class Course {
	private String name;
	private int numStudents;
	private Student st1 = new Student();
	private Student st2= new Student();
	private Student st3= new Student();
	private Student st4= new Student();
	private Student st5= new Student();
	
	public Course(){
		name = "";
		numStudents = 0;
	}
	
	public Course(String n){
		name = n;
		numStudents = 0;
	}
	
	public double average(){
		if(numStudents == 1){
			return st1.average();
		}
		if(numStudents == 1){
			return st1.average()+st2.average()*1.0/5;
		}
		if(numStudents == 1){
			return st1.average()+st2.average()+st3.average()*1.0/5;
		}
		if(numStudents == 1){
			return st1.average()+st2.average()+st3.average()+st4.average()*1.0/5;
		}
		return st1.average()+st2.average()+st3.average()+st4.average()+st5.average()*1.0/5;
	}
	
	public void roll(){
		System.out.println(st1);
		System.out.println(st2);
		System.out.println(st3);
		System.out.println(st4);
		System.out.println(st5);
	}
	
	public void addStudent(String first, String last, Address home, Address school, int s1, int s2, int s3){
		if(numStudents==0){
			st1 = new Student(first, last, home, school, s1,s2, s3);
		}
		if(numStudents==1){
			st2 = new Student(first, last, home, school, s1,s2, s3);
		}
		if(numStudents==2){
			st3 = new Student(first, last, home, school, s1,s2, s3);
		}
		if(numStudents==3){
			st4 = new Student(first, last, home, school, s1,s2, s3);
		}
		if(numStudents==4){
			st5 = new Student(first, last, home, school, s1,s2, s3);
		}
	}
}
