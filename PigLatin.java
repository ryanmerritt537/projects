
public class PigLatin {
	public static String translate(String original){
		String newString = "";
		String o = original.toUpperCase();
		if(o.charAt(0) == 'A' || o.charAt(0) == 'E' ||
				o.charAt(0) == 'I' || o.charAt(0) == 'O' ||
				o.charAt(0) == 'U'){
			newString+=o + "vai";
		}else{
			if(o.length() > 1){
				newString += o.substring(1) + o.charAt(0) + "ai";
			}else if(o.length() == 1){
				newString = o + "ai";
			}else{
				newString = "ai";
			}
		}
		
		return newString;
	}
}
