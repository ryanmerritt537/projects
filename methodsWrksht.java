package ch4Projects;

public class methodsWrksht {
	
	public static double a(int x, int y, int z){
		return (x+y+z)/3.0;
	}
	public static double a(int x,int y){
		return (x+y)/2.0;
	}
	public static void main(String[] args){
		System.out.println(a(3,3));
	}
}
