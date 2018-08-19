package ch4Projects;

public class Student {
	private String firstName, lastName;
	private Address homeAddress, schoolAddress;
	private int score1, score2, score3;

	// -----------------------------------------------------------------
	// Sets up this Student object with the specified initial values.
	// -----------------------------------------------------------------
	public Student(String first, String last, Address home, Address school) {
		firstName = first;
		lastName = last;
		homeAddress = home;
		schoolAddress = school;
		score1 = 0;
		score2 = 0;
		score3 = 0;
	}
	
	public Student(String first, String last, Address home, Address school, int s1, int s2, int s3){
		firstName = first;
		lastName = last;
		homeAddress = home;
		schoolAddress = school;
		score1 = s1;
		score2 = s2;
		score3 = s3;
	}
	
	public Student(){
		firstName = "";
		lastName = "";
		homeAddress = new Address();
		schoolAddress = new Address();
		score1 = 0;
		score2 = 0;
		score3 = 0;
	}
	
	public void setTestScore(int testNumber, int newScore){
		if(testNumber == 1)
			score1 = newScore;
		else if(testNumber == 2)
			score2 = newScore;
		else if(testNumber == 3)
			score3 = newScore;
	}
	
	public int getTestScore(int testNumber){
		if(testNumber == 1)
			return score1;
		else if(testNumber == 2)
			return score2;
		else if(testNumber == 3)
			return score3;
		return 0;
	}
	
	public double average(){
		return score1+score2+score3*1.0/3;
	}
	
	// -----------------------------------------------------------------
	// Returns this Student object as a string.
	// -----------------------------------------------------------------
	public String toString() {
		String result;
		result = firstName + " " + lastName + "\n";
		result += "Home Address:\n" + homeAddress + "\n";
		result += "School Address:\n" + schoolAddress + "\n";
		result += "Average Score: \n" + average() + "\n";
		result += "Scores: "+ getTestScore(1) + ", "+ getTestScore(2) + ", and " + getTestScore(3);
		return result;
	}
}