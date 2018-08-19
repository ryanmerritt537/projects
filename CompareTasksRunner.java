package ch5Projects;

public class CompareTasksRunner {
	public static void main(String[] args) {
		Task t1 = new Task();
		t1.setPriority(24);
		Task t2 = new Task();
		t2.setPriority(26);
		System.out.println("Task 1 ranking: " + t1.compareTo(t2));
		System.out.println("Task 2 ranking: " + t2.compareTo(t1));
	}
}
