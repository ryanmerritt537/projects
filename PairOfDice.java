package ch4Projects;

public class PairOfDice {
	private Die d1;
	private Die d2;

	public PairOfDice() {
		d1 = new Die();
		d2 = new Die();
	}

	public PairOfDice(int s1, int s2) {
		d1 = new Die(s1);
		d2 = new Die(s2);
	}

	public void roll() {
		d1.roll();
		d2.roll();
	}
	
	public int getD1Value(){
		return d1.getFaceValue();
	}
	
	public int getD2Value(){
		return d2.getFaceValue();
	}
	
	public boolean isHalfSnakeEyes() {
		return (d1.getFaceValue() == 1 
				&& d2.getFaceValue() != 1) 
				|| (d1.getFaceValue() != 1 
				&& d2.getFaceValue() == 1);
	}
	
	public boolean isSnakeEyes() {
		return (d1.getFaceValue() == 1 
				&& d2.getFaceValue() == 1);
	}
}
