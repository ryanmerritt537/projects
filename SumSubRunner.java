package ch6projects;

public class SumSubRunner {
	public static void main(String[] args){
		int[][] n  = new int[12][12];
		for(int i = 0; i < 12; i++){
			for(int j = 0; j < 12; j++){
				n[i][j] = (int)(Math.random()*9)+1;
			}
		}
		
		for(int i = 0; i < 12; i++){
			for(int j = 0; j < 12; j++){
				System.out.print(n[i][j]+"\t");
			}
			s.p("");
		}
		s.p("");
		int[][] x = SumSub.sumSubMax(n,2);
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				System.out.print(x[i][j]+"\t");
			}
			s.p("");
		}
	}
}
