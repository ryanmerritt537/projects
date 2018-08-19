package moreCh7Projects;

public class Magazine extends Reading{
	int numArticles;
	String subject;
	String name;
	public Magazine(int n, String au, int a, String s, String na){
		super("magazine", n,au);
		numArticles = a;
		subject = s;
		name = na;
	}
	public String toString(){
		return super.toString()+ "\narticles: "+ numArticles + "\nsubject: "+subject + "\nname: "+name;
	}
}
