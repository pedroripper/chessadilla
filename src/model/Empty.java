package model;

import java.util.ArrayList;

class Empty extends Piece{

	public Empty(Color c, int x, int y, int owner) {
		super(c, x, y, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		// TODO Auto-generated method stub
		return null;
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
