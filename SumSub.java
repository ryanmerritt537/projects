package ch6projects;

public class SumSub {
	public static int[][] createSubArray(int[] loc, int s, int[][] n) {
		int[][] subArray = new int[s][s];
		for (int i = loc[1]; i < loc[1] + s; i++) {
			for (int j = loc[0]; j < loc[0] + s; j++) {
				subArray[i - loc[1]][j - loc[0]] = n[i][j];
			}
		}
		return subArray;
	}

	public static int[][] sumSubMax(int[][] n, int s) {
		int sums = 0;
		int[][] biggestSumArray = new int[s][s];
		int currentBiggestSum = 0;
		int[] loc;
		for (int i = 0; i <= n.length - s; i++) {
			for (int j = 0; j <= n.length - s; j++) {
				loc = new int[] { j, i };
				// calculating the sum of the current subarray
				// also finds the sum of the current largest sum array
				for (int i1 = 0; i1 < s; i1++) {
					for (int j1 = 0; j1 < s; j1++) {
						sums += createSubArray(loc, s, n)[i1][j1];
						currentBiggestSum += biggestSumArray[i1][j1];
					}
				}
				if (sums>currentBiggestSum) {
					biggestSumArray = createSubArray(loc, s, n);
				}
				sums = 0;
				currentBiggestSum = 0;
			}
		}

		return biggestSumArray;
	}

}
