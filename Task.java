package ch5Projects;

public class Task implements Priority, Complexity, Comparable<Task>{
	private int priority;
	private int complexitie;
	private int ranking;
	public Task(){
		priority = 0;
		complexitie = 0;
		ranking = 0;
	}
	public void setPriority(int x){
		priority = x;
	}
	public int getPriority(){
		return priority;
	}
	public void setComplexity(int complexity){
		complexitie = complexity;
	}
	public int getComplexity(){
		return complexitie;
	}
	public void setRanking(int r){
		ranking = r;
	}
	public int compareTo(Task t){
		if(t.getPriority()>priority){
			t.setRanking(1);
			ranking = 0;
		}else if(t.getPriority() < priority){
			ranking = 1;
			t.setRanking(0);
		}
		return ranking;
	}
	public String toString(){
		return "Priority: "+priority+", Complexity: "+complexitie;
	}
}
