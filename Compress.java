
public class Compress {
	public static String compress(String original){
		String newString = "";
		int count = 1;
		if(original.length() <= 1){
			return original;
		}
		for(int i = 0; i < original.length(); i++){
			if(i < original.length()-1 && original.charAt(i) == original.charAt(i+1)){
				int j = i;
				while(j<original.length() && original.charAt(i) == original.charAt(j)){
					count ++;
					j++;
				}
				i=j-1;
				count-=1;
				newString+=count;
				newString+=original.charAt(i);
			}else{
				newString+=original.charAt(i);
			}
			count = 1;
		}
		
		return newString;
	}
	public static void main(String args[]){
		String x = IO.readString();
		System.out.println(compress(x));
	}	
}
