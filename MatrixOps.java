public class MatrixOps
{
	public static double[][] multiply(double[][] matrix1, double[][] matrix2)
	{
		int col1 = matrix1.length;
		int col2 = matrix2.length;
		int row1 = matrix1[0].length;
		int row2 = matrix2[0].length;
		if(col2 != row1){
			return null;
		}
		double[][] answer = new double[col1][row2];
		for(int i = 0; i < col1; i++){
			for(int j = 0; j < row2; j++){
				for(int k = 0; k < row1; k++){
					answer[i][j]+=matrix1[i][k]*matrix2[k][j];
				}
			}
		}
		return answer;
	}
}