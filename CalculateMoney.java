package summerProject;

public class CalculateMoney {
	public static void main(String[] args) {
		//calculates the amount of money in a bank account
		double cash = 1000;
		for (int i = 0; i < 10; i++) {
			cash *= 1.08;
		}
		System.out.println("ending cash is " + cash);
	}
}
