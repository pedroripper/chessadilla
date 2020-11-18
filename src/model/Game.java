package model;

import java.util.ArrayList;

import Observer.Observer;

class Game {
	private static Game g = null;
	private int n_p1, n_p2;
	private int round;
	private int turn;

	
	/*
	 * Pawn => 0 - 7
	 * Rook => 8,9
	 * Knight => 10,11
	 *  Bishop => 12,13
	 *  Queen => 14
	 *  King => 15
	 */
	ArrayList <Piece> p1_pieces;
	ArrayList <Piece> p2_pieces;
	
	ArrayList <Coordinate> mustMove_p1;
	ArrayList <Coordinate> mustMove_p2;
	
	
	
	static Game  get_game() {
		if(g != null) {
			return g;
		}
		g = new Game();	
		g.p1_pieces = new ArrayList<Piece>();
		g.p2_pieces = new ArrayList<Piece>();
		g.mustMove_p1 = new ArrayList<Coordinate>();
		g.mustMove_p2 = new ArrayList<Coordinate>();
		g.n_p1 =  16;
		g.n_p2 = 16;
		g.round = 0;
		g.turn = 1;
		return g;
	}
	
	void load_game() {
		
	}
	
	int get_np1() {
		return g.n_p1;
	}
	
	int get_np2() {
		return g.n_p2;
	}
	
	int get_round() {
		return g.round;
	}
	
	int get_turn() {
		return g.turn;
	}
	
	
	
	void toggle_turn() {
		if(turn == 1) {
			g.turn  = 2;
		} else {
			g.turn =  1;
		}
	}
	
	void decrease_p1() {
		g.n_p1 --;
	}
	
	void decrease_p2() {
		g.n_p2 --;
	}
	
	
	void addPiece(Piece p) {
		if(p.owner == 1) {
			g.p1_pieces.add(p);
		} else {
			g.p2_pieces.add(p);
		}
	}
	
	ArrayList<Coordinate> getMustMoves(int player){
		if(player == 1) {
			return g.mustMove_p1;
		} else {
			return g.mustMove_p2;
		}
	}
	
//	void updatePieceList(Piece pOld, Piece pNew) {
//		if(pOld.owner == 1) {
//			int i  = p1_pieces.indexOf(pOld);
//			p1_pieces.set(i, pNew);
//		}
//		if(pOld.owner == 2) {
//			int i  = p1_pieces.indexOf(pOld);
//			p1_pieces.set(i, pNew);
//		}
//	}
	
	Coordinate getKingPos(int player) {
		if(player == 1) {
			for(Piece p: p1_pieces) {
				if(p instanceof King) {
					return p.coord;
				}
			}
		} else {
			for(Piece p: p2_pieces) {
				if(p instanceof King) {
					return p.coord;
				}
			}		
		}
		return null;
	}
	}
