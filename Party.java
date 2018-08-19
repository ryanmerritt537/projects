
import java.text.DecimalFormat;
public class Party {
	public static void main(String[] args){
		DecimalFormat format = new DecimalFormat(".00");
		System.out.println("How many people are at the party?");
		int people = IO.readInt();
		System.out.println("How many slices can each person eat?");
		int slicesPerPerson = IO.readInt();
		System.out.println("How many cans of soda can each person eat?");
		int cansPerPerson = IO.readInt();
		System.out.println("What is the cost of each pizza?");
		double pizzaCost = IO.readDouble();
		System.out.println("How many slices are in each pizza?");
		int  slicesInPie = IO.readInt();
		System.out.println("What is the cost of a case of soda?");
		double sodaCost = IO.readDouble();
		System.out.println("How many sodas are in each case?");
		int sodasInCase = IO.readInt();
		
		//calculate result
		int pizzas = (int)Math.ceil (((people * slicesPerPerson) / slicesInPie));
		int cases = (int) Math.ceil(((people * cansPerPerson) / sodasInCase));
		double totalCost = (sodaCost * (cases+1.0)) + (pizzaCost * (pizzas+1.0));
		IO.outputDoubleAnswer(Double.parseDouble(format.format(totalCost)));
	}
}
