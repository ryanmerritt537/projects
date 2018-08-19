package ch6projects;

public class Hw2 {
	public static void main(String[] args){
		int[] a1 = new int[20];
		int[] a2 = new int[a1.length];
		for(int i = 0; i < a1.length; i++){
			a1[i] = (int)(Math.random() * 100) + 1;
			a2[i] = (int)(Math.random() * 100) + 1;
		}
		for(int i = 0; i < a1.length; i++)
			s.p("array 1 [" + i + "] " + a1[i] 
					+ "		array 2 [" + i + "] " + a2[i]);
		int sames = 0;
		for(int i = 0; i < a1.length; i++)
			if(a1[i] == a2[i])
				sames++;
		s.p("# of same values = " + sames);
	} 
}
