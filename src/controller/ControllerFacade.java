package controller;

public class ControllerFacade {
	StartingController sc = null;
	
	public ControllerFacade() {
		sc = StartingController.get_starting_controller();
	}
	
	public void init() {
		sc.start();
	}
}
