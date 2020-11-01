package model;

import java.util.ArrayList;

abstract class Piece {
	protected enum Color{
		white, black;
	}
	protected char type;
	protected Color color; // Cor do objeto
	Coordinate coord;
	Board board = Board.get_board();
	ArrayList<Coordinate> moveList;

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
	public boolean move(int x, int y) throws CoordinateInvalid{
		if(check_move(new Coordinate(x,y))) {
//			Pode realizar o movimento
			if(board.get_piece(x, y) instanceof Piece) {
				board.remove_piece(x, y);
			}
			board.add_piece(this, x, y);
			board.remove_piece(this.coord.x, this.coord.y);
			return true;
		} else {
			return false;
		}
	}
	
}
