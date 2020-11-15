package model;

import java.util.ArrayList;

import Observer.Observer;

public class ModelFacade {
	private static Board board = null;
	private static Game game = null;
	private ArrayList<Observer> obs = new ArrayList<Observer>();

	
	public ModelFacade() {
		board = Board.get_board();
		game = Game.get_game();
	}
	
	
	/*
	 * newGame: popula a Board com as pecas
	 */
	public void newGame() throws CoordinateInvalid {
		board.init_board();
	}
	
	public void add_observer(Observer o) {
		this.obs.add(o);
	}
	
	public void remove_observer(Observer o) {
		this.obs.remove(o);
	}
	
	/*
	 * get_board_data(): retorna a matriz de posicao das pecas no board
	 */
	public Piece[][] get_board_data(){
		return board.b;
	}
	
	
	public void pieces_to_display() throws CoordinateInvalid {
		board.send_pieces();
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
		if(p.move(c2) == true) {
			for (Observer ob : this.obs) {
	            ob.update();
	        }
		}
		return true;
	}
	
	
	public int get_turn() {
		return  game.get_turn();
	}
	
	
}
