package ch6projects;

import ch5Projects.s;

public class SelectionSort {
	// main method
	public static void main(String[] args) {
		int[] a = new int[15];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 100) + 1;
		}
		display(a);
		s.p("\nAfter sorting");
		sort(a);
		display(a);
	}

	// display
	public static void display(int[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print("a[" + i + "] : " + n[i] + "\t");
			if((i+1) % 3 == 0)
				s.p("");
		}
	}

	// swap method
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

	// finds minimum index
	public static int findMinIndex(int[] n, int startIndex) {
		int minIndex = startIndex;
		int min = n[minIndex];
		int index = 0;
		for (int i = startIndex; i < n.length; i++) {
			min = Math.min(min, n[i]);
			if (n[i] == min)
				index = i;
		}
		return index;
	}

	// sorts in descending order
	public static void sort(int[] n) {
		for (int i = 0; i < n.length - 1; i++) {
			int cMinIndex = findMinIndex(n, i);
			swap(n, i, cMinIndex);
		}
	}
}
