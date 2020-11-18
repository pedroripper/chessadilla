package model;

import java.util.ArrayList;

class Knight extends Piece{

	public Knight(Color c, int x, int y, int o) {
		super(c, x, y, o);
		type = 'c';
	}

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
			if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+2)) {
				// movimento no L em pe superior direita
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y+2);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+2));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+2));
				}
			}
			if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+2)) {
				// movimento no L em pe superior esquerda
				Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y+2);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+2));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+2));
				}
			}
			if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-2)) {
				// movimento no L em pe inferior esquerda
				Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y-2);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-2));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-2));
				}
			}
			if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-2)) {
				// movimento no L em pe inferior direita
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y-2);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-2));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-2));
				}
			}
			if(board.verify_xy(this.getCoord().x+2, this.getCoord().y+1)) {
				// movimento no L deitado superior direita
				Piece p = board.get_piece(this.getCoord().x+2, this.getCoord().y+1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+2, this.getCoord().y+1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+2, this.getCoord().y+1));
				}
			}
			if(board.verify_xy(this.getCoord().x-2, this.getCoord().y+1)) {
				// movimento no L deitado superior esquerda
				Piece p = board.get_piece(this.getCoord().x-2, this.getCoord().y+1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-2, this.getCoord().y+1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-2, this.getCoord().y+1));
				}
			}
			if(board.verify_xy(this.getCoord().x-2, this.getCoord().y-1)) {
				// movimento no L deitado inferior esquerda
				Piece p = board.get_piece(this.getCoord().x-2, this.getCoord().y-1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-2, this.getCoord().y-1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-2, this.getCoord().y-1));
				}
			}
			if(board.verify_xy(this.getCoord().x+2, this.getCoord().y-1)) {
				// movimento no L deitado inferior direita
				Piece p = board.get_piece(this.getCoord().x+2, this.getCoord().y-1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+2, this.getCoord().y-1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+2, this.getCoord().y-1));
				}
			}
		moveList = lst;
		return lst;
	}

	@Override
	int testCheck() throws CoordinateInvalid {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int testCheckMate(Piece enemy) throws CoordinateInvalid {
		// TODO Auto-generated method stub
		return 0;
	}



}
