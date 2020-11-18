package model;

import java.util.ArrayList;

class King extends Piece{

	public King(Color c, int x, int y, int o) {
		super(c, x, y, o);
//		gInfo.updateKingPos(new Coordinate(4,0), 1);
//		gInfo.updateKingPos(new Coordinate(4,7), 2);
		type = 'k';
	}
	
	
	private Piece enemy_inline(Coordinate c) throws CoordinateInvalid {
		if(this.owner == 1) {
			ArrayList<Piece> enemies = this.board.gInfo.p2_pieces;
			for(Piece e: enemies) {
				e.move_list();
				if(e.check_move(c)) {
					return e;
				}
			}
			return null;
		} else {
			ArrayList<Piece> enemies = this.board.gInfo.p1_pieces;
			for(Piece e: enemies) {
				e.move_list();
				if(e.check_move(c)) {
					return e;
				}
			}
			return null;
		}

	}


	
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
			}
		}
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
			}
		}
		if(board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
			}
		}
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
			}
		}
		this.moveList = lst;
		return lst;
	}
	
	public boolean move(int x, int y) throws CoordinateInvalid{
		if(check_move(new Coordinate(x,y))) {
			if(enemy_inline(new Coordinate(x,y))  == null) {
//				Pode realizar o movimento
				if(board.get_piece(x, y) instanceof Piece) {
					board.remove_piece(x, y);
				}
				board.add_piece(this, x, y);
				board.remove_piece(this.getCoord().x, this.getCoord().y);
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	int testCheckMate(Piece enemy) throws CoordinateInvalid {
		ArrayList<Coordinate> mlist = this.move_list();
		ArrayList<Coordinate> saveMove = new ArrayList<Coordinate>();
		int nMoves = mlist.size();
		int blockedMoves = 0;
		
		enemy.move_list();
		ArrayList<Coordinate> sini = new ArrayList<Coordinate>();
		sini.add(this.getCoord());
		for(Coordinate esc: mlist) {
			if(enemy.check_move(esc)) {
				blockedMoves ++;
			} else {
				sini.add(esc);
			}
		}
		
		if(blockedMoves == nMoves) {
			return 2;
		}
		else {
			saveMove = sini;
			if(this.get_owner()  == 1) {
				this.board.gInfo.mustMove_p1 = saveMove;
			} else {
				this.board.gInfo.mustMove_p2 = saveMove;
			}
			
			return 1;
		}
	
	}
	
	
	int testCheck() throws CoordinateInvalid {
		ArrayList<Coordinate> saveMove = new ArrayList<Coordinate>();
		int blockedMoves = 0;
		Piece enemy  = this.enemy_inline(this.getCoord());
		if(enemy != null){
			if(this.get_owner()  == 1) {
				for(Piece p: this.board.gInfo.p1_pieces) {
					if(p == this) {
						continue;
					}
					ArrayList<Coordinate> sini = new ArrayList<Coordinate>();
					ArrayList<Coordinate> sfim = new ArrayList<Coordinate>();
					sini.add(p.getCoord());
					for(Coordinate pc: p.move_list()) {
						if(enemy.blockedMove(enemy, p, pc, this.getCoord())) {
							sfim.add(p.getCoord());
						}
					}
					sini.addAll(sfim);
					if(sini.size() > 2) {
						saveMove.addAll(sini);
					}
				}
				if(saveMove.size() != 0) {
					this.board.gInfo.mustMove_p1 = saveMove;
					return 1;
				} else  {
					return testCheckMate(enemy);
				}
			}
			else {
				for(Piece p: this.board.gInfo.p2_pieces) {
					if(p == this) {
						continue;
					}
					ArrayList<Coordinate> sini = new ArrayList<Coordinate>();
					ArrayList<Coordinate> sfim = new ArrayList<Coordinate>();
					sini.add(p.getCoord());
					for(Coordinate pc: p.move_list()) {
						if(enemy.blockedMove(enemy, p, pc, this.getCoord())) {
							sfim.add(p.getCoord());
						}
					}
					sini.addAll(sfim);
					if(sini.size() > 1) {
						saveMove.addAll(sini);
					}
				}
				if(saveMove.size() != 0) {
					return 1;
				} else  {
					return testCheckMate(enemy);
				}
			}
		}
		return 0;
	}


}
