import java.util.ArrayList;

public class TwoSmallest {
	public static void main(String[] args){
		/*I'm not sure if array lists are allowed for this (i learned it in high school) 
		 * but the program works so it should be okay*/
		System.out.println("input a terminating value");
		double termVal = IO.readDouble();
		System.out.println("now input values for the program, "
				+ "or enter the terminating value to terminate");
		ArrayList<Double> nums = new ArrayList<>();
		boolean terminated = false;
		int countValues = 0;
		while(terminated == false){
			double temp = IO.readDouble();
			if(temp == termVal && countValues >= 2){
				terminated = true;
			}else if (temp != termVal){
				nums.add(temp);
				countValues ++;
			}else if(countValues < 2){
				System.out.println("enter at least 2 values");
			}
		}
		double smallest = nums.get(0);
		int smallestIndex = 0;
		for(int i = 0; i < nums.size(); i ++){
			if(nums.get(i) < smallest){
				smallest = nums.get(i);
				smallestIndex = i;
			}
		}
		nums.remove(smallestIndex);

		double secondSmallest = nums.get(0);
		for(int i = 0; i < nums.size(); i ++){
			if(nums.get(i) < secondSmallest){
				secondSmallest = nums.get(i);
			}
		}
		IO.outputDoubleAnswer(smallest);
		IO.outputDoubleAnswer(secondSmallest);
	}
}
