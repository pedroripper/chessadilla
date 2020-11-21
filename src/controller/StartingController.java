package controller;

import Observer.Observer;
import view.ViewFacade;

class StartingController {
	static StartingController sc = null;
	
	static StartingController get_starting_controller() {
		if(sc== null){
			sc = new StartingController();
		}
		return sc;
	}
	
	/*
	 * inicializa a tela
	 */
	void start() {
		ViewFacade view = ViewFacade.get_viewFacade();
		view.init_graphics();
	}

	
	
}
