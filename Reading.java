package moreCh7Projects;

public class Reading {
	int numPages;
	String author;
	String type;
	public Reading(String t, int n, String a){
		type = t;
		numPages = n;
		author = a;
	}
	public String toString(){
		return "Type of reading: "+ type + "\nNumber of Pages: "+numPages + "\nAuthor: "+author;
	}
}
