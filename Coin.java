package ch5Projects;

import java.util.Random;

public class Coin implements Lockable{
	private int key;
	private final int HEADS = 0;
	private final int TAILS = 1;
	private int face;
	private boolean isLocked;
	//-----------------------------------------------------------------
	// Sets up the coin by flipping it initially.
	//-----------------------------------------------------------------
	public Coin ()
	{	
		key = 0;
		flip();
		isLocked = true;
	}
	//-----------------------------------------------------------------
	// Flips the coin by randomly choosing a face value.
	//-----------------------------------------------------------------
	public void flip ()
	{	
		if(unlocked()){
			face = (int) (Math.random() * 2);
		}
	}
	//-----------------------------------------------------------------
	// Returns true if the current face of the coin is heads.
	//-----------------------------------------------------------------
	public boolean isHeads ()
	{
		return (face == HEADS);
	}
	//-----------------------------------------------------------------
	// Returns the current face of the coin as a string.
	//-----------------------------------------------------------------
	public String toString(){
		String faceName;
		if (face == HEADS)
			faceName = "Heads";
		else
			faceName = "Tails";
		return faceName;
	}
	
	//interface stuff
	public void setKey(int k){
		key = k;
	}
		
	public void lock(int k){
		if(k == key){
			isLocked = true;
		}
	}
	
	public void unlock(int k){
		if(k == key){
			isLocked = false;
		}
	}
	
	public boolean unlocked(){
		return !isLocked;
	}
}
