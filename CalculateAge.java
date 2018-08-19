package summerProject;

public class CalculateAge {
	public static void main(String[] args) {
		//calculates a person's age after a birthday gift exceeds $1000
		int money = 10;
		int age = 12;
		int prevMoney = 10;
		for (int i = 1; money < 1000; i += 1) {
			money += (prevMoney * 2);
			age += i/i;
			prevMoney *= 2;
		}
		System.out.println(age);
	}
}
