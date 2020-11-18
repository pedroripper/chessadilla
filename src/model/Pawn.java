package model;

import java.util.ArrayList;

class Pawn extends Piece{
	public Pawn(Color c, int x, int y, int  o) {
		super(c, x, y, o);
		type = 'p';
	}
	
	

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(color == Color.white) {
			if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y+1);
				if(p != null && p.color == Color.black) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
				}
			}
			if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
				Piece p2 = board.get_piece(this.getCoord().x-1, this.getCoord().y+1);
				if(p2 != null && p2.color == Color.black) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
				}
			}
			if(board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
				if(board.get_piece(this.getCoord().x, this.getCoord().y+1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
				}
			}
			if(this.getCoord().y == 1) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(board.get_piece(this.getCoord().x, this.getCoord().y+1) == null && 
						board.get_piece(this.getCoord().x, this.getCoord().y+2) == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+2));
				}
			}
		} 
		else {
			if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y-1);
				if(p != null && p.color == Color.white) { 
					// existe piece para ser removida na diagonal direita
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
				}
			}
			if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
				Piece p2 = board.get_piece(this.getCoord().x-1, this.getCoord().y-1);
				if(p2 != null && p2.color == Color.white) { 
					// existe piece para ser removida na diagonal esqueda
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
				}
			}
			if(board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
				if(board.get_piece(this.getCoord().x, this.getCoord().y-1) == null) { 
					// nao existe ninguem na frente a uma casa
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
				}
			}
			if(this.getCoord().y == 6) { 
				//encontra-se na posicao inicial e pode andar duas casas
				if(board.get_piece(this.getCoord().x, this.getCoord().y-1) == null && 
						board.get_piece(this.getCoord().x, this.getCoord().y-2) == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-2));
				}
			}
		}
		this.moveList = lst;
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
