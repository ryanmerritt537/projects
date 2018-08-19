package moreCh7Projects;

public class Novel extends Reading {
	int numCharacters;
	String name;
	public Novel(int n, String au, int c, String na){
		super("novel", n,au);
		numCharacters = c;
		name = na;
	}
	public String toString(){
		return super.toString()+ "\ncharacters: "+ numCharacters + "\nname: "+name;
	}
}
