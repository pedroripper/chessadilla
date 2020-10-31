package model;

class Pawn extends Piece{
	public Pawn(Color c, int x, int y) {
		super(c, x, y);
		this.color = c;
		this.x = x;
		this.y = y;
	}
}
