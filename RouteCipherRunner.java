package ch6projects;

public class RouteCipherRunner {
	public static void main(String[] args) {
		RouteCipher r = new RouteCipher(4,4);
		System.out.println(r.decryptMessage("Ithc eilh saat ssuhistis  s bt eeydlnp .ctl.reo.",4,4));
	}
}
