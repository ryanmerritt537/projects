package ch4Projects;

public class TrafficLight {
	private int state;
	public TrafficLight(){
		state = 0;
	}
	public String light(){
		if(state == 0){
			return "red";
		}else if(state == 1){
			return "green";
		}
		return "yellow";
	}
	public void change(){
		state++;
		if(state>2){
			state = 0;
		}
	}
	public String toString(){
		return light();
	}
}
