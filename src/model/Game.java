package model;

import java.util.ArrayList;

import Observer.Observer;

class Game {
	private static Game g = null;
	private int n_p1, n_p2;
	private int round;
	private int turn;

	
	Coordinate c_k1;
	Coordinate  c_k2;
	
	Piece p1_foe;
	Piece p2_foe;
	
	boolean isP1inCheck;
	boolean isP2inCheck;
	
	static Game  get_game() {
		if(g != null) {
			return g;
		}
		g = new Game();	
		g.n_p1 =  16;
		g.n_p2 = 16;
		g.round = 0;
		g.turn = 1;
		g.c_k1 = new Coordinate(0,0);
		g.c_k2 = new Coordinate(0,0);
		g.isP2inCheck = false;
		g.isP1inCheck = false;
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

	void setKingCoord(int player, Coordinate c) {
		if(player == 1) {
			g.c_k1 = c;
		} else {
			g.c_k2 = c;
		}
	}

	Coordinate getKingPos(int player) {
		if(player == 1) {
			return g.c_k1;
		} else {
			return g.c_k2;
	
		}
	}
	}
