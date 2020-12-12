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
	
	ArrayList<Piece> p1_foe;
	ArrayList<Piece> p2_foe;
	
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
		g.isP1inCheck = false;
		g.isP2inCheck = false;
		return g;
	}
	
	void load_game() {
		
	}
	
	int get_np1() {
		return g.n_p1;
	}
	
	void set_np1(int n) {
		g.n_p1 = n;
	}
	
	int get_np2() {
		return g.n_p2;
	}
	
	void set_np2(int n) {
		g.n_p2 = n;
	}
	
	int get_round() {
		return g.round;
	}
	
	void set_round(int n) {
		g.round = n;
	}
	
	int get_turn() {
		return g.turn;
	}
	
	void set_turn(int n) {
		g.turn = n;
	}
	
	Coordinate get_c_k1() {
		return g.c_k1;
	}
	
	void set_c_k1(Coordinate c) {
		g.c_k1 = c;
	}
	
	Coordinate get_c_k2() {
		return g.c_k2;
	}
	
	void set_c_k2(Coordinate c) {
		g.c_k2 = c;
	}
	
	boolean get_isP1inCheck() {
		return g.isP1inCheck;
	}
	
	void set_isP1inCheck(boolean b) {
		g.isP1inCheck = b;
	}
	
	boolean get_isP2inCheck() {
		return g.isP2inCheck;
	}
	
	void set_isP2inCheck(boolean b) {
		g.isP2inCheck = b;
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
