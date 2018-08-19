public class LuckySevens {
	public static void main(String[] args){
		int numSevens = 0;
		System.out.println("Enter the lower bound");
		int lBound = IO.readInt();
		System.out.println("Enter the upper bound");
		int hBound = IO.readInt();
		if(lBound<=hBound){
			for(int i = lBound; i <= hBound; i ++){
				String temp = "" + i;
				for(int j = 0; j < temp.length(); j++){
					if(temp.charAt(j) == '7'){
						numSevens ++;
					}
				}
			}
			IO.outputIntAnswer(numSevens);
		}
		else{
			IO.outputIntAnswer(-1);
		}
	}
}