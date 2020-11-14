package model;

import java.util.ArrayList;

class Pawn extends Piece{
	public Pawn(Color c, int x, int y) {
		super(c, x, y);
		type = 'p';
	}
	
	

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(color == Color.white) {
			if(board.verify_xy(this.coord.x+1, this.coord.y+1)) {
				Piece p = board.get_piece(this.coord.x+1, this.coord.y+1);
				if(p != null && p.color == Color.black) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.coord.x+1, this.coord.y+1));
				}
			}
			if(board.verify_xy(this.coord.x-1, this.coord.y+1)) {
				Piece p2 = board.get_piece(this.coord.x-1, this.coord.y+1);
				if(p2 != null && p2.color == Color.black) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.coord.x-1, this.coord.y+1));
				}
			}
			if(board.verify_xy(this.coord.x, this.coord.y+1)) {
				if(board.get_piece(this.coord.x, this.coord.y+1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.coord.x, this.coord.y+1));
				}
			}
			if(this.coord.y == 1) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(board.get_piece(this.coord.x, this.coord.y+1) == null && 
						board.get_piece(this.coord.x, this.coord.y+2) == null) {
					lst.add(new Coordinate(this.coord.x, this.coord.y+2));
				}
			}
		} 
		else {
			if(board.verify_xy(this.coord.x+1, this.coord.y-1)) {
				Piece p = board.get_piece(this.coord.x+1, this.coord.y-1);
				if(p != null && p.color == Color.white) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.coord.x+1, this.coord.y-1));
				}
			}
			if(board.verify_xy(this.coord.x-1, this.coord.y-1)) {
				Piece p2 = board.get_piece(this.coord.x-1, this.coord.y-1);
				if(p2 != null && p2.color == Color.white) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.coord.x-1, this.coord.y-1));
				}
			}
			if(board.verify_xy(this.coord.x, this.coord.y-1)) {
				if(board.get_piece(this.coord.x, this.coord.y-1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.coord.x, this.coord.y-1));
				}
			}
			if(this.coord.y == 6) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(board.get_piece(this.coord.x, this.coord.y-1) == null && 
						board.get_piece(this.coord.x, this.coord.y-2) == null) {
					lst.add(new Coordinate(this.coord.x, this.coord.y-2));
				}
			}
		}
		this.moveList = lst;
		return lst;
	}



}
