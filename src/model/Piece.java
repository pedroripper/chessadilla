package model;

import java.util.ArrayList;

import Observer.Observer;

abstract class Piece {
	enum Color{
		white('w'), black('b');
		
		char c;
		private Color(char c) {
			this.c = c;
		}
		
		char get_color() {
			return c;
		}
		
	}
	protected Color color; // Cor do objeto
	private Coordinate coord;
	ArrayList<Coordinate> moveList;
	ArrayList<Coordinate> savingList;
	Board board;
	int owner;
	int nMoves;

	char type;
	public boolean isOut;

	public Piece(Color c, int x, int y, int owner, char type) {
		this.isOut = false;
		this.color =  c;
		this.coord = new Coordinate(x,y);
		this.owner = owner;
		this.board = Board.get_board();
		this.nMoves = 0;
		this.type = type;
	}
	
	
	public boolean check_move(Coordinate c) throws CoordinateInvalid {
		this.move_list();
		for(Coordinate lst: moveList){
			if(lst.get_x() == c.get_x() && lst.get_y() == c.get_y()) {
				return true;
			}
		}
		return false;
	}
	
	ArrayList<Coordinate> getPath(Piece p, Coordinate destiny){
		ArrayList <Coordinate> path = new ArrayList<Coordinate>();
		if(p.type == 'c') {
			return null;
		}
//		Caminho vertical
		if(destiny.get_x() == p.coord.get_x()  && destiny.get_y() != p.coord.get_y()) {
			if(destiny.get_y() > p.coord.get_y()) {
				for(int i = p.coord.get_y(); i < destiny.get_y(); i++) {
					path.add(new Coordinate(destiny.get_x(),i));
				}
				return path;
			} 
			else {
				for(int i = destiny.get_y(); i < p.coord.get_y(); i++) {
					path.add(new Coordinate(destiny.get_x(),i));
				}
				return path;
			} 
		}
//		Caminho horizontal
		else if(destiny.get_x() != p.coord.get_x()  && destiny.get_y() == p.coord.get_y()) {
			if(destiny.get_x() > p.coord.get_x()) {
				for(int i = p.coord.get_x(); i < destiny.get_x(); i++) {
					path.add(new Coordinate(i,destiny.get_y()));
				}
				return path;
			} 
			else {
				for(int i = destiny.get_x(); i < p.coord.get_x(); i++) {
					path.add(new Coordinate(i,destiny.get_y()));
				}
				return path;
			} 
		}
//		Caminho diagonal superior direito
		else if(destiny.get_x() > p.coord.get_x()  && destiny.get_y() > p.coord.get_y()) {
			int j = p.coord.get_y();
			for(int i = p.coord.get_x(); i < destiny.get_x(); i++) {
				path.add(new Coordinate(i,j));
				j++;
			}
			return path;
		}
//		Caminho diagonal superior esquerdo
		else if(destiny.get_x() < p.coord.get_x()  && destiny.get_y() > p.coord.get_y()) {
			int j = p.coord.get_y();
			for(int i = p.coord.get_x(); i > destiny.get_x(); i--) {
				path.add(new Coordinate(i,j));
				j++;
			}
			return path;
		}
//		Caminho diagonal inferior esquerdo
		else if(destiny.get_x() < p.coord.get_x()  && destiny.get_y() < p.coord.get_y()) {
			int j = p.coord.get_y();
			for(int i = p.coord.get_x(); i > destiny.get_x(); i--) {
				path.add(new Coordinate(i,j));
				j--;
			}
			return path;
		}
//		Caminho  diagonal inferior direito
		else if(destiny.get_x() > p.coord.get_x()  && destiny.get_y() < p.coord.get_y()) {
			int j = p.coord.get_y();
			for(int i = p.coord.get_x(); i < destiny.get_x(); i++) {
				path.add(new Coordinate(i,j));
				j--;
			}
			return path;
		}
		
		return null;
	}
	
	public boolean blockedMove(Piece foe,Piece savior, Coordinate c, Coordinate destiny) throws CoordinateInvalid  {
		ArrayList<Coordinate> path = getPath(foe,destiny);
		savior.move_list();
		for(Coordinate coord: path) {
			
			if(c.get_x() == coord.get_x() && c.get_y()  == coord.get_y()) {
				
				return true;
			}
		}
		return false;
	}
	
	public int get_owner() {
		return this.owner;
	}
	
	public abstract ArrayList<Coordinate> move_list() throws CoordinateInvalid;
	
	
	/*
	movimenta a piece
	*/
	public boolean move(Coordinate c) throws CoordinateInvalid{
		Board board = this.board;
		if(check_move(new Coordinate(c.x,c.y))) {
			board = Board.get_board();
			//			Pode realizar o movimento
			
			Piece p  =  this;
			board.remove_piece(this.coord.x, this.coord.y);
			p.coord = c;
			p.nMoves ++;

			board.add_piece(p, c.x, c.y);

			if(p instanceof Queen) {
				System.out.print("Olha oq  temos aqui  \n");
				System.out.print(p.coord.x +  "  "+ p.coord.y +"\n");
				System.out.print(this.board.get_piece(p.coord.x, p.coord.y));
				System.out.print((p != null) + "\n");

			}
			if(this instanceof King) {
				if(this.owner == 1) {
					this.board.gInfo.c_k1 = c;
				} else {
					this.board.gInfo.c_k2 =c;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	
	void setCoord(Coordinate c) {
		this.coord = c;
	}
	
	Coordinate getCoord() {
		return this.coord;
	}


}
