public class WordCount {
	public static int countWords (String original, int maxLength){
		int count = 0;
		boolean word = false;
		original = original + ' ';
		original = original.toUpperCase();
		int letterCount = 0;
		for(int i = 0; i < original.length(); i++){
			if(Character.isLetter(original.charAt(i)) && word == false){
				word = true;
			}
			if(word == true && !Character.isLetter(original.charAt(i))){
				count++;
				word = false;
				if(letterCount > maxLength){
					count--;
				}
				letterCount =0;
			}
			if(word == true && Character.isLetter(original.charAt(i))){
				letterCount ++;
			}
		}
		return count;
	}
	public static void main(String[] args){
		
		System.out.println(countWords("a b ddfdAc  d e f g",4));
	}
}