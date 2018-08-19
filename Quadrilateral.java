package ch4Projects;

import java.text.DecimalFormat;

/*STUDENT VERSION DO NOT EDIT*/

public class Quadrilateral {
	/*
	 * Points are created in clockwise order such that: A is top left B is top
	 * right C is bottom right D is bottom left
	 */
	private Point A;
	private Point B;
	private Point C;
	private Point D ;
	private String type = "Quadrilateral";

	// default constructor
	public Quadrilateral() {
		genA();
		genB();
		genC();
		genD();
		type = classify();
		// assign value to type using classify method
	}

	// overloaded constructor
	/*
	 * Creates a quadrilateral based upon the String type passed as a parameter.
	 * type: Parallelogram, Rhombus, Rectangle, Square and Trapezoid EXTRA
	 * CREDIT: kite Use classify method to initialize type.
	 */
	public Quadrilateral(String str) {
		type = str;
//		if (type == "Kite") {
//			genA();
//			genB();
//			genC();
//			Line AC = new Line(A, C);
//			Line BD = new Line(B, (-1.0*AC.getSlope().getQ())/AC.getSlope().getP());
//			Point M = new Point(AC.intersection(BD).getRX(), AC.intersection(BD).getRY());
//			double xAdd =B.getX()-M.getX();
//			double yAdd =M.getY()-B.getY();
//			D = new Point(M.getX() - xAdd, M.getY() + yAdd);
//		} else 
			if (type == "Square") {
			genA();
			genB();
			double xAdd = B.getX() - A.getX();
			double yAdd = B.getY() - A.getY();
			C = new Point(B.getX() - yAdd, B.getY() + xAdd);
			D = new Point(A.getX() - yAdd, A.getY() + xAdd);
		} else if (type == "Rhombus") {
			genA();
			genB();
			double xAdd = B.getX() - A.getX();
			double yAdd = B.getY() - A.getY();
			C = new Point(B.getX() + yAdd, B.getY() + xAdd);
			D = new Point(A.getX() + yAdd, A.getY() + xAdd);
		} else if (type == "Rectangle") {
			genA();
			genB();
			Line AB = new Line(getA(), getB());
			Line AD = new Line();
			if(AB.getSlope().getP()!=0){
				AD = new Line(A,(-1.0*(AB.getSlope()).getQ())/(double)((AB.getSlope()).getP()));
			}else{
				AD = new Line(new Point(A.getX(),A.getY()),new Point(A.getX(),A.getY()-1));
			}
			int dY = (int) (Math.random() * 10) * 10 + 110;
			Line d = new Line(0.0, 1.0*dY);
			Point p =new Point(intersect(d,AD).getX(), intersect(d,AD).getY());
			D = new Point(p.getX(),dY);
			double xAdd;
			double yAdd;
			if(B.getY()<A.getY()){
				xAdd = B.getX() - A.getX();
				yAdd  = A.getY() - B.getY();
				C = new Point(D.getX() + xAdd, D.getY() - yAdd);
			}else{
				xAdd = B.getX() - A.getX();
				yAdd  = B.getY() - A.getY();
				C = new Point(D.getX() + xAdd, D.getY() + yAdd);
			}
			
		} else if (type == "Parallelogram") {
			genA();
			genB();
			genC();
			double xAdd = B.getX() - A.getX();
			double yAdd = B.getY() - A.getY();
			D = new Point(C.getX() - xAdd, C.getY() - yAdd);

		} else if (type == "Trapezoid") {
			genA();
			genB();
			genC();
			Line DC = new Line(C, ((new Line(A, B)).getSlope()).getP()/(1.0*((new Line(A, B)).getSlope()).getQ()));
			int dY =(int) (Math.random() * 10) * 10 + 110;
			while(dY<C.getY()){
				dY = (int) (Math.random() * 10) * 10 + 110;
			}
			Line l = new Line(0.0,1.0*dY);
			Point p = new Point(intersect(l,DC).getX(),intersect(l,DC).getY());
			while(p.getX()>=C.getX()&&p.getX()<=0){
				dY =(int) (Math.random() * 10) * 10 + 110;
				if(dY<C.getY()){
					dY = (int) (Math.random() * 10) * 10 + 110;
				}
				p = new Point(intersect(l,DC).getX(),intersect(l,DC).getY());
			}
			
			D = new Point(p.getX(), dY);
		}
	}
	
	//this method was made to help out with some
	//intersection stuff that wasn't working. It only
	//works if the 1st parameter is a horizontal line,
	//but that is okay because that is all I need for this code.
	public Point intersect(Line a, Line b){
		Rational ONE = new Rational(1.0);
		
		if (!a.isVertical() && b.isVertical()&&type!="rectangle"&&type != "trapezoid") {
			
			return new Point(b.getVerticalSlope(), 
					(a.getSlope().multiply(b.getVerticalSlope())).add(a.getYIntercept()));
		}
		else if (type=="Trapezoid") {
			return new Point(
					((a.getYIntercept().subtract(b.getYIntercept())).divide(b.getSlope())),
					a.getYIntercept());
		}
		else if(type == "Rectangle"){
			return new Point(
					((a.getYIntercept().subtract(b.getYIntercept())).divide(b.getSlope())),
					a.getYIntercept());
		}
		else if (b.isHorizontal() == true && !a.isVertical()&&type!="rectangle"&&type != "trapezoid") {
			
			return new Point(
					((a.getYIntercept().subtract(b.getYIntercept())).divide((b.getSlope().subtract(a.getSlope())))),
					b.getYIntercept());
		}else{
			
		}
			
		return new Point(b.getVerticalSlope(), a.getYIntercept());
		
	}

	// random point with x ->[0-100], y->[0,100], x and y are multiples of 10
	private void genA() {
		int x = (int) (Math.random() * 11) * 10;
		int y = (int) (Math.random() * 11) * 10;
		A = new Point(x, y);
	}

	// random point with x ->[100-200], y->[0,100], x and y are multiples of 10
	private void genB() {
		int x = (int) (Math.random() * 10) * 10 + 110;
		int y = (int) (Math.random() * 11) * 10;
		B = new Point(x, y);
	}

	// random point with x ->[100-200], y->[100,200], x and y are multiples of
	// 10
	private void genC() {
		int x = (int) (Math.random() * 10) * 10 + 110;
		int y = (int) (Math.random() * 10) * 10 + 110;
		C = new Point(x, y);
	}

	// random point with x ->[0-100], y->[100,200], x and y are multiples of 10
	private void genD() {
		int x = (int) (Math.random() * 11) * 10;
		int y = (int) (Math.random() * 10) * 10 + 110;
		D = new Point(x, y);
	}

	public Point getA() {
		return A;
	}

	public Point getB() {
		return B;
	}

	public Point getC() {
		return C;
	}

	public Point getD() {
		return D;
	}

	/*
	 * returns true or false based upon if quadrilateral is a parallelogram with
	 * all equal side and all equal angles.
	 */
	public boolean isSquare() {
		return (new Line(getA(), getB())).IsParallel(new Line(getC(), getD()))
				&& (new Line(getA(), getB())).isPerpendicular(new Line(getB(), getC()))
				&& Math.abs(getA().distanceTo(getB()) - getB().distanceTo(getC())) <= 0.1;
	}

	/*
	 * returns true or false based upon if quadrilateral is a parallelogram.
	 * There are many sufficient conditions to prove that shape is a
	 * parallelogram. You can decide to choose any you like.
	 */
	public boolean isParallelogram() {
		return (new Line(getA(), getB())).IsParallel(new Line(getC(), getD()))
				&& (new Line(getA(), getD())).IsParallel(new Line(getB(), getC()))
				&&!isRhombus()&&!isSquare()&&!isRectangle();
	}

	/*
	 * returns true or false based upon if quadrilateral is a quadrilaeral with
	 * all 90 degree angles
	 */
	public boolean isRectangle() {

		return (new Line(A, B)).IsParallel(new Line(C,D))
				&& (new Line(A,B)).isPerpendicular(new Line(B, C)) && !isSquare();
	}

	/*
	 * returns true or false based upon if quadrilateral is a parallelogram with
	 * all equal sides
	 */
	public boolean isRhombus() {
		return (new Line(getA(), getB())).IsParallel(new Line(getC(), getD()))
				&& Math.abs(getA().distanceTo(getB()) - getB().distanceTo(getC())) <= 1 && !isSquare();
	}

	/*
	 * returns true of false based upon if quadrilateral has congruent
	 * consecutive sides.
	 */
	
	//kite caused problems so I commented it out but I at least tried to do it.
	
//	public boolean isKite() {
//
//		return (((((Math.abs(getA().distanceTo(getB()) - getB().distanceTo(getC())) <= 1)
//				&& (Math.abs(getA().distanceTo(getD()) - getD().distanceTo(getC())) <= 1)))
//				|| (((Math.abs(getB().distanceTo(getC()) - getC().distanceTo(getD())) <= 1)
//						&& (Math.abs(getA().distanceTo(getB()) - getD().distanceTo(getA())) <= 1)))))
//
//				&&( !isSquare() && !isRhombus() && !isParallelogram() && !isTrapezoid());
//	}

	public boolean isTrapezoid() {
		return ((new Line(getA(), getB()).IsParallel(new Line(getD(), getC())))
				|| (new Line(getB(), getC()).IsParallel(new Line(getD(), getA())))) && !isRhombus() 
					&& !isParallelogram() && !isSquare() && !isRectangle();
	}

	/*
	 * returns area of the quadrilateral, in order to do this you may want to
	 * check this link: https://www.youtube.com/watch?v=JVZud7ZBEKg OR You may
	 * want to use Heron's Formula, in this case you will treat quadrilateral as
	 * two triangles and find length of the diagonal.
	 */
	public double area() {
		// I used herons formula
		double s1 = (getA().distanceTo(getB()) + getB().distanceTo(getC()) + getC().distanceTo(getA())) / 2;
		double a1 = getA().distanceTo(getB());
		double b1 = getB().distanceTo(getC());
		double c1 = getC().distanceTo(getA());
		double area1 = Math.sqrt(s1 * (s1 - a1) * (s1 - b1) * (s1 - c1));
		double s2 = (getA().distanceTo(getC()) + getC().distanceTo(getD()) + getD().distanceTo(getA())) / 2;
		double a2 = getA().distanceTo(getC());
		double b2 = getC().distanceTo(getD());
		double c2 = getD().distanceTo(getA());
		double area2 = Math.sqrt(s2 * (s2 - a2) * (s2 - b2) * (s2 - c2));
		return area1 + area2;
	}

	// returns perimeter of a quadrilateral
	public double perimeter() {

		return getA().distanceTo(getB()) + getB().distanceTo(getC()) + getC().distanceTo(getD())
				+ getD().distanceTo(getA());
	}

	// returns slope of the line formed by points L and N
	public Rational sideSlope(Point L, Point N) {
		Rational r = new Rational(new Line(L, N).getSlope().getP()
				, new Line(L, N).getSlope().getQ());
		return r;
	}

	// returns length of the line with end points in parameter
	public double sideLength(Point L, Point N) {
		double len = L.distanceTo(N);

		return len;
	}

	/*
	 * Classify quadrilateral as parallelogram, rectangle, rhombus, square or
	 * trapezoid. write code below for this method
	 */
	private String classify() {
		String result = "";
		 if (isSquare()) {
		 result = "SQUARE";
		 }
		 else if (isRectangle()) {
		 result = "RECTANGLE";
		 }
		 else if (isParallelogram()) {
		 result = "PARALLELOGRAM";
		 }
		 else if (isRhombus()) {
		 result = "RHOMBUS";
		 }
		 //kite didn't work, to keep the 
		 //program operational I had to remove this code
		// else if (isKite()) {
		// result = "KITE";
		// }
		 else if (isTrapezoid()) {
		 result = "TRAPEZOID";
		 }

		return result;
	}

	public String getType() {
		return type;
	}

	/*
	 * should return coordinates of 4 corners Area: Perimeter: Type:
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.0");
		return "A: " + A + "\t\tB: " + B + "\nC: " + C + "\tD: " + D 
				+ "\nArea: "+ df.format(this.area()) + "\nPerimeter: " + df.format(this.perimeter()) + "\n"+classify() ;
	}

}
