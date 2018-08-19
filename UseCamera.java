package ch4Projects;

public class UseCamera {
	public static void main(String[] args){
		//creates a camera
		Camera cam = new Camera();
		cam.turnOn();
		cam.takePicture();
		System.out.println("Current Storage: "+cam.getCurrentStorage());
		System.out.println("Is on: "+cam.getState());
		Camera cam2 = new Camera();
		//compares if cameras are the same
		System.out.println(cam2.equals(cam));
		cam2.turnOn();
		cam2.takePicture();
		//compares if the cameras are the same again
		System.out.println(cam2.equals(cam));
	}
}
