package summerProject;

public class VariableTotal {

	public static void main(String[] args) {
		// determines the variable total after each of the following loops is
		// executed
		int total = 1;
		for (int icnt = 1; icnt <= 8; ++icnt) {
			total = total * icnt;
		}
		System.out.println(total);
	}

}
/**
 * Answers -- a: 10, b: 1024, c: 75, d: 105, e: 40320.
 */
