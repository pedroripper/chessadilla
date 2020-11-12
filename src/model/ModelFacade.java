package model;

import java.util.ArrayList;

public class ModelFacade {
	private static Board board = null;
	
	public ModelFacade() {
		board = Board.get_board();
	}
	
	/*
	 * newGame: popula a Board com as pecas
	 */
	public void newGame() {
		board.init_board();
	}
	
	/*
	 * possible_moves: retorna um array de Coordinate mostrando os 
	 * movimentos possiveis para a piece
	 */
	public ArrayList<Coordinate> possible_moves(Coordinate c) throws CoordinateInvalid {
		Piece p = board.get_piece(c.x, c.y);
		if(p == null) {
			return null;
		}
		return p.move_list();
	}
	
	/*
	 * make_move: realiza o movimento da piece da coordenada c1 para a c2 se for
	 * possivel
	 */
	public boolean make_move(Coordinate c1, Coordinate c2) throws CoordinateInvalid {
		Piece p = board.get_piece(c1.x, c1.y);
		if(p == null) {
			return false;
		}
		return p.move(c2);
	}
	
	
}
