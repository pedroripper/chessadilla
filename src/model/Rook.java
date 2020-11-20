package model;

import java.util.ArrayList;

class Rook extends Piece{

	public Rook(Color c, int x, int y, int o, char type) {
		super(c, x, y, o,type);
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
			while ( this.board.verify_xy(this.getCoord().x + j*orientation[i][0],
								   this.getCoord().y + j*orientation[i][1])) {
				if (this.board.get_piece(this.getCoord().x + j*orientation[i][0], 
								   this.getCoord().y + j*orientation[i][1]) != null && this.board.get_piece(this.getCoord().x + j*orientation[i][0], 
										   this.getCoord().y + j*orientation[i][1]).color == this.color) {
					break;
				}
				lst.add(new Coordinate(this.getCoord().x + j*orientation[i][0],
									   this.getCoord().y + j*orientation[i][1]));
				
				if(this.board.get_piece(this.getCoord().x + j*orientation[i][0], 
								   this.getCoord().y + j*orientation[i][1]) != null && this.board.get_piece(this.getCoord().x + j*orientation[i][0], 
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
	
	
	boolean canCastling(Coordinate c) throws CoordinateInvalid {
		if(this.nMoves > 0) {
			return false;
		}
		Piece king = this.board.get_piece(c.get_x(), c.get_y());
		if(!(king instanceof King) || king.nMoves > 0) {
			return false;
		}
		int xini = king.getCoord().get_x();
		int yini = king.getCoord().get_y();
		int xfim = this.getCoord().get_x();
		if(xfim > xini) {
			while(xini < xfim) {
				xini ++;
				if(this.board.get_piece(xini, yini) != null) {
					return false;
				}
			}
		} else {
			while(xini > xfim) {
				xini --;
				if(this.board.get_piece(xini, yini) != null) {
					return false;
				}
			}
		}
		return true;	
	}

	int testCheck() throws CoordinateInvalid {
		return 0;
	}


	int testCheckMate(Piece enemy) throws CoordinateInvalid {
		return 0;
	}


}
