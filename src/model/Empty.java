package model;

import java.util.ArrayList;

class Empty extends Piece{

	public Empty(Color c, int x, int y, int owner, char type) {
		super(c, x, y, owner, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		// TODO Auto-generated method stub
		return null;
	}

	

}
