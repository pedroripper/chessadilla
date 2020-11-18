package model;

import java.util.ArrayList;

import Observer.Observer;

abstract class Piece {
	enum Color{
		white('w'), black('b');
		
		char c;
		private Color(char c) {
			this.c = c;
		}
		
		char get_color() {
			return c;
		}
		
	}
	protected Color color; // Cor do objeto
	private Coordinate coord;
	Board board = null;
	ArrayList<Coordinate> moveList;
	ArrayList<Coordinate> savingList;
	int owner;
	Game gInfo = null;
	
	char type;
	public boolean isOut;

	public Piece(Color c, int x, int y, int owner) {
		this.isOut = false;
		this.color =  c;
		this.coord = new Coordinate(x,y);
		this.owner = owner;
		this.board = Board.get_board();
		this.gInfo = Game.get_game();
	}
	
	
	public boolean check_move(Coordinate c) throws CoordinateInvalid {
		this.move_list();
		for(Coordinate lst: moveList){
			if(lst.get_x() == c.get_x() && lst.get_y() == c.get_y()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean blockedMove(Piece foe,Piece savior, Coordinate c, Coordinate destiny) throws CoordinateInvalid  {
		board.add_fake(savior, c.get_x(), c.get_y());
		foe.move_list();
		if(foe.check_move(destiny)) {
			board.remove_fake(c.get_x(), c.get_y());
			return true;
		}
		board.remove_fake(c.get_x(), c.get_y());
		return false;
	}
	
	public int get_owner() {
		return this.owner;
	}
	
	public abstract ArrayList<Coordinate> move_list() throws CoordinateInvalid;
	
	
	/*
	movimenta a piece
	*/
	public boolean move(Coordinate c) throws CoordinateInvalid{
		if(check_move(new Coordinate(c.x,c.y))) {
//			Pode realizar o movimento
			if(board.get_piece(c.x, c.y) instanceof Piece) {
//				board.gInfo.updatePieceList(board.get_piece(c.x, c.y), null);
				board.remove_piece(c.x, c.y);
			}
			board.add_piece(this, c.x, c.y);
			board.remove_piece(this.coord.x, this.coord.y);
			this.coord = c;
//			board.gInfo.updatePieceList(this, board.get_piece(c.x, c.y));
			if(this instanceof King) {
//				System.out.print("Cai aqui");
			}
			return true;
		} else {
			return false;
		}
	}
	
	void setCoord(Coordinate c) {
		this.coord = c;
	}
	
	Coordinate getCoord() {
		return this.coord;
	}


	abstract int testCheck() throws CoordinateInvalid;
	abstract int testCheckMate(Piece enemy) throws CoordinateInvalid;

}
