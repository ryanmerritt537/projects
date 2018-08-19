package ch4Projects;

public class Rectangle {
	private Point p1;//top left
	private Point p2;//bottom right
	/*
	 * I made the next two points so that the
	 * toString() method would print all 4 points
	 * for convenience purposes
	 */
	private Point p3;//top right
	private Point p4;//bottom left
	
	//default constructor for random rectangles
	public Rectangle() {
		int x = (int) (Math.random() * 10) * 40;
		int y = (int) (Math.random() * 10) * 40;
		int x2 = (int) (Math.random() * 10) * 40;
		int y2 = (int) (Math.random() * 10) * 40;
		while((x2+x)>400){
			x2 = (int) (Math.random() * 10) * 40;
		}
		while((y2+y)>400){
			y2 = (int) (Math.random() * 10) * 40;
		}
		p1 = new Point(x, y);
		p2 = new Point(x + x2, x + y2);
		p3 = new Point(p2.getX(), p1.getY());
		p4 = new Point(p1.getX(), p2.getY());
	}
	
	//constructor that requires a rectangle
	public Rectangle(Rectangle r){
		p1 = new Point(r.getP1().getX(),r.getP1().getY());
		p2 = new Point(r.getP2().getX(),r.getP2().getY());
		p3 = new Point(r.getP3().getX(),r.getP3().getY());
		p4 = new Point(r.getP4().getX(),r.getP4().getY());
	}
	
	//creates rectangle from two given points
	public Rectangle(Point pt1, Point pt2) {
		p1 = new Point(pt1.getX(), pt1.getY());
		p2 = new Point(pt2.getX(), pt2.getY());
		p3 = new Point(p2.getX(), p1.getY());
		p4 = new Point(p1.getX(), p2.getY());
	}
	
	//gets point 1 (Top left)
	public Point getP1() {
		return new Point(p1.getX(), p1.getY());
	}
	
	//gets point 2 (Bottom right)
	public Point getP2() {
		return new Point(p2.getX(), p2.getY());
	}
	
	//gets point 3 (Top right)
	public Point getP3() {
		return new Point(p3.getX(), p3.getY());
	}
	
	//gets point 4 (Bottom left)
	public Point getP4() {
		return new Point(p4.getX(), p4.getY());
	}
	
	//makes a rectangle that encloses two other rectangles
	public Rectangle enclose(Rectangle r) {
		// these variables are for convenience
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double rx1 = r.getP1().getX();
		double ry1 = r.getP1().getY();
		double rx2 = r.getP2().getX();
		double ry2 = r.getP2().getY();
		Point np1 = new Point();
		Point np2 = new Point();
		// sets coordinates for point 1
		if (x1 <= rx1 && y1 <= ry1) {
			np1.setXY(x1, y1);
		} else if (x1 <= rx1 && y1 >= ry1) {
			np1.setXY(x1, ry1);
		} else if (x1 >= rx1 && y1 >= ry1) {
			np1.setXY(rx1, ry1);
		} else if (x1 >= rx1 && y1 <= ry1) {
			np1.setXY(rx1, y1);
		}
		// sets coordinates for point 2
		if (x2 >= rx2 && y2 >= ry2) {
			np2.setXY(x2, y2);
		} else if (x2 >= rx2 && y2 <= ry2) {
			np2.setXY(x2, ry2);
		} else if (x2 <= rx2 && y2 <= ry2) {
			np2.setXY(rx2, ry2);
		} else if (x2 <= rx2 && y2 >= ry2) {
			np2.setXY(rx2, y2);
		}
		return new Rectangle(np1, np2);
	}
	
	//returns true if tow rectangles overlap
	public boolean overlap(Rectangle r) {
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double rx1 = r.getP1().getX();
		double ry1 = r.getP1().getY();
		double rx2 = r.getP2().getX();
		double ry2 = r.getP2().getY();
		return (x1 > rx1 && y1 > ry1 && x1 < rx2 && y1 < ry2) 
				|| (x2 < rx2 && y2 < ry2 && x2 > rx1 && y2 > ry1);
	}
	
	//returns true if the rectangles are the same.
	public boolean equals(Rectangle r) {
		return p1.equals(r.getP1()) && p2.equals(r.getP2());
	}
	
	//returns a string that includes all 4 points
	public String toString(){
		return "Point A: " + p1.getX() + " , " + p1.getY()
				+ "\nPoint B: " + p3.getX() + " , " + p3.getY()
				+ "\nPoint C: " + p2.getX() + " , " + p2.getY()
				+ "\nPoint D: " + p4.getX() + " , " + p4.getY();
	}
}
