package model;

import java.util.ArrayList;

class Rook extends Piece{

	public Rook(Color c, int x, int y, int o) {
		super(c, x, y, o);
		type = 'r';
	}

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		int i = 0;
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		int[][] orientation = {
				{1,0},
				{-1,0},
				{0,1},
				{0,-1},
		};
		while (i < 4) {
			int j = 1;
			while ( board.verify_xy(this.coord.x + j*orientation[i][0],
								   this.coord.y + j*orientation[i][1])) {
				if (board.get_piece(this.coord.x + j*orientation[i][0], 
								   this.coord.y + j*orientation[i][1]) != null) {
					break;
				}
				lst.add(new Coordinate(this.coord.x + j*orientation[i][0],
									   this.coord.y + j*orientation[i][1]));
				j++;
			}
			i++;
		}
		this.moveList = lst;
		return lst;
	}


}
