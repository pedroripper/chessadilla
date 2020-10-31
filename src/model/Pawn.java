package model;

import java.util.ArrayList;

class Pawn extends Piece{
	public Pawn(Color c, int x, int y) {
		super(c, x, y);
	}

	public void move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(color == Color.white) {
			Piece p = board.get_piece(this.coord.x+1, this.coord.y+1);
			if(p != null && p.color == Color.black) { // existe piece para ser removida
				
			}
		}
		
	}

	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}


}
