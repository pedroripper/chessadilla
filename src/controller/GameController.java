package controller;

import java.util.ArrayList;

//import java.util.ArrayList;

import Observer.Observer;
import model.Coordinate;
import model.CoordinateInvalid;

import model.ModelFacade;
import view.ViewFacade;

class GameController implements Observer{
	static GameController gController = null;
	ModelFacade model = null;
	ViewFacade view = null;
	
	static GameController get_gameController() {
		if(gController != null) {
			return gController;
		}
		gController = new GameController();
		gController.model = ModelFacade.getModelFacade();
		gController.view = ViewFacade.get_viewFacade();
		gController.model.add_observer(gController);
		return gController;
	}

	@Override
	public void update() {
		System.out.print("Update");
		gController.view.refresh();
	}
	
	void startGame() throws CoordinateInvalid {
		gController.model.newGame();
		gController.model.add_observer(gController);
		gController.view.init_gameFrame();
	}
	
	ArrayList<String> get_piecesToDisplay() throws CoordinateInvalid{
		return gController.model.pieces_to_display();
	}
	
	int get_turn() {
		return gController.model.get_turn();
	}
	
	public ArrayList<Coordinate> pre_move(int x, int y) throws CoordinateInvalid {
		return gController.model.possible_moves(x, y);
	}
	
	void make_move(int x1, int y1, int x2, int y2) throws CoordinateInvalid {
		boolean move = gController.model.make_move(x1, y1, x2, y2);
		if(move == true) {
			gController.model.toggle_turn();
		}
	}
	
	void runGame() {
		
	}

	public int get_owner(int x1, int y1) throws CoordinateInvalid {
		return gController.model.get_owner(x1, y1);
	}

	public ArrayList<String> isThereCheck() throws CoordinateInvalid {
		ArrayList<String> piecesInCheck = new ArrayList<String>();
		piecesInCheck = gController.model.getInCheckPieces();
		return piecesInCheck;
	}

	public ArrayList<Coordinate> getMustMoves(int i) {
		return gController.model.getMustMoves(i);
	}
	
	
}
