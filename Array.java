package ch5Projects;

public class Array {
	public static void main(String[] args) {
		// for question #1
		int[] n = new int[10];

		// for question #2
		for (int i = 0; i < n.length; i++) {
			n[i] = (int) (Math.random() * 100) + 1;
		}

		// outputs question 3
		output(n);
		
		// outputs question 4
		s.p("average: " + average(n));
		
		// outputs question 5
		s.p("Min: " + min(n));
		
		// outputs question 6
		s.p("Max: " + max(n));
		
		// outputs question 7
		swap(n, 2, 4);
		output(n);
		
		// outputs question 8
		maxFirstMinLast(n);
		output(n);
		
		// outputs question 9
		s.p("evens: " + countEvens(n));
		
		// outputs question 10
		s.p("odds: " + countOdds(n));
	}

	// for question #3
	public static void output(int[] n) {
		s.p("output:");
		for (int i = 0; i < n.length; i++) {
			s.p("array num[" + i + "] : " + n[i]);
		}
	}

	// for question #4
	public static int average(int[] n) {
		int sum = 0;
		for (int i = 0; i < n.length; i++) {
			sum += n[i];
		}
		return sum / n.length;
	}

	// for question #5
	public static int min(int[] n) {
		int currentMin = 100;
		for (int i = 0; i < n.length; i++) {
			currentMin = Math.min(currentMin, n[i]);
		}
		return currentMin;
	}

	// for question #6
	public static int max(int[] n) {
		int currentMax = 0;
		for (int i = 0; i < n.length; i++) {
			currentMax = Math.max(currentMax, n[i]);
		}
		return currentMax;
	}

	// for question #7
	public static void swap(int[] n, int first, int second) {
		int temp = 0;
		if (second < n.length && first < n.length) {
			temp = n[first];
			n[first] = n[second];
			n[second] = temp;
		} else {
			s.p("array's length is smaller than one of the values entered");
		}
	}

	// method finds the index at a certain number
	public static int indexAt(int[] n, int num) {
		int x = 0;
		int i = 0;
		while (n[i] != num) {
			x += 1;
			i += 1;
		}
		return x;
	}

	// for question #8
	public static void maxFirstMinLast(int[] n) {
		swap(n, indexAt(n, max(n)), 0);
		swap(n, indexAt(n, min(n)), n.length - 1);
	}

	// for question #9
	public static int countEvens(int[] n) {
		int x = 0;
		for (int i = 0; i < n.length; i++)
			if (n[i] % 2 == 0)
				x++;
		return x;
	}

	// for question #10
	public static int countOdds(int[] n) {
		int x = 0;
		for (int i = 0; i < n.length; i++)
			if (n[i] % 2 != 0)
				x++;
		return x;
	}
}
