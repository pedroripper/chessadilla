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
		board = Board.get_board();
		if(check_move(new Coordinate(c.x,c.y))) {

			if(this.type == 'k') {
				if(this.coord.get_x() +2 == c.get_x()|| this.coord.get_x() -4 == c.get_x()) {
					if(this.Castling(this.coord, c)) {
						return true;
					}
				}
				King k = (King) this;
				Piece e = k.enemy_inline(new Coordinate(c.get_x(),c.get_y()));
				if(e != null) {
						return false;
				}
				else if(board.get_piece(c.get_x(),c.get_y()) == null) {

					if(board.get_piece(c.get_x(),c.get_y()) instanceof Piece) {
						board.remove_piece(c.get_x(),c.get_y());
					}

					board.add_piece(this, c.get_x(),c.get_y());
					board.remove_piece(this.getCoord().x, this.getCoord().y);
					this.setCoord(c);

					board.gInfo.setKingCoord(this.owner, c);
					
					return true;
				}
				else if(board.get_piece(c.get_x(),c.get_y()).owner != this.owner || e == null) {
//					Pode realizar o movimento

					if(board.get_piece(c.get_x(),c.get_y()) instanceof Piece) {
						board.remove_piece(c.get_x(),c.get_y());
					}

					board.add_piece(this, c.get_x(),c.get_y());
					board.remove_piece(this.getCoord().x, this.getCoord().y);
					this.setCoord(c);

					board.gInfo.setKingCoord(this.owner, c);
					
					return true;
				}
				
				
			} else {
				

			//			Pode realizar o movimento
			
			Piece p  =  this;
			board.remove_piece(this.coord.x, this.coord.y);
			p.coord = c;
			p.nMoves ++;

			board.add_piece(p, c.x, c.y);
			
			if(c.get_y() == 0 || c.get_y() == 7) {
				if(p instanceof Pawn) {
					this.board.model.togglePromotion();
				}
			}

			return true;
			}
		}

		return false;
	}
	
	boolean Castling(Coordinate c1, Coordinate c2) throws CoordinateInvalid {
		Piece p1 = this.board.get_piece(c1.get_x(), c1.get_y());
		Piece p2 = this.board.get_piece(c2.get_x(), c2.get_y());
		King k;
		Rook r;
		if(p1 instanceof King) {
			k = ((King)p1);
			r  = ((Rook)p2);
		} else {
			k = ((King)p2);
			r  = ((Rook)p1);
		}
		if(k.getCoord().get_x() > r.getCoord().get_x()) {
			Piece pTemp1 = k;
			board.remove_piece(k.getCoord().get_x(), k.getCoord().get_y());
			pTemp1.coord = new Coordinate(2,pTemp1.getCoord().get_y());
			pTemp1.nMoves ++;
			board.add_piece(pTemp1, 2, pTemp1.getCoord().get_y());
			
			Piece pTemp2 = r;
			board.remove_piece(r.getCoord().get_x(), r.getCoord().get_y());
			pTemp2.coord = new Coordinate(3,pTemp2.getCoord().get_y());
			pTemp2.nMoves ++;
			board.add_piece(pTemp2, 3, pTemp2.getCoord().get_y());
			
			if(this.owner == 1) {
				this.board.gInfo.c_k1 = new Coordinate(2, pTemp1.getCoord().get_y());
			} else {
				this.board.gInfo.c_k2 = new Coordinate(2, pTemp1.getCoord().get_y());
			}
			return true;
		} else {
			Piece pTemp1 = k;
			board.remove_piece(k.getCoord().get_x(), k.getCoord().get_y());
			pTemp1.coord = new Coordinate(6,pTemp1.getCoord().get_y());
			pTemp1.nMoves ++;
			board.add_piece(pTemp1, 6, pTemp1.getCoord().get_y());
			
			Piece pTemp2 = r;
			board.remove_piece(r.getCoord().get_x(), r.getCoord().get_y());
			pTemp2.coord = new Coordinate(5,pTemp2.getCoord().get_y());
			pTemp2.nMoves ++;
			board.add_piece(pTemp2, 5, pTemp2.getCoord().get_y());
			
			if(this.owner == 1) {
				this.board.gInfo.c_k1 = new Coordinate(6, pTemp1.getCoord().get_y());
			} else {
				this.board.gInfo.c_k2 = new Coordinate(6, pTemp1.getCoord().get_y());
			}
			return true;
		}
//		return false;
	}
	
	
	
	void setCoord(Coordinate c) {
		this.coord = c;
	}
	
	Coordinate getCoord() {
		return this.coord;
	}


}
