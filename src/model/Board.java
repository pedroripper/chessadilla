package model;

public class Board {
	
	protected int x; // Dimensão x do tabuleiro
	protected int y; // Dimensão y do tabuleiro
	protected Piece b [][]; // Matriz que representa o tabuleiro
	public Board(int x, int y) throws CoordinateInvalid{
		if(x > 0 &&  y > 0) {
			this.x = x;
			this.y = y;
			b = new Piece[x][y];
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
	public int get_xdim() {
		return this.x;
	}
	
	public int get_ydim() {
		return this.y;
	}
	
	private boolean verify_xy(int x,int y) {
		if ((x >= 0) && (x < this.x) && (y >= 0) && (y < this.y)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public void add_piece(Piece p, int x, int y) throws CoordinateInvalid {
		if(verify_xy(x,y)) {
			this.b[x][y] = p;
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
	
	public Piece get_piece(int x, int y) throws CoordinateInvalid {
		if (verify_xy(x,y)) {
			if(this.b[x][y] != null) {
				return this.b[x][y];
			}
			else {
				return null;
			}
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
}
