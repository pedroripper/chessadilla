package model;

import java.io.FileReader;
import java.util.ArrayList;

import Observer.Observer;

public class ModelFacade {
	private static ModelFacade mf=  null;
	private ArrayList<Observer> obs = null;
	Board board = null;
	private Game gInfo = null;
	
	public static ModelFacade getModelFacade() {
		if(mf != null) {
			return  mf;
		}
		mf = new ModelFacade();
		mf.gInfo = Game.get_game();
		mf.board = Board.get_board();
		mf.obs = new ArrayList<Observer>();
		return mf;
	}
	
	
	/*
	 * newGame: popula a Board com as pecas
	 */
	public void newGame() throws CoordinateInvalid {
		mf.board.init_board();
	}
	
	public void oldGame(FileReader file) throws CoordinateInvalid {
		mf.board.load_board(file);
		for (Observer ob : mf.obs) {
            ob.update();
        }
	}
	
	/*
	 * Adiciona observers
	 */
	public void add_observer(Observer o) {
		mf.obs.add(o);
	}
	
	/*
	 * Remove observers
	 */
	public void remove_observer(Observer o) {
		mf.obs.remove(o);
	}
	
	/*
	 * chama a board para enviar as  suas pecas
	 */
	public ArrayList<String> pieces_to_display() throws CoordinateInvalid {
		return mf.board.send_pieces();
	}

	public Piece[][] get_board_data(){
		Board board = Board.get_board();
		return board.b;
	}
	
	/*
	 * Parser da board para uma string
	 */
	public String board_data_to_string(Piece[][] bp) {
		String s = "";
		int col, linha;
		s = String.valueOf(mf.gInfo.get_np1()) + "\n" +
			String.valueOf(mf.gInfo.get_np2()) + "\n" +
			String.valueOf(mf.gInfo.get_round()) + "\n" +
			String.valueOf(mf.gInfo.get_turn()) + "\n" +
			String.valueOf(mf.gInfo.get_c_k1().get_x()) + " " +
			String.valueOf(mf.gInfo.get_c_k1().get_y()) + "\n" +
			String.valueOf(mf.gInfo.get_c_k2().get_x()) + " " +
			String.valueOf(mf.gInfo.get_c_k2().get_y()) + "\n" +
			(mf.gInfo.get_isP1inCheck() ? "1" : "0") + "\n" +
			(mf.gInfo.get_isP2inCheck() ? "1" : "0") + "\n";
		for(linha = 0;linha < 8; linha++) {
			for(col = 0;col < 8; col++) {
				if (bp[col][linha] == null) {
					s = s + "-";
				}
				else {
					String aux1 = String.valueOf(bp[col][linha].owner);
					s = s + (aux1 + bp[col][linha].type);
					if (bp[col][linha].type == 'k' || bp[col][linha].type == 'r') {
						s = s + String.valueOf(bp[col][linha].nMoves) + "$";
					}
				}
			}
			s = s + "\n";
		}

		return s;
		
	}
	

	
	/*
	 * possible_moves: retorna um array de Coordinate mostrando os 
	 * movimentos possiveis para a piece
	 */
	public ArrayList<Coordinate> possible_moves(int x, int y) throws CoordinateInvalid {
		return mf.board.getPossibleMoves(x,y);
	}
	
	/*
	 * make_move: realiza o movimento da piece da coordenada c1 para a c2 se for
	 * possivel
	 */
	public boolean make_move(int x1, int y1, int x2, int y2) throws CoordinateInvalid {
		Coordinate c1 = new Coordinate(x1,y1);
		Coordinate c2 = new Coordinate(x2,y2);
		Piece p = mf.board.get_piece(c1.x, c1.y);
		Piece p2 =  mf.board.get_piece(c2.x, c2.y);
		if(p == null) {
			return false;
		}
	

		if(p.move(c2) == true) {
			for (Observer ob : mf.obs) {
	            ob.update();
	        }
			return true;
		}
		else {
			for (Observer ob : mf.obs) {
	            ob.update();
	        }
			return false;
			}
	
	}
	
	/*
	 * Pega de quem é a vez de jogar
	 */
	public int get_turn() {
		return  mf.gInfo.get_turn();
	}
	
	public void toggle_turn() {
		mf.gInfo.toggle_turn();
	}
	
	/*
	 * Pega quem é o dono da peca em (x,y)
	 */
	public int get_owner(int x, int y) throws CoordinateInvalid {
		return mf.board.get_piece(x, y).owner;
	}
	
	/*
	 * Retorna o(s) rei(s) em cheque
	 */
	public ArrayList<String> getInCheckPieces() throws CoordinateInvalid {
		ArrayList<String> checked = new ArrayList<String>();
		
		Coordinate  king1Pos = mf.gInfo.getKingPos(1);
		Coordinate king2Pos =  mf.gInfo.getKingPos(2);
		

		Piece k1 = mf.board.get_piece(king1Pos.x,king1Pos.y);
		
		

		if(k1.type == 'k') {
			String s1;
			int test = ((King) k1).testCheck();
			if(test == 1) {
				s1 = ""+king1Pos.x+""+king1Pos.y+"1";
				checked.add(s1);
			} else if(test == 2) {
				s1 = ""+king1Pos.x+""+king1Pos.y+"2";
				checked.add(s1);
			}
		}
		Piece k2 = mf.board.get_piece(king2Pos.x,king2Pos.y);
		if(k2.type == 'k') {
			String s2;
			int test = ((King) k2).testCheck();
			if(test == 1) {
				s2 = ""+king2Pos.x+""+king2Pos.y+"1";
				checked.add(s2);
			} else if(test == 2) {
				s2 = ""+king2Pos.x+""+king2Pos.y+"2";
				checked.add(s2);
			}
		}
		return checked;
	}

	
	/*
	 * Realiza o roque
	 */
	public boolean make_castling(int x1, int x2, int y1, int y2) throws CoordinateInvalid {
		Piece p1 = mf.board.get_piece(x1, y1);
		if(p1.Castling(new Coordinate(x1,y1), new Coordinate(x2,y2))) {
			for (Observer ob : mf.obs) {
	            ob.update();
	        }
			return true;
		} 
		return false;
	}

	/*
	 * promove a peça
	 */
	public void promote(char c) throws CoordinateInvalid {
		mf.board.promotePiece(c);
		for (Observer ob : mf.obs) {
            ob.update();
        }
		
	}

	
	public void togglePromotion() {
		for (Observer ob : mf.obs) {
            ob.pawnPromotion();
        }
		
	}
	
	public boolean shouldFreeze() throws CoordinateInvalid {
		return mf.board.shouldFreeze();
	}

	}

	

