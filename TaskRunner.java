package ch5Projects;

public class TaskRunner {
	public static void main(String[] args){
		Task t1 = new Task();
		t1.setPriority(38);
		t1.setComplexity(42);
		Task t2 = new Task();
		t2.setComplexity(22);
		t2.setPriority(21);
		Task t3 = new Task();
		t3.setComplexity(52);
		t3.setPriority(13);
		
		//output
		System.out.println("Task 1 "+t1);
		System.out.println("Task 2 "+t2);
		System.out.println("Task 3 "+t3);
	}
}
