package ch4Projects;

public class BoxCars {
	public static void main(String[] args){
		Die d1 = new Die();
		Die d2 = new Die();
		int count = 0;
		for(int i = 0; i < 1000; i++){
			d1.roll();
			d2.roll();
			if(d1.getFaceValue() == 6&&d2.getFaceValue() == 6){
				count ++;
			}
		}
		System.out.println(count);
	}
}
/*the theoretical probablility is 27 out of 1000. 
 * I got numbers ranging from 24 to 34,
 * which are around 27.*/