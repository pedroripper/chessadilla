package model;

import java.util.ArrayList;

class Pawn extends Piece{
	public Pawn(Color c, int x, int y, int  o, char type) {
		super(c, x, y, o,type);
	}
	
	

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(color == Color.white) {
			if(this.board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
				Piece p = this.board.get_piece(this.getCoord().x+1, this.getCoord().y+1);
				if(p != null && p.color == Color.black) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
				}
			}
			if(this.board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
				Piece p2 = this.board.get_piece(this.getCoord().x-1, this.getCoord().y+1);
				if(p2 != null && p2.color == Color.black) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
				}
			}
			if(this.board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
				if(this.board.get_piece(this.getCoord().x, this.getCoord().y+1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
				}
			}
			if(this.getCoord().y == 1) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(this.board.get_piece(this.getCoord().x, this.getCoord().y+1) == null && 
						this.board.get_piece(this.getCoord().x, this.getCoord().y+2) == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+2));
				}
			}
		} 
		else {
			if(this.board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
				Piece p = this.board.get_piece(this.getCoord().x+1, this.getCoord().y-1);
				if(p != null && p.color == Color.white) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
				}
			}
			if(this.board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
				Piece p2 = this.board.get_piece(this.getCoord().x-1, this.getCoord().y-1);
				if(p2 != null && p2.color == Color.white) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
				}
			}
			if(this.board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
				if(this.board.get_piece(this.getCoord().x, this.getCoord().y-1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
				}
			}
			if(this.getCoord().y == 6) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(this.board.get_piece(this.getCoord().x, this.getCoord().y-1) == null && 
						this.board.get_piece(this.getCoord().x, this.getCoord().y-2) == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-2));
				}
			}
		}
		this.moveList = lst;
		return lst;
	}



	boolean canCastling(Coordinate c) throws CoordinateInvalid {
		return false;
	}



	int testCheck() throws CoordinateInvalid {
		return 0;
	}



	int testCheckMate(Piece enemy) throws CoordinateInvalid {
		return 0;
	}







}
