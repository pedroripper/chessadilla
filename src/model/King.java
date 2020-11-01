package model;

import java.util.ArrayList;

class King extends Piece{

	public King(Color c, int x, int y) {
		super(c, x, y);
	}
	
	
	private boolean enemy_inline(Coordinate c) throws CoordinateInvalid {
		// vertical check
		for (int i = 0; i < 8; i++) {
			Piece p = board.get_piece(c.x, i);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
        }
		for (int j = 0; j < 8; j++) {
			Piece p = board.get_piece(j, c.y);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
        }
		// Diagonal check
		int fx = c.x;
		for (int k = c.y; k < 8; k++) {
			if(fx >= 8) {
				break;
			}
			Piece p = board.get_piece(fx, k);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
			fx +=1;
        }
		fx = c.x;
		for (int l = c.y; l >= 0; l--) {
			if(fx >= 8) {
				break;
			}
			Piece p = board.get_piece(fx, l);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
			fx +=1;
        }
		fx = c.x;
		for (int k = c.y; k < 8; k++) {
			if(fx < 0) {
				break;
			}
			Piece p = board.get_piece(fx, k);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
			fx -=1;
        }
		fx = c.x;
		for (int k = c.y; k >= 0; k--) {
			if(fx < 0) {
				break;
			}
			Piece p = board.get_piece(fx, k);
			if(p != null && p.color != this.color) {
				p.move_list();
				if(p.check_move(c)) {
					return true;
				}
			}
			fx -=1;
        }
		// knight check
		Piece knight;
		knight = board.get_piece(c.x+1, c.y+2);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x-1, c.y+2);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x+1, c.y-2);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x-1, c.y-2);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x+2, c.y+1);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x+2, c.y-1);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x-2, c.y-1);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		knight = board.get_piece(c.x-2, c.y+1);
		if(knight != null && knight.color != this.color && knight instanceof Knight) {
			knight.move_list();
			if(knight.check_move(c)) {
				return true;
			}
		}
		return false;
	}


	
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		if(board.verify_xy(this.coord.x+1, this.coord.y)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x+1, this.coord.y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x+1, this.coord.y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x+1, this.coord.y));
			}
		}
		if(board.verify_xy(this.coord.x+1, this.coord.y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x+1, this.coord.y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x+1, this.coord.y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x+1, this.coord.y+1));
			}
		}
		if(board.verify_xy(this.coord.x, this.coord.y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x, this.coord.y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x, this.coord.y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x, this.coord.y+1));
			}
		}
		if(board.verify_xy(this.coord.x-1, this.coord.y+1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x-1, this.coord.y+1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x-1, this.coord.y+1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x-1, this.coord.y+1));
			}
		}
		if(board.verify_xy(this.coord.x-1, this.coord.y)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x-1, this.coord.y);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x-1, this.coord.y));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x-1, this.coord.y));
			}
		}
		if(board.verify_xy(this.coord.x-1, this.coord.y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x-1, this.coord.y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x-1, this.coord.y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x-1, this.coord.y-1));
			}
		}
		if(board.verify_xy(this.coord.x, this.coord.y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x, this.coord.y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x, this.coord.y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x, this.coord.y-1));
			}
		}
		if(board.verify_xy(this.coord.x+1, this.coord.y-1)) {
			// movimento no L em pe superior direita
			Piece p = board.get_piece(this.coord.x+1, this.coord.y-1);
			if(p != null && p.color != this.color) { 
				lst.add(new Coordinate(this.coord.x+1, this.coord.y-1));
			} 
			else if(p == null) {
				lst.add(new Coordinate(this.coord.x+1, this.coord.y-1));
			}
		}
		this.moveList = lst;
		return lst;
	}
	
	public boolean move(int x, int y) throws CoordinateInvalid{
		if(check_move(new Coordinate(x,y))) {
			if(enemy_inline(new Coordinate(x,y))) {
//				Pode realizar o movimento
				if(board.get_piece(x, y) instanceof Piece) {
					board.remove_piece(x, y);
				}
				board.add_piece(this, x, y);
				board.remove_piece(this.coord.x, this.coord.y);
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}

}
