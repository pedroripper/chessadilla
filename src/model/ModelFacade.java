package model;

import java.util.ArrayList;

import Observer.Observer;

public class ModelFacade {
	private static ModelFacade mf=  null;
	private ArrayList<Observer> obs = new ArrayList<Observer>();

	
	public ModelFacade getModelFacade() {
		if(mf == null) {
			mf = new ModelFacade();
		}
		return mf;
	}
	
	
	/*
	 * newGame: popula a Board com as pecas
	 */
	public void newGame() throws CoordinateInvalid {
		Board board = Board.get_board();
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
	public static Piece[][] get_board_data(){
		Board board = Board.get_board();
		return board.b;
	}
	
	
	public void pieces_to_display() throws CoordinateInvalid {
		Board board = Board.get_board();
		board.send_pieces();
	}
	
	/*
	 * possible_moves: retorna um array de Coordinate mostrando os 
	 * movimentos possiveis para a piece
	 */
	public static ArrayList<Coordinate> possible_moves(int x, int y) throws CoordinateInvalid {
		Board board = Board.get_board();
		Coordinate c = new Coordinate(x,y);
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
	public boolean make_move(int x1, int y1, int x2, int y2) throws CoordinateInvalid {
		Board board = Board.get_board();
		Coordinate c1 = new Coordinate(x1,y1);
		Coordinate c2 = new Coordinate(x2,y2);
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
		Game game = Game.get_game();
		return  game.get_turn();
	}
	
	
}
