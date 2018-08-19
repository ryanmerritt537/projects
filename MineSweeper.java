package ch6projects;

public class MineSweeper {
	int[][] board;

	public MineSweeper() {
		board = new int[12][12];
		initBoard();
	}

	public void initBoard() {
		double rnd = Math.random();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (rnd < 0.25) {
					board[i][j] = 1;
				} else {
					board[i][j] = 0;
				}
				rnd = Math.random();
			}
		}
	}

	public int[][] paths(int[][] n) {
		int[][] x = new int[n.length][n.length];
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length; j++) {
				if (n[i][j] == 0)
					x[i][j]++;
				if (i > 0 && n[i - 1][j] == 0)
					x[i][j]++;
				if (j > 0 && n[i][j - 1] == 0)
					x[i][j]++;
				if (i < n.length - 1 && n[i + 1][j] == 0)
					x[i][j]++;
				if (j < n.length - 1 && n[i][j + 1] == 0)
					x[i][j]++;
				if (i > 0 && j > 0 && n[i - 1][j - 1] == 0)
					x[i][j]++;
				if (i > 0 && j < n.length - 1 && n[i - 1][j + 1] == 0)
					x[i][j]++;
				if (j > 0 && i < n.length - 1 && n[i + 1][j - 1] == 0)
					x[i][j]++;
				if (i < n.length - 1 && j < n.length - 1 && n[i + 1][j + 1] == 0)
					x[i][j]++;
			}
		}
		return x;
	}

	public int[][] mines(int[][] n) {
		int[][] x = new int[n.length][n.length];
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length; j++) {
				if (n[i][j] == 1)
					x[i][j]++;
				if (i > 0 && n[i - 1][j] == 1)
					x[i][j]++;
				if (j > 0 && n[i][j - 1] == 1)
					x[i][j]++;
				if (i < n.length - 1 && n[i + 1][j] == 1)
					x[i][j]++;
				if (j < n.length - 1 && n[i][j + 1] == 1)
					x[i][j]++;
				if (i > 0 && j > 0 && n[i - 1][j - 1] == 1)
					x[i][j]++;
				if (i > 0 && j < n.length - 1 && n[i - 1][j + 1] == 1)
					x[i][j]++;
				if (j > 0 && i < n.length - 1 && n[i + 1][j - 1] == 1)
					x[i][j]++;
				if (i < n.length - 1 && j < n.length - 1 && n[i + 1][j + 1] == 1)
					x[i][j]++;
			}
		}
		return x;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				str += board[i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}

	public String displayMines() {
		int[][] m = new int[board.length][board.length];
		m = mines(board);
		String str = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				str += m[i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}

	public String displayPaths() {
		int[][] m = new int[board.length][board.length];
		m = paths(board);
		String str = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				str += m[i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}
}
