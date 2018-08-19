package ch4Projects;

public class Camera {
	//amount of pictures the camera has
	private double numPhotos;
	//amount of storage the camera originally had
	private double initialStorage;
	//amount of storage the camera has now
	private double currentStorage;
	//whether the camera is on or not
	private boolean isOn;
	
	//default constructor
	public Camera(){
		numPhotos = 0;
		initialStorage = 16;
		currentStorage = 16;
		isOn = false;
	}
	//constructor
	public Camera(double nP, double iS, double cS, boolean iO){
		numPhotos = nP;
		initialStorage = iS;
		currentStorage = cS;
		isOn = iO;
	}
	//.equals method
	public boolean equals(Camera c1){
		return this.getNumPics()==c1.getNumPics()&&
				this.getInitialStorage()==c1.getInitialStorage()&&
				this.getCurrentStorage()==c1.getCurrentStorage()&&
				this.getState()==c1.getState();
	}
	//getters
	public double getNumPics(){
		return numPhotos;
	}
	public double getCurrentStorage(){
		return currentStorage;
	}
	public double getInitialStorage(){
		return initialStorage;
	}
	public boolean getState(){
		return isOn;
	}
	//take picture method
	public void takePicture(){
		if(isOn){
			numPhotos++;
			currentStorage -=0.01;
		}
	}
	//turn on method
	public void turnOn(){
		isOn = true;
	}
	//delete picture method
	public void deletePicture(){
		numPhotos--;
		currentStorage +=0.01;
	}
	//toString method
	public String toString(){
		return "Number of Photos:" + this.getNumPics()
				+ ", Initial Storage: " 
				+ this.getInitialStorage() + "GB"
				+ ", Current Storage: "
				+ this.getCurrentStorage() + "GB";
	}
}
