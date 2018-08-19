package ch4Projects;

public class Line {
	private Rational b = new Rational();
	private Rational m = new Rational();
	private Rational xIntercept = new Rational();
	private boolean vertical;
	private Rational verticalSlope = new Rational();
	private boolean pointsSame;

	// default constructor
	public Line() {
		m = new Rational(1.0);
		b = new Rational(0.0);
		vertical = false;
	}
	
	// point point form
	public Line(Point A, Point B) {
		if (!A.equals(B)) {
			if (A.getX() != B.getX()) {
				m = new Rational((B.getY() - A.getY()) / (B.getX() - A.getX()));
				b = new Rational(A.getY() - (m.decimalValue() * (A.getX())));
				vertical = false;
			} else {
				verticalSlope = new Rational(A.getX());
				vertical = true;
			}
		} else {
			pointsSame = true;
			System.out.println("Points are the same!");
		}
	}	
	
	
	// standard form
	public Line(int a, int bb, int c) {
		if (bb != 0) {
			m = new Rational(-1 * c, bb);
			b = new Rational(a, bb);
			m.reduce();
			b.reduce();
			vertical = false;
		} else {
			verticalSlope = new Rational();
			verticalSlope.setP(c);
			verticalSlope.setQ(a);
			vertical = true;
		}
	}

	// returns whether points are the same
	// this is only for point point form
	// I needed this method to fix a problem
	public boolean getPointsEqual() {
		return pointsSame;
	}

	// point slope form
	public Line(Point P, double slope) {
		m = new Rational(slope);
		b = new Rational(P.getY() - (m.decimalValue() * P.getX()));
		vertical = false;
	}

	// slope intercept form
	public Line(double slope, double yInt) {
		Rational ZERO = new Rational(0.0);
		m = new Rational(slope);
		b = new Rational(yInt);
		if (m.equals(ZERO)) {
			vertical = true;
		} else {
			vertical = false;
		}
	}
	
	//isConsistent
	public boolean isConsistent(Line l){
		return m!=l.getSlope();
	}
	
	// .equals
	public boolean equals(Line l) {
		return Math.abs(m.decimalValue()-l.getSlope().decimalValue())<1 && 
				Math.abs(b.decimalValue()-l.getYIntercept().decimalValue())<1;
	}

	// get slope
	public Rational getSlope() {
		return new Rational(m.getP(), m.getQ());
	}

	// get x intercept
	public Rational getXIntercept() {
		Rational NEGONE = new Rational(-1);
		Rational f = new Rational(((b.multiply(NEGONE)).divide(m)).getP(), ((b.multiply(NEGONE)).divide(m)).getQ());
		return new Rational();
	}

	// get y intercept
	public Rational getYIntercept() {
		return new Rational(b.getP(), b.getQ());
	}

	// get intersection point
	public Point intersection(Line L) {
		Rational u = new Rational(m.divide(L.getSlope()).getP(), m.divide(L.getSlope()).getQ());
		Rational ONE = new Rational(1.0);
		Rational y = new Rational();
		y.setP((L.getYIntercept().multiply(u.subtract(b)).divide(u.subtract(ONE))).getP());
		y.setQ((L.getYIntercept().multiply(u.subtract(b)).divide(u.subtract(ONE))).getQ());
		if (isVertical() && !L.isVertical()) {
			return new Point(verticalSlope, ((L.getSlope().multiply(verticalSlope)).add(L.getYIntercept())));
		}
		else if (!isVertical() && L.isVertical()) {
			return new Point(L.getVerticalSlope(), (m.multiply(L.getVerticalSlope())).add(b));
		} 
		else if (isHorizontal() == true && !L.isVertical()) {
			
			return new Point(((L.getYIntercept().subtract(b)).divide(m.subtract(L.getSlope()))), b);
		}
		else if (L.isHorizontal() == true && !isVertical()) {
			
			return new Point(((b.subtract(L.getYIntercept())).divide((L.getSlope().subtract(m)))), L.getYIntercept());
		}
		else if (L.isHorizontal() == true && isVertical()) {
			
			return new Point(verticalSlope, L.getYIntercept());
		}
		else if (isHorizontal() == true && L.isVertical()) {
			
			return new Point(L.getVerticalSlope(), b);
		}
		return new Point((((L.getYIntercept().subtract(b)).divide(m.subtract(L.getSlope())))), y);
	}

	// return true if line is vertical
	public boolean isVertical() {
		return vertical;
	}

	// return true if line is horizontal
	public boolean isHorizontal() {
		return m.getP()==0&&!isVertical();
	}

	// return whether lines are parallel
	public boolean IsParallel(Line L) {
		Rational f = new Rational();
		if (isVertical()&&L.isVertical()) {
			return true;
		}
		if(isVertical()&&!L.isVertical()){
			return false;
		}
		if(L.isVertical()&&!isVertical()){
			return false;
		}
		return m.equals(L.getSlope());
	}

	// sets x intercept
	public void setXIntercept() {
		Rational NEGONE = new Rational(-1);
		if (!m.equals(0)) {
			xIntercept = new Rational(b.multiply(NEGONE).divide(m).decimalValue());
		} else if (isVertical()) {
			xIntercept = new Rational(verticalSlope.decimalValue());
		} else {
			System.out.println("Line is horizontal");
		}
	}

	// Vertical slope is for standard form
	// equations. It is equal to C/A. I needed
	// this to solve a problem that I was getting.
	public Rational getVerticalSlope() {
		Rational v = new Rational();
		v.setP(verticalSlope.getP());
		v.setQ(verticalSlope.getQ());
		return v;
	}

	// return to string
	public String toString() {
		if (isVertical()) {
			return "x = " + verticalSlope.toString();
		}
		return "y = " + m.toString() + "x + " + b.toString();
	}
	
	public Line perpLine(Point P){
		double recip = m.getQ()/m.getP()*-1;
		return new Line(P, recip);
	}
	
	public boolean isPerpendicular(Line l){
		Rational recip = new Rational(m.getQ(),m.getP());
		recip.multiply(new Rational(-1.0));
		return Math.abs(recip.decimalValue()-l.getSlope().decimalValue())<=1;
	}
	
	public Point intersect(Line L) {
		Rational u = new Rational(m.divide(L.getSlope()).getP(), m.divide(L.getSlope()).getQ());
		Rational ONE = new Rational(1.0);
		Rational y = new Rational();
		y.setP((L.getYIntercept().multiply(u.subtract(b)).divide(u.subtract(ONE))).getP());
		y.setQ((L.getYIntercept().multiply(u.subtract(b)).divide(u.subtract(ONE))).getQ());
		
		return new Point((((L.getYIntercept().subtract(b)).divide(m.subtract(L.getSlope())))), y);
	}
}
