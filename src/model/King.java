package model;

import java.util.ArrayList;

class King extends Piece{

	public King(Color c, int x, int y, int o, char type) {
		super(c, x, y, o, type);
//		gInfo.updateKingPos(new Coordinate(4,0), 1);
//		gInfo.updateKingPos(new Coordinate(4,7), 2);
		nMoves = 0;
	}
	
	
	private Piece enemy_inline(Coordinate c) throws CoordinateInvalid {
		if(this.owner == 1) {
			ArrayList<Piece> enemies = this.board.getPlayerPieces(2);
			for(Piece e: enemies) {
				e.move_list();
				if(e.check_move(c)) {
					return e;
				}
			}
			return null;
		} else {
			ArrayList<Piece> enemies = this.board.getPlayerPieces(1);
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
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
			}
		}
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
			Piece p = board.get_piece(this.getCoord().x, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y)) {
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
			Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
			}
		}
		if(board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
			Piece p = board.get_piece(this.getCoord().x, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
			}
		}
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
			Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
			}
		}
		if(canCastling(new Coordinate(0, this.getCoord().get_y()))) {
			lst.add(new Coordinate(0, this.getCoord().get_y()));
		}
		if(canCastling(new Coordinate(7, this.getCoord().get_y()))) {
			lst.add(new Coordinate(7, this.getCoord().get_y()));
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
		int nMoves = mlist.size();
		int blockedMoves = 0;
		
		enemy.move_list();
		for(Coordinate esc: mlist) {
			if(enemy.check_move(esc)) {
				blockedMoves ++;
			} 
		}
		
		if(blockedMoves == nMoves) {
			return 2;
		}
		else {
			return 1;
		}
	
	}
	
	
	int testCheck() throws CoordinateInvalid {
//		this.board.gInfo.p1_foe = null;
//		this.board.gInfo.p2_foe = null;
		int savingMoves = 0;
		Piece enemy  = this.enemy_inline(this.getCoord());
		if(enemy != null){
			if(this.get_owner() == 1) {
				this.board.gInfo.isP1inCheck = true;
			} else {
				this.board.gInfo.isP2inCheck = true;
			}
			if(this.owner == 1) {
				this.board.gInfo.p1_foe = enemy;
			} else {
				this.board.gInfo.p2_foe = enemy;
			}
			for(Piece p: this.board.getPlayerPieces(this.owner)) {
				if(p == this) {
					continue;
				}
				for(Coordinate pc: p.move_list()) {
					if(enemy.blockedMove(enemy, p, pc, this.getCoord())) {
						savingMoves ++;
					}
				}
			}
			if(savingMoves > 0) {
				return 1;
			} else  {
				return testCheckMate(enemy);
			}
		}
		if(this.get_owner() == 1) {
			this.board.gInfo.isP1inCheck = false;
		} else {
			this.board.gInfo.isP2inCheck = false;
		}
			
		return 0;
	}

	
	boolean canCastling(Coordinate c) throws CoordinateInvalid {
		if(!(this.type == 'k')) {
			System.out.print("Cai aqui 1\n");
			return false;
		}
		if(this.nMoves > 0) {
			System.out.print("Cai aqui 2\n");
			return false;
		}
		Piece rook = this.board.get_piece(c.get_x(), c.get_y());
		if(!(rook instanceof Rook) || rook.nMoves > 0) {
			return false;
		}
		System.out.print("Cai aqui 3\n");
		int xini = this.getCoord().get_x();
		int yini = this.getCoord().get_y();
		int xfim = rook.getCoord().get_x();
		if(xfim > xini) {
			xini ++;
			while(xini < xfim) {
				if(this.board.get_piece(xini, yini) != null) {
					return false;
				}
				xini ++;
			}
		} else {
			xini --;
			while(xini > xfim) {
				if(this.board.get_piece(xini, yini) != null) {
					return false;
				}
				xini --;
			}
		}
		return true;	
	}

}
