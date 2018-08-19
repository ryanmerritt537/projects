package ch4Projects;

public class Die {
	private int numSides;
	private int faceValue;

	public Die() {
		numSides = 6;
	}

	public void roll() {
		faceValue = (int) (Math.random() * numSides) + 1;
	}

	public Die(int s) {
		numSides = s;
	}
	public int getFaceValue(){
		return this.faceValue;
	}
}
