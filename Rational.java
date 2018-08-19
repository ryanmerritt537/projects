package ch4Projects;

public class Rational {
	private int p;
	private int q;
	boolean defined;

	public Rational() {
		p = 1;
		q = 1;
		defined = true;
	}

	public Rational(int a, int b) {
		if(b<0){
			a=a*(-1);
			b=b*(-1);
		}
		
		if (b == 0) {
			defined = false;
			p = a;
			q = b;
		} else {
			defined = true;
			p = a;
			q = b;
			reduce();
		}
	}

	public Rational(double value) {
		boolean tic = false;
		if(value<0){
			value = Math.abs(value);
			tic = true;
		}
		int denomenator = deciToFracHelper(value);
		p = (int) (value * denomenator);
		q = denomenator;
		reduce();
		
		if (tic)
			p=p*(-1);
	}

	private int deciToFracHelper(double val){
		defined = true;
		int j = 1;
		do {
			j = j * 10;
		} while ((val * j) % 10 != 0);
		j = j / 10;
		return j;
	}
	
	public Rational(double n, double d) {
		if(d<0){
			n=n*(-1);
			d=d*(-1);
		}
		int denN = deciToFracHelper(n);
		int denD = deciToFracHelper(d);
		int newN = (int)(n*denN * denD);
		int newD = (int)(denN*d*denD);
				
		if (d == 0) {
			defined = false;
			p = newN;
			q = newD;
		} else {
			defined = true;
			p = newN;
			q = newD;
			reduce();
		}
	}
	
	public int getP() {
		return p;
	}

	public int getQ() {
		return q;
	}

	public void setP(int np) {
		p = np;
	}

	public void setQ(int nq) {
		q = nq;
	}

	private int gcf(int nn, int dd) {
		for (int i = Math.min(nn, dd); i > 0; i--)
			if (nn % i == 0 && dd % i == 0)
				return i;
		return 1;
	}

	public Rational add(Rational r) {
		if (defined) {
			int n = this.getP() * r.getQ() + this.getQ() * r.getP();
			int d = this.getQ() * r.getQ();
			reduce();
			return new Rational(n, d);
		} else
			return null;
	}

	public Rational subtract(Rational r) {
		if (defined) {
			int n = this.getP() * r.getQ() - this.getQ() * r.getP();
			int d = this.getQ() * r.getQ();
			reduce();
			return new Rational(n, d);
		} else
			return null;
	}

	public Rational multiply(Rational r) {
		if (this.isDefined() && r.isDefined()) {
			int n = this.getP() * r.getP();
			int d = this.getQ() * r.getQ();
			reduce();
			return new Rational(n, d);
		} else if(this.isDefined()&&!r.isDefined()){
			return new Rational(this.getP(),this.getQ());
		}
		return new Rational(r.getP(),r.getQ());
	}

	public Rational divide(Rational r) {
		if (defined) {
			int n = this.getP() * r.getQ();
			int d = this.getQ() * r.getP();
			reduce();
			return new Rational(n, d);
		} else
			return null;
	}

	public void reduce() {
		int g = gcf(this.getP(), this.getQ());
		this.setP(this.getP() / g);
		this.setQ(this.getQ() / g);
	}

	public boolean isDefined() {
		return defined;
	}

	public boolean equals(Rational r) {
		return (p == r.getP()) && (q == r.getQ());
	}

	public double decimalValue() {
		return 1.0 * p / q;
	}

	public String toString() {
		if (isDefined())
			return p + "/" + q;
		else
			return "not defined";
	}
}
