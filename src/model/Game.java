package model;

import java.util.ArrayList;

import Observer.Observer;

class Game {
	private static Game g = null;
	private int n_p1, n_p2;
	private int round;
	private int turn;
	
	static Game  get_game() {
		if(g == null) {
			g = new Game();	
			g.n_p1 =  16;
			g.n_p2 = 16;
			g.round = 0;
			g.turn = 1;
		}
		return g;
	}
	
	void load_game() {
		
	}
	
	int get_np1() {
		return this.n_p1;
	}
	
	int get_np2() {
		return this.n_p2;
	}
	
	int get_round() {
		return this.round;
	}
	
	int get_turn() {
		return this.turn;
	}
	
	
	
	void toggle_turn() {
		if(turn == 1) {
			turn  = 2;
		} else {
			turn =  1;
		}
	}
	
	void decrease_p1() {
		n_p1 --;
	}
	
	void decrease_p2() {
		n_p2 --;
	}
}
