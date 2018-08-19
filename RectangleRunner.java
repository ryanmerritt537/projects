package ch4Projects;

public class RectangleRunner {
	public static void main(String[] args){
		//points
		Point ap1 = new Point(10, 10);
		Point ap2 = new Point(100, 100);
		Point bp1 = new Point(20, 0);
		Point bp2 = new Point(150, 150);
		//rectangles
		Rectangle a = new Rectangle(ap1, ap2);
		Rectangle b = new Rectangle(bp1, bp2);
		Rectangle c = a.enclose(b);
		System.out.println(c);
	}
}
