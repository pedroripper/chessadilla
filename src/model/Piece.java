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
		if(!foe.check_move(destiny)) {
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
			this.board = Board.get_board();
			//			Pode realizar o movimento
			if(this.board.get_piece(c.x, c.y) instanceof Piece) {
				this.board.remove_piece(c.x, c.y);
			}
			Piece p  =  this;
			this.board.remove_piece(this.coord.x, this.coord.y);
			p.coord = c;
			
			this.board.add_piece(p, c.x, c.y);

			if(p instanceof Queen) {
				System.out.print("Olha oq  temos aqui  \n");
				System.out.print(p.coord.x +  "  "+ p.coord.y +"\n");
				System.out.print(this.board.get_piece(p.coord.x, p.coord.y));
				System.out.print((p != null) + "\n");

			}
			if(this instanceof King) {
				if(this.owner == 1) {
					this.board.gInfo.c_k1 = c;
				} else {
					this.board.gInfo.c_k2 =c;
				}
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
