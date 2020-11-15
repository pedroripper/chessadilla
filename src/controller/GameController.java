package controller;

//import java.util.ArrayList;

import Observer.Observer;
import model.CoordinateInvalid;

import model.ModelFacade;

class GameController implements Observer{
	static GameController gController = null;
	
	static GameController get_gameController() {
		if(gController == null) {
			gController = new GameController();
		}
		return gController;
	}

	@Override
	public void update() {
		
	}
	
	void startGame() throws CoordinateInvalid {
		ModelFacade model = new ModelFacade();
		model.newGame();
		runGame();
	}
	
	void runGame() {
		while(true) {
			
		}
	}
	
	
}
