package model;

import java.util.ArrayList;

import Observer.Observer;
import model.Piece.Color;
import view.ViewFacade;
class Board {
	
	protected int x; // Dimensão x do tabuleiro
	protected int y; // Dimensão y do tabuleiro
	
	Piece b [][]; // Matriz que representa o tabuleiro
	
	private static Board board = null;	
	Game gInfo = null;
	public static Board get_board(){
		if(board != null) {
			return board;
		}
		board = new Board();
		board.x = 8;
		board.y = 8;
		board.gInfo = Game.get_game();
		board.b = new Piece[board.x][board.y];
		return board;
	}
	
	/*
	 * init_board: inicializa a board com as pieces nas suas posicoes iniciais
	 */
	void init_board() throws CoordinateInvalid {
		for(int i = 0; i < 8; i ++) {
			add_piece(new Pawn(Color.white,i,1,1), i, 1);
			add_piece(new Pawn(Color.black,i,6,2), i, 6);
		}
//		Inicializando os rooks
		add_piece(new Rook(Color.white,0,0,1), 0, 0);
		add_piece(new Rook(Color.white,7,0,1), 7, 0);
		add_piece(new Rook(Color.black,0,7,2), 0, 7);
		add_piece(new Rook(Color.black,7,7,2), 7, 7);
//		Inicializando os knights
		add_piece(new Knight(Color.white,1,0,1), 1,0);
		add_piece(new Knight(Color.white,6,0,1), 6, 0);
		add_piece(new Knight(Color.black,1,7,2), 1, 7);
		add_piece(new Knight(Color.black,6,7,2), 6, 7);
//		Inicializando os bishops
		add_piece(new Bishop(Color.white,2,0,1), 2,0);
		add_piece(new Bishop(Color.white,5,0,1), 5,0);
		add_piece(new Bishop(Color.black,2,7,2), 2,7);
		add_piece(new Bishop(Color.black,5,7,2), 5,7);
//		Inicializando a queen
		add_piece(new Queen(Color.white,3,0,1), 3,0);
		add_piece(new Queen(Color.black,3,7,2), 3,7);
//		Inicializando o king
		add_piece(new King(Color.white,4,0,1), 4,0);
		add_piece(new King(Color.black,4,7,2), 4,7);
		
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
		if ((x >= 0) && (x < board.x) && (y >= 0) && (y < board.y)) {
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
			board.b[x][y] = p;
			board.gInfo.addPiece(p);
			if(p.owner == 1) {
				board.gInfo.p1_pieces.add(p);
			} else {
				board.gInfo.p2_pieces.add(p);
			}
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
	void add_fake(Piece p, int x, int y) throws CoordinateInvalid {
		if(verify_xy(x,y)) {
			board.b[x][y] = p;
		}
		else {
			throw new CoordinateInvalid();
		}
	}
	
	/*
	 * IsInCheque: 
	 * retorna 1 se rei encontra-se em cheque
	 * retorna 2 se rei encontra-se em cheque-mate
	 * retorna 0 se rei nao esta ameacado
	 */
	int isInCheque() {
		return 1;
	}
	
	/*
	get_piece: recebe coordenadas x e y. Retorna uma piece se ela existir nas coordenadas.
	caso contrario retorna null
	*/
	Piece get_piece(int x, int y) throws CoordinateInvalid {
		if (verify_xy(x,y)) {
			return board.b[x][y];
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
				Piece trash = board.b[x][y];
				board.b[x][y] = null;
				trash.isOut = true;
				if(trash.owner == 1) {
					board.gInfo.p1_pieces.remove(trash);
				} else {
					board.gInfo.p2_pieces.remove(trash);
				}
				return trash;
			} else {
				return null;
			}
		} else {
			throw new CoordinateInvalid();
		}
	}
	
	void remove_fake(int x, int y) throws CoordinateInvalid {
		if(verify_xy(x,y)) {
			if(get_piece(x,y) instanceof Piece) {
				board.b[x][y] = null;
			} 
		} else {
			throw new CoordinateInvalid();
		}
	}
	
	ArrayList<String> send_pieces() {
		ArrayList<String> encoded_pieces = new ArrayList<String>();
		for(int j = 0; j < 8 ; j ++) {
				for(int i = 0; i < 8 ; i ++) {
				if(board.b[i][j] instanceof Piece && board.b[i][j] != null) {
					String s = "" + i +""+ j + board.b[i][j].type + board.b[i][j].color.get_color();
					encoded_pieces.add(s);
				}
			}
		}
//		System.out.print(encoded_pieces);
//		System.out.print("Enviando as pecas");
		return encoded_pieces;
	}
	
	ArrayList<Coordinate> getPossibleMoves(int x, int y) throws CoordinateInvalid{
		Piece p = board.get_piece(x,y);
		return p.move_list();
	}
}
