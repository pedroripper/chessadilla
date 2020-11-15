package model;

class Game {
	private int n_p1, n_p2;
	private int round;
	
	Game(){
		n_p1 = 16;
		n_p2 = 16;
		round = 0;
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
	
	void decrease_p1() {
		n_p1 --;
	}
	
	void decrease_p2() {
		n_p2 --;
	}
}
