package ch4Projects;

public class DiceProbablilty {
	public static int findCount(int times, int condition1, int condition2) {
		Die d1 = new Die();
		Die d2 = new Die();
		Die d3 = new Die();
		Die d4 = new Die();
		Die d5 = new Die();
		int count = 0;
		for (int i = 0; i < times; i++) {
			d1.roll();
			d2.roll();
			d3.roll();
			d4.roll();
			d5.roll();
			int sum = d1.getFaceValue() + d2.getFaceValue() 
				+ d3.getFaceValue() + d4.getFaceValue() 
				+ d5.getFaceValue();
			if (sum > condition1 - 1 && sum < condition2 + 1) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println("sum of 15, 100 times: " 
				+ findCount(100, 15, 15));
		System.out.println("sum of 15, 1000 times: " 
				+ findCount(1000, 15, 15));
		System.out.println("sum of 15, 10000 times: " 
				+ findCount(10000, 15, 15));
		System.out.println("sum of 15, 100000 times: " 
				+ findCount(100000, 15, 15));
		System.out.println("sum between 5-10, 100 times: " 
				+ findCount(100, 5, 10));
		System.out.println("sum between 5-10, 1000 times: " 
				+ findCount(1000, 5, 10));
		System.out.println("sum between 5-10, 10000 times: " 
				+ findCount(10000, 5, 10));
		System.out.println("sum between 5-10, 100000 times: " 
				+ findCount(100000, 5, 10));
		System.out.println("sum of 20, 100 times: " 
				+ findCount(100, 20, 20));
		System.out.println("sum of 20, 1000 times: " 
				+ findCount(1000, 20, 20));
		System.out.println("sum of 20, 10000 times: " 
				+ findCount(10000, 20, 20));
		System.out.println("sum of 20, 100000 times: " 
				+ findCount(100000, 20, 20));
		System.out.println("sum between 15-25, 100 times: " 
				+ findCount(100, 15, 25));
		System.out.println("sum between 15-25, 1000 times: " 
				+ findCount(1000, 15, 25));
		System.out.println("sum between 15-25, 10000 times: " 
				+ findCount(10000, 15, 25));
		System.out.println("sum between 15-25, 100000 times: " 
				+ findCount(100000, 15, 25));
	}

}
