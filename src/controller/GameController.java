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
		model.add_observer(this);
		runGame();
	}
	
	void make_move(int x1, int y1, int x2, int y2) throws CoordinateInvalid {
		ModelFacade model = ModelFacade.getModelFacade();
		model.make_move(x1, y1, x2, y2);
	}
	
	void runGame() {
		
	}
	
	
}
