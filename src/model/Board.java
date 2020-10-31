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
	
	/*
	get_xdim: retorna a dimensao no eixo x do board
	*/
	public int get_xdim() {
		return this.x;
	}
	
	/*
	get_ydim: retorna a dimensao no eixo y do board
	*/
	public int get_ydim() {
		return this.y;
	}
	
	/*
	verify_xy: checa se duas coordenadas sao validas para o tabuleiro
	*/
	private boolean verify_xy(int x,int y) {
		if ((x >= 0) && (x < this.x) && (y >= 0) && (y < this.y)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/*
	add_piece: Recebe piece e duas coordenadas para inserir uma piece. Se existir uma outra piece
	no lugar, esta é removida do tabuleiro.
	*/
	public void add_piece(Piece p, int x, int y) throws CoordinateInvalid {
		if(verify_xy(x,y)) {
			this.b[x][y] = p;
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
	/*
	get_piece: recebe coordenadas x e y. Retorna uma piece se ela existir nas coordenadas.
	caso contrario retorna null
	*/
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
	
	/*
	remove_piece: recebe duas coordenadas. Remove uma piece caso ela exista nessas duas coordenadas.
	Se realmente existir, retorna a piece. Caso contrario retorna null
	*/
	public Piece remove_piece(int x, int y) throws CoordinateInvalid {
		if(verify_xy(x,y)) {
			if(get_piece(x,y) instanceof Piece) {
				Piece trash = this.b[x][y];
				this.b[x][y] = null;
				return trash;
			} else {
				return null;
			}
		} else {
			throw new CoordinateInvalid();
		}
	}
	
}
