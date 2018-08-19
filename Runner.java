package moreCh7Projects;

public class Runner {
	public static void main(String[] args){
		Reading a = new Magazine(123, "Multiple authors", 12, "technology", "Forbes Magazine");
		Reading b = new Novel(198,"George Orwell", 3, "1984");
		Reading c = new Textbook(1134, "Harcourt", "trig");
		System.out.println(a+"\n\n"+b+"\n\n"+c);
	}
}
