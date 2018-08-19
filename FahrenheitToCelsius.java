package summerProject;

public class FahrenheitToCelsius {
	public static void main(String[] args) {
		//converts a set of Fahrenheit temperatures to celsius
		double f = 20.0;
		double c = 0.0;
		for (int i = 0; i < 20; i += 1) {
			c = (f - 32) * (5.0 / 9.0);
			System.out.println("Fahrenheit: " + f + ", Celsius: " + c);
			f += 3;
		}
	}
}
