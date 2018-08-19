package ch5Projects;

public class CoinRunner {
	public static void main(String[] args){
		Coin c = new MonetaryCoin(2.56);
		System.out.println(c);
		c.setKey(34);
		c.unlock(34);
		c.flip();
		System.out.println("\nAfter Flipping:\n"+c);
	}
}