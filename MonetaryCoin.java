package ch5Projects;

public class MonetaryCoin extends Coin{
	private double value;
	
	public MonetaryCoin(double v){
		super();
		value = v;
	}
	
	public double getValue(){
		return value;
	}
	
	public String toString(){
		return super.toString() + "\nValue: "+value;
	}
}
