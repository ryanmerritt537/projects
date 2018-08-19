package ch4Projects;
import java.util.Scanner;

public class NewPigGame {
	static PairOfDice compDice = new PairOfDice(6,6);
	static PairOfDice yourDice = new PairOfDice(6,6);
	static int player = 1, yourPoints = 0, compPoints = 0;
	static Scanner kbd = new Scanner(System.in);
	public static void s(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		int roundPoints = 0;
		while(compPoints<100&&yourPoints<100){
			if(compPoints<100&&yourPoints<100){
				s("Enter 1 to roll, "
						+ "enter 2 to let the computer roll");
				player = kbd.nextInt();
			}
			while(player == 1&&compPoints<100&&yourPoints<100){
				roundPoints = 0;
				yourDice.roll();
				if(yourDice.isHalfSnakeEyes()){
					s("You got half snake eyes");
					s("Points earned this round: "+roundPoints);
					s("Total points: "+yourPoints+"\n");
					roundPoints = 0;
					player = 2;
				}else if(yourDice.isSnakeEyes()){
					s("You got snake eyes");
					s("Points earned this round: "+roundPoints);
					roundPoints = 0;
					yourPoints = 0;
					player = 2;
					s("Total points: "+yourPoints+"\n");
				}else {
					s("You rolled " + yourDice.getD1Value()
						+ " , " + yourDice.getD2Value());
					roundPoints+=yourDice.getD1Value();
					roundPoints+=yourDice.getD2Value();
					yourPoints+=roundPoints;
					s("Points earned this round: "+roundPoints);
					s("Total points: "+yourPoints+"");
					if (compPoints<100&&yourPoints<100){
						s("Enter 1 to roll, "
								+ "enter 2 to let the "
								+ "computer roll\n");
						player = kbd.nextInt();
					}
				}
			}
			roundPoints = 0;
			int compRound = 0;
			int max = 20;
			while(player == 2&&compRound<=20
					&&compPoints<100&&yourPoints<100){
				roundPoints = 0;
				compDice.roll();
				if(compPoints < yourPoints+30){
					max = 50;
				}else if(yourPoints < compPoints + 40){
					max = 15;
				}else{
					max = 20;
				}
				if(yourPoints > 80){
					max = 60;
				}else if(compPoints > 85){
					max = 10;
				}
				if(compDice.isHalfSnakeEyes()){
					s("Comp got half snake eyes");
					roundPoints = 0;
					player = 0;
				}else if(compDice.isSnakeEyes()){
					s("Comp got snake eyes");
					roundPoints = 0;
					compPoints = 0;
					player = 0;
				}else if(compRound >=max){
					player = 0;
				}else{
					s("Comp rolled " + compDice.getD1Value()
						+ " , " + compDice.getD2Value());
					roundPoints+=compDice.getD1Value()
							+compDice.getD2Value();
					compPoints+=roundPoints;
					compRound+=roundPoints;
				}
				s("Comp score: "+compPoints);
			}
		}
		if(yourPoints>compPoints){
			s("You won!");
		}else{
			s("Computer won.");
		}
	}
}
