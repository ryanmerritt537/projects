package ch4Projects;

import java.text.DecimalFormat;

public class Point {

	static DecimalFormat fmt = new DecimalFormat("0.##");
	private double x;
	private double y;
	private Rational rx = new Rational();
	private Rational ry = new Rational();
	
	public Point(){
		x=0;
		y=0;
		
	}
	
	public Point(double a, double b){
		x=a;
		y=b;
	}
	
	public Point(Rational a, Rational b){
		x = a.decimalValue();
		y = b.decimalValue();
		rx.setP(a.getP());
		rx.setQ(a.getQ());
		ry.setP(b.getP());
		ry.setQ(b.getQ());
	}
	
	public Rational getRX(){
		return new Rational(this.rx.getP(),this.rx.getQ());
	}
	
	public Rational getRY(){
		return new Rational(this.ry.getP(),this.ry.getQ());
	}
	
	public Point(Point P){
		x = P.getX();
		y = P.getY();
	}
	
	public Point midPoint(Point N){
		return new Point();
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double newX){
		x = newX;
	}
	
	public void setY(double newY){
		y = newY;
	}
	
	public void setXY(double newX, double newY){
		x = newX;
		y = newY;
	}
	
	public boolean equals(Point P){
		return (this.getX() == P.getX() && this.getY() == P.getY());
	}
	
	
	public double distanceTo(Point P){
		double distance = Math.sqrt(Math.pow((P.getX()-this.getX()), 2.0)+
										Math.pow((P.getY()-this.getY()), 2.0));
		return distance;
	}
	
	public String toString(){
		return "("+fmt.format(x)+"  ,  "+fmt.format(y)+")";
	}
	
}
