package controller;

import java.io.FileReader;
import java.util.ArrayList;

//import java.util.ArrayList;

import Observer.Observer;
import model.Coordinate;
import model.CoordinateInvalid;

import model.ModelFacade;
import view.ViewFacade;

class GameController implements Observer{
	static GameController gController;
	ModelFacade model = null;
	ViewFacade view = null;
	
	static GameController get_gameController() {
		if(gController != null) {
			return gController;
		}
		gController = new GameController();
		gController.model = ModelFacade.getModelFacade();
		gController.view = ViewFacade.get_viewFacade();
		return gController;
	}

	@Override
	public void update() {
		gController.view.refresh();
	}
	
	void startGame() throws CoordinateInvalid {
		gController.model.add_observer(gController);
		gController.model.newGame();
		gController.view.init_gameFrame();
	}
	
	void loadGame(FileReader file) throws CoordinateInvalid {
		gController.model.add_observer(gController);
		gController.model.oldGame(file);
		gController.view.init_gameFrame();
	}
	
	/*
	 * Retorna as pecas que devem ser exibidas
	 */
	ArrayList<String> get_piecesToDisplay() throws CoordinateInvalid{
		return gController.model.pieces_to_display();
	}
	
	/*
	 * Retorna a vez do jogador
	 */
	int get_turn() {
		return gController.model.get_turn();
	}
	
	/*
	 * Retorna movimentos possiveis da peca
	 */
	public ArrayList<Coordinate> pre_move(int x, int y) throws CoordinateInvalid {
		return gController.model.possible_moves(x, y);
	}
	
	boolean make_move(int x1, int y1, int x2, int y2) throws CoordinateInvalid {
		boolean move = gController.model.make_move(x1, y1, x2, y2);
		if(move == true) {
			gController.model.toggle_turn();
			return true;
		}
		return false;
	}
	
	/*
	 * Retorna o dono para a peça na coordenada (x,y)
	 */
	int get_owner(int x1, int y1) throws CoordinateInvalid {
		return gController.model.get_owner(x1, y1);
	}

	/*
	 * Checa se existe xeque no jogo
	 */
	ArrayList<String> isThereCheck() throws CoordinateInvalid {
		ArrayList<String> piecesInCheck = new ArrayList<String>();
		piecesInCheck = gController.model.getInCheckPieces();
		return piecesInCheck;
	}
	
	/*
	 * Realiza a promoção do peão no model
	 */
	void promote(char c) throws CoordinateInvalid {
		gController.model.promote(c);
	}

	
	@Override
	public void pawnPromotion() {
		gController.view.togglePromotion();
	}


	
}
