package model;

import model.Piece.Color;

class Board {
	
	protected int x; // Dimensão x do tabuleiro
	protected int y; // Dimensão y do tabuleiro
	protected Piece b [][]; // Matriz que representa o tabuleiro
	private static Board board = null;

	
	public static Board get_board(){
		if(board != null) {
			return board;
		}
		board = new Board();
		board.x = 8;
		board.y = 8;
		board.b = new Piece[board.x][board.y];
		return board;
	}
	
	/*
	 * init_board: inicializa a board com as pieces nas suas posicoes iniciais
	 */
	void init_board() {
		for(int i = 0; i < 8; i ++) {
			board.b[i][2] = new Pawn(Color.white,i,2);
			board.b[i][6] = new Pawn(Color.black,i,2);
		}
//		Inicializando os rooks
		board.b[0][0] = new Rook(Color.white,0,0);
		board.b[7][0] = new Rook(Color.white,7,0);
		board.b[0][7] = new Rook(Color.black,0,7);
		board.b[7][7] = new Rook(Color.black,7,7);
//		Inicializando os knights
		board.b[1][0] = new Knight(Color.white,1,0);
		board.b[6][0] = new Knight(Color.white,6,0);
		board.b[1][7] = new Knight(Color.black,1,7);
		board.b[6][7] = new Knight(Color.black,6,7);
//		Inicializando os bishops
		board.b[2][0] = new Bishop(Color.white,2,0);
		board.b[5][0] = new Bishop(Color.white,5,0);
		board.b[2][7] = new Bishop(Color.black,2,7);
		board.b[5][7] = new Bishop(Color.black,5,7);
//		Inicializando a queen
		board.b[3][0] = new Queen(Color.white,3,0);
		board.b[3][7] = new Queen(Color.black,3,7);
//		Inicializando o king
		board.b[4][0] = new King(Color.white,4,0);
		board.b[4][7] = new King(Color.black,4,7);
	}
	
	
	/*
	get_xdim: retorna a dimensao no eixo x do board
	*/
	private int get_xdim() {
		return this.x;
	}
	
	/*
	get_ydim: retorna a dimensao no eixo y do board
	*/
	private int get_ydim() {
		return this.y;
	}
	
	/*
	verify_xy: checa se duas coordenadas sao validas para o tabuleiro
	*/
	boolean verify_xy(int x,int y) {
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
	void add_piece(Piece p, int x, int y) throws CoordinateInvalid {
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
	Piece get_piece(int x, int y) throws CoordinateInvalid {
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
	Piece remove_piece(int x, int y) throws CoordinateInvalid {
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
