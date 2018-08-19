package ch5Projects;

public class ChessPiece implements Comparable<ChessPiece>{
	private String type;
	private int typeNum;
	private int player;
	
	public ChessPiece(String s){
		type = s;
		classify();
	}
	
	public void classify(){
		if(type=="Pawn")
			typeNum = 1;
		else if(type == "Knight" || type == "Bishop")
			typeNum = 2;
		else if(type == "Rook")
			typeNum = 3;
		else if(type == "Queen")
			typeNum = 4;
		else
			typeNum = 5;
	}
	
	public int getTypeNum(){
		return typeNum;
	}
	
	public String returnType(){
		return type;
	}
	
	public int compareTo(ChessPiece c){
		if(Math.min(typeNum, c.getTypeNum())==typeNum){
			return -1;
		}else if(typeNum==c.getTypeNum()){
			return 0;
		}
		return 1;
	}
	
	public int returnPlayer(){
		return player;
	}
	
}
