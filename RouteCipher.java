package ch6projects;

public class RouteCipher {

	/**
	 * A two-dimensional array of single-character strings, instantiated in the
	 * constructor
	 */
	private String[][] letterBlock;

	/** The number of rows of letterBlock, set by the constructor */
	private int numRows;

	/** The number of columns of letterBlock, set by the constructor */
	private int numCols;

	// Intializes numRows and numCols data metembers.
	public RouteCipher(int r, int c) {
		numRows = r;
		numCols = c;
		letterBlock = new String[numRows][numCols];
	}

	/**
	 * Places a string into letterBlock in row-major order.
	 * 
	 * @param str
	 *            the string to be processed Postcondition: if str.length() <
	 *            numRows * numCols, "A" is placed in each unfilled cell if
	 *            str.length() > numRows * numCols, trailing characters are
	 *            ignored
	 */
	private void fillBlock(String str) { /* to be implemented in part (a) */
		for (int i = 0; i < letterBlock.length; i++) {
			for (int j = 0; j < letterBlock[i].length; j++) {
				if ((i * letterBlock.length) + j < str.length()) {
					letterBlock[i][j] = str.charAt((i * letterBlock[i].length) + j)+"";
				} else {
					letterBlock[i][j] = "A";
				}
			}
		}
	}

	/**
	 * Extracts encrypted string from letterBlock in column-major order.
	 * Precondition: letterBlock has been filled
	 * 
	 * @return the encrypted string from letterBlock
	 */
	public String encryptBlock() { /* implementation not shown */
		String str = "";
		for (int i = 0; i < letterBlock.length; i++) {
			for (int j = 0; j < letterBlock[i].length; j++) {
					str += letterBlock[j][i];
			}
		}
		return str; // change this return statement
	}

	/**
	 * Encrypts a message.
	 * 
	 * @param message
	 *            the string to be encrypted
	 * @return the encrypted message; if message is the empty string, returns
	 *         the empty string
	 */
	public String encryptMessage(String message) {
		/* to be implemented in part (b) */
		String str = "";
		fillBlock(message);
		while (message.length() > 0&&message.length()>=encryptBlock().length()) {
			str += encryptBlock();
			message = message.substring(encryptBlock().length() - 1);
			fillBlock(message);
		}
		return str; // change this
	}

	// This method accepts String to be decrypted, number of row and
	// and number of columns for the letterBlock.
	public String decryptMessage(String message, int nRow, int nCol) {
		String[][] n  = new String[nRow][nCol];
		String str = "";
		for(int i = 0; i < n.length; i++){
			for(int j = 0; j < n[i].length; j++){
				n[j][i]=message.charAt((i*nCol)+j)+"";
				str+=n[i][j];
			}
		}
		return str; // change this
	}

	/* returns the letter block in row X column format as a string */
	public String toString() {
		String str = "";
		for(int i = 0; i < letterBlock.length; i++){
			for(int j = 0; j < letterBlock[i].length; j++){
				str+=letterBlock[i][j];
			}
		}
		return str; // change this
	}

	public static void main(String[] args) {
		
	}
}
