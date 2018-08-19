package summerProject;

public class SumOfTerms {
	public static void main(String[] args) {
		//calculates the sum of terms in a series
		double sum = 0.0;
		double n = 0;
		for (int i = 1; i <= 100; i++) {
			n = i;
			sum += 1 / (n * (n + 1));
		}
		System.out.println(sum);
	}
}
