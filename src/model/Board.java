package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
		board.gInfo.setKingCoord(1, new Coordinate(4,0));
		board.gInfo.setKingCoord(2, new Coordinate(4,7));
	}
	
	void load_board(FileReader file) throws CoordinateInvalid {
		BufferedReader buffered_reader = new BufferedReader(file);
		int i,linha,coluna;
		linha = 0;
		coluna = 0;
		try {
			gInfo.set_np1(Integer.parseInt(buffered_reader.readLine()));
			gInfo.set_np2(Integer.parseInt(buffered_reader.readLine()));
			gInfo.set_round(Integer.parseInt(buffered_reader.readLine()));
			gInfo.set_turn(Integer.parseInt(buffered_reader.readLine()));
			String aux = buffered_reader.readLine();
			gInfo.set_c_k1(new Coordinate(
								Integer.parseInt(String.valueOf(aux.charAt(0))),
								Integer.parseInt(String.valueOf(aux.charAt(2)))));
			aux = buffered_reader.readLine();
			gInfo.set_c_k2(new Coordinate(
								Integer.parseInt(String.valueOf(aux.charAt(0))),
								Integer.parseInt(String.valueOf(aux.charAt(2)))));
			gInfo.set_isP1inCheck(Boolean.parseBoolean(buffered_reader.readLine()));
			gInfo.set_isP2inCheck(Boolean.parseBoolean(buffered_reader.readLine()));
			
			while((i = buffered_reader.read())!=-1) {
				char c = (char)i;
				if(c == '1') {
					c = (char)buffered_reader.read();
					if(c == 'r') {
						String s = "";
						while((c = (char)buffered_reader.read()) != '$') s = s + c;
						Rook rook = new Rook(Color.white,coluna,linha,1,'r');
						rook.nMoves = Integer.parseInt(s);
						add_piece(rook, coluna, linha);
					}
					if(c == 'p') add_piece(new Pawn(Color.white,coluna,linha,1,'p'), coluna, linha);
					if(c == 'c') add_piece(new Knight(Color.white,coluna,linha,1,'c'), coluna, linha);
					if(c == 'b') add_piece(new Bishop(Color.white,coluna,linha,1,'b'),coluna, linha);
					if(c == 'k') {
						String s = "";
						while((c = (char)buffered_reader.read()) != '$') s = s + c;
						King king = new King(Color.white,coluna,linha,1,'k');
						king.nMoves = Integer.parseInt(s);
						add_piece(king, coluna, linha);
					}
					if(c == 'q') add_piece(new Queen(Color.white,coluna,linha,1,'q'), coluna, linha);
				}
				if(c == '2') {
					c = (char)buffered_reader.read();
					if(c == 'r') { 
						String s = "";
						while((c = (char)buffered_reader.read()) != '$') s = s + c;
						Rook rook = new Rook(Color.black,coluna,linha,2,'r');
						rook.nMoves = Integer.parseInt(s);
						add_piece(rook, coluna, linha);
					}
					if(c == 'p') add_piece(new Pawn(Color.black,coluna,linha,2,'p'), coluna, linha);
					if(c == 'c') add_piece(new Knight(Color.black,coluna,linha,2,'c'),coluna, linha);
					if(c == 'b') add_piece(new Bishop(Color.black,coluna,linha,2,'b'), coluna, linha);
					if(c == 'k') {
						String s = "";
						while((c = (char)buffered_reader.read()) != '$') s = s + c;
						King king = new King(Color.black,coluna,linha,2,'k');
						king.nMoves = Integer.parseInt(s);
						add_piece(king, coluna, linha);
					}
					if(c == 'q') add_piece(new Queen(Color.black,coluna,linha,2,'q'), coluna, linha);
				}
				if(linha < 8 && coluna == 8) {
					linha += 1;
					coluna = 0;
				}
				else if (coluna <= 7) {
					coluna++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			return null;
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
				return trash;
			} else {
				return null;
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
					} else {
					}
					
				}
				
				if(p != null) {
					String s = "" + i +""+ j + board.b[i][j].type + board.b[i][j].color.get_color();
					encoded_pieces.add(s);
				}
			}
		}
		return encoded_pieces;
	}
	
	ArrayList<Coordinate> getPossibleMoves(int x, int y) throws CoordinateInvalid{
		Piece p = board.get_piece(x,y);
		if(p == null) {
			return null;
		}
		
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
				
				ArrayList<Piece> enemies;
				if(p.owner == 1) {
					enemies = board.gInfo.p1_foe;
				} else { 
					enemies = board.gInfo.p2_foe;
				}

				ArrayList<Coordinate> savingMoves = new ArrayList<Coordinate>(); 
				boolean saves = false;
				for(Coordinate move: p.moveList) {
					for(Piece enemy: enemies) {
						if(move.get_x() == enemy.getCoord().get_x() && move.get_y() == enemy.getCoord().get_y()) {
							saves = true;
						}
						else if(!enemy.check_move(move)) {
							saves = true;
						} else {
							saves = false;
							break;
						}
					}
					if(saves == true) {
						savingMoves.add(move);
					}

				}
				return savingMoves;
			} else {
				ArrayList<Coordinate> savingMoves = new ArrayList<Coordinate>(); 
				p.move_list();
				ArrayList<Piece> enemies;
				if(p.owner == 1) {
					enemies = board.gInfo.p1_foe;
				} else { 
					enemies = board.gInfo.p2_foe;
				}
				boolean saves = false;
				for(Coordinate move: p.moveList) {
					for(Piece enemy:enemies) {
						if(enemy.blockedMove(enemy, p, move, board.gInfo.getKingPos(p.owner))) {
							saves = true;
						} else {
							saves = false;
							break;
						}
					}
					if(saves == true) {
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

	public void promotePiece(char c) throws CoordinateInvalid {
		Piece p = null;
		for(int i = 0; i < 8; i ++) {
			p = board.get_piece(i, 0);
			if(p instanceof Pawn) {
				break;
			} else {
				p = board.get_piece(i, 7);
				if(p instanceof Pawn) {
					break;
				}
			}
		}
		
		Coordinate coord = p.getCoord();
		int owner = p.get_owner();
		Color color = p.color;
		
		board.remove_piece(coord.get_x(), coord.get_y());
		if(c == 'q') {
			board.add_piece(new Queen(color, coord.get_x(), coord.get_y(), owner, c), coord.get_x(), coord.get_y());
		}
		if(c == 'b') {
			board.add_piece(new Bishop(color, coord.get_x(), coord.get_y(), owner, c), coord.get_x(), coord.get_y());
		}
		if(c == 'c') {
			board.add_piece(new Knight(color, coord.get_x(), coord.get_y(), owner, c), coord.get_x(), coord.get_y());
		}
		if(c == 'r') {
			board.add_piece(new Rook(color, coord.get_x(), coord.get_y(), owner, c), coord.get_x(), coord.get_y());
		}
		
	}
	

	/*
	 * Retorna se não há mais movimentos possíveis para o jogo de nenhum dos jogadores
	 */
	boolean shouldFreeze() throws CoordinateInvalid {
		ArrayList <Piece> pieces1= board.getPlayerPieces(1);
		ArrayList <Piece> pieces2= board.getPlayerPieces(2);
		if(pieces1.size() == 1 && pieces2.size() == 1) {
			return true;
		}
		int sum1 = 0;
		int sum2 = 0;
		for(Piece p: pieces1) {
			sum1 += p.move_list().size();
		}
		for(Piece p: pieces2) {
			sum2 += p.move_list().size();
		}
		if(sum1 == 0 || sum2 == 0) {
			return true;
		}
		return false;
	}
	
	
}
