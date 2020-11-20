package model;

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
	
	/*
	 * Adiciona observers
	 */
	public void add_observer(Observer o) {
		System.out.print(o.getClass());
		mf.obs.add(o);
		System.out.print("Tamanho do observer ->  " + obs.size() + "\n");
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
	
	public String board_data_to_string(Piece[][] bp) {
		String s = "";
		
		for(Piece[] i: bp) {
			for(Piece j: i) {
				if (j == null) {
					s = s + "-";
				}
				else {
					//char c = (char) j.owner;
					String aux1 = String.valueOf(j.owner);  
					s = s + (aux1 + j.type);
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
//		System.out.print("Realizando movimento \n");
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
		}
		else {
//			System.out.print("Não foi possivel realizar o movimento");
		}
		return true;
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
	
	public ArrayList<String> getInCheckPieces() throws CoordinateInvalid {
		ArrayList<String> checked = new ArrayList<String>();
		Piece k1 = mf.board.get_piece(mf.gInfo.getKingPos(1).x,mf.gInfo.getKingPos(1).y);
		if(k1.type == 'k') {
			String s1;
//			System.out.print("\n Antes de pedir o test ==> "+this.board.gInfo.p1_pieces.size()+ "\n");
			if(((King) k1).testCheck() == 1) {
				s1 = ""+mf.gInfo.getKingPos(1).x+""+mf.gInfo.getKingPos(1).y+"1";
				checked.add(s1);
			} else if(((King) k1).testCheck() == 2) {
				s1 = ""+mf.gInfo.getKingPos(1).x+""+mf.gInfo.getKingPos(1).y+"2";
				checked.add(s1);
			}
		}
		
		Piece k2 = mf.board.get_piece(mf.gInfo.getKingPos(2).x,mf.gInfo.getKingPos(2).y);
		if(k2.type == 'k') {
			String s2;
			if(((King) k2).testCheck() == 1) {
				s2 = ""+mf.gInfo.getKingPos(2).x+""+mf.gInfo.getKingPos(2).y+"1";
				checked.add(s2);
			} else if(((King) k2).testCheck() == 2) {
				s2 = ""+mf.gInfo.getKingPos(2).x+""+mf.gInfo.getKingPos(2).y+"2";
				checked.add(s2);
			}
		}
		return checked;
	}


	public boolean make_castling(int x1, int x2, int y1, int y2) throws CoordinateInvalid {
		Piece p1 = mf.board.get_piece(x1, y1);
		if(p1.Castling(new Coordinate(x1,y1), new Coordinate(x2,y2))) {
			for (Observer ob : mf.obs) {
	            ob.update();
	        }
			return true;
		} 
//		System.out.print("");
		return false;
	}

	
}
