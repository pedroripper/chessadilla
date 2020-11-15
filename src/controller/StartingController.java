package controller;

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
		ViewFacade view = new ViewFacade();
		view.init_graphics();
	}
	
	
}
