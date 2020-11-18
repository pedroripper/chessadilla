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
			while ( board.verify_xy(this.getCoord().x + j*orientation[i][0],
								   this.getCoord().y + j*orientation[i][1])) {
				if (board.get_piece(this.getCoord().x + j*orientation[i][0], 
								   this.getCoord().y + j*orientation[i][1]) != null && board.get_piece(this.getCoord().x + j*orientation[i][0], 
										   this.getCoord().y + j*orientation[i][1]).color == this.color) {
					break;
				}
				lst.add(new Coordinate(this.getCoord().x + j*orientation[i][0],
									   this.getCoord().y + j*orientation[i][1]));
				
				if(board.get_piece(this.getCoord().x + j*orientation[i][0], 
								   this.getCoord().y + j*orientation[i][1]) != null && board.get_piece(this.getCoord().x + j*orientation[i][0], 
										   this.getCoord().y + j*orientation[i][1]).color != this.color) {
					break;
				}
				
				j++;
			}
			i++;
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
