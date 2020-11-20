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
	ModelFacade model = null;
	Game gInfo = null;
	public static Board get_board(){
		if(board != null) {
			return board;
		}
		board = new Board();
		board.x = 8;
		board.y = 8;
		board.model = ModelFacade.getModelFacade();
		board.gInfo = Game.get_game();
		board.b = new Piece[board.x][board.y];
		return board;
	}
	
	/*
	 * init_board: inicializa a board com as pieces nas suas posicoes iniciais
	 */
	void init_board() throws CoordinateInvalid {
		for(int i = 0; i < 8; i ++) {
			add_piece(new Pawn(Color.white,i,1,1,'p'), i, 1);
			add_piece(new Pawn(Color.black,i,6,2,'p'), i, 6);
		}
//		Inicializando os rooks
		add_piece(new Rook(Color.white,0,0,1,'r'), 0, 0);
		add_piece(new Rook(Color.white,7,0,1,'r'), 7, 0);
		add_piece(new Rook(Color.black,0,7,2,'r'), 0, 7);
		add_piece(new Rook(Color.black,7,7,2,'r'), 7, 7);
//		Inicializando os knights
		add_piece(new Knight(Color.white,1,0,1,'c'), 1,0);
		add_piece(new Knight(Color.white,6,0,1,'c'), 6, 0);
		add_piece(new Knight(Color.black,1,7,2,'c'), 1, 7);
		add_piece(new Knight(Color.black,6,7,2,'c'), 6, 7);
//		Inicializando os bishops
		add_piece(new Bishop(Color.white,2,0,1,'b'), 2,0);
		add_piece(new Bishop(Color.white,5,0,1,'b'), 5,0);
		add_piece(new Bishop(Color.black,2,7,2,'b'), 2,7);
		add_piece(new Bishop(Color.black,5,7,2,'b'), 5,7);
//		Inicializando a queen
		add_piece(new Queen(Color.white,3,0,1,'q'), 3,0);
		add_piece(new Queen(Color.black,3,7,2,'q'), 3,7);
//		Inicializando o king
		add_piece(new King(Color.white,4,0,1,'k'), 4,0);
		add_piece(new King(Color.black,4,7,2,'k'), 4,7);
		board.gInfo.c_k1 = new Coordinate(4,0);
		board.gInfo.c_k2 = new Coordinate(4,7);
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
//			p.setCoord(new Coordinate(x,y));
			System.out.print("\n  AQUI ==> "+ numPiece() +  " \n");

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
	
	int numPiece() throws CoordinateInvalid {
		int num = 0;
		for(int j = 0; j< 8; j++) {
			for(int i = 0; i < 8; i ++) {
				if(board.get_piece(i, j)!= null) {
					num ++;
				}
			}
		}
		return num;
	}
	
	/*
	get_piece: recebe coordenadas x e y. Retorna uma piece se ela existir nas coordenadas.
	caso contrario retorna null
	*/
	Piece get_piece(int x, int y) throws CoordinateInvalid {
		if (board.verify_xy(x,y)) {
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
				Piece trash = this.model.board.get_piece(x, y);
				this.model.board.b[x][y] = null;
				
				System.out.print("\n Removendo -- " + trash.type +  "\n");
				
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
	
	ArrayList<String> send_pieces() throws CoordinateInvalid {
		ArrayList<String> encoded_pieces = new ArrayList<String>();
		for(int j = 0; j < 8 ; j ++) {
				for(int i = 0; i < 8 ; i ++) {
				Piece p = board.get_piece(i, j);
				if(i== 6 && j ==2) {
					if(p != null) {
						System.out.print("Hej\n");
					} else {
						System.out.print("tak\n");
					}
					
				}
				
				if(p != null) {
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
		if(p == null) {
			return null;
		}
		
		System.out.print("A peça selecionada tem o tipo ==> "+ p.type + "\n");
		
		boolean isInCheck;
		if(p.get_owner() == 1) {
			isInCheck =  board.gInfo.isP1inCheck;
		} else {
			isInCheck = board.gInfo.isP2inCheck;
		}
		if(isInCheck) {
		if(board.model.getInCheckPieces().size() > 0) {
			if(p instanceof King) {
				p.move_list();
				Piece enemy;
				if(p.owner == 1) {
					enemy = board.gInfo.p1_foe;
				} else { 
					enemy = board.gInfo.p1_foe;
				}
				ArrayList<Coordinate> savingMoves = new ArrayList<Coordinate>(); 
				for(Coordinate move: p.moveList) {
					if(!enemy.check_move(move)) {
						savingMoves.add(move);
					}
				}
				return savingMoves;
			} else {
				ArrayList<Coordinate> savingMoves = new ArrayList<Coordinate>(); 
				p.move_list();
				Piece enemy;
				if(p.owner == 1) {
					enemy = board.gInfo.p1_foe;
				} else { 
					enemy = board.gInfo.p2_foe;
				}

				for(Coordinate move: p.moveList) {
					System.out.print(""+board.gInfo.getKingPos(p.owner).x +" " + board.gInfo.getKingPos(p.owner).y + "\n");
					if(enemy.blockedMove(enemy, p, move, board.gInfo.getKingPos(p.owner))) {
						savingMoves.add(move);
					}
				}
				return savingMoves;
			}
		}
		}
		return p.move_list();
	}
	
	ArrayList<Piece> getPlayerPieces(int player) throws CoordinateInvalid{
		ArrayList<Piece> lst = new ArrayList<Piece>();
		for(int j = 0; j < 8; j ++) {
			for(int i = 0; i < 8; i ++) {
				Piece p = board.get_piece(i, j);
				if(p!=null && p.owner == player) {
					lst.add(p);
				}
			}
		}
		return lst;
	}
	
	
}
