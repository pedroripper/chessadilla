package model;

import java.util.ArrayList;

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
	Coordinate coord;
	Board board = Board.get_board();
	ArrayList<Coordinate> moveList;
	char type;

	public Piece(Color c, int x, int y) {
		this.color =  c;
		this.coord = new Coordinate(x,y);
	}
	
	
	public boolean check_move(Coordinate c) {
		for(Coordinate lst: moveList){
			if(lst.equals(c)) {
				return true;
			}
		}
		return false;
	}	
	
	public abstract ArrayList<Coordinate> move_list() throws CoordinateInvalid;
	
	/*
	movimenta a piece
	*/
	public boolean move(Coordinate c) throws CoordinateInvalid{
		if(check_move(new Coordinate(c.x,c.y))) {
//			Pode realizar o movimento
			if(board.get_piece(c.x, c.y) instanceof Piece) {
				board.remove_piece(c.x, c.y);
			}
			board.add_piece(this, c.x, c.y);
			board.remove_piece(this.coord.x, this.coord.y);
			return true;
		} else {
			return false;
		}
	}
	
}
