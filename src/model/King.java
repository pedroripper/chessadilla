package model;

import java.util.ArrayList;

class King extends Piece{

	public King(Color c, int x, int y, int o, char type) {
		super(c, x, y, o, type);
//		gInfo.updateKingPos(new Coordinate(4,0), 1);
//		gInfo.updateKingPos(new Coordinate(4,7), 2);
		nMoves = 0;
	}
	
	/*
	 * Checa por pecas inimigas na horizontal 
	 */
	ArrayList<Piece> enemy_horizontal(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
//		left
		for(int i = c.get_x(); i >= 0; i --) {
			Piece p = board.get_piece(i, c.get_y());
			if(p != null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Rook) {
						enemies.add(p);
					}
					else if(Math.abs(i) == 1 && p instanceof King) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
//		right
		for(int j = c.get_x(); j < 8; j ++) {
			Piece p = board.get_piece(j, c.get_y());
			if(p != null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Rook) {
						enemies.add(p);
					}
					else if(Math.abs(j) == 1 && p instanceof King) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		return enemies;
	}
	
	/*
	 * Checa por pecas na vertical
	 */
	ArrayList<Piece> enemy_vertical(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
		for(int j = c.get_y(); j < 8; j ++) {
			Piece p = board.get_piece(c.get_x(),j);
			if(p != null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Rook) {
						enemies.add(p);
					}
					else if(Math.abs(j) == 1 && p instanceof King) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		for(int j = c.get_y(); j >= 0; j --) {
			Piece p = board.get_piece(c.get_x(),j);
			if(p != null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Rook) {
						enemies.add(p);
					}
					else if(Math.abs(j) == 1 && p instanceof King) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		return enemies;
	}
	
	/*
	 * Checa por andar de cavalo
	 */
	ArrayList<Piece> enemy_knight(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
		Piece p;
		if(c.get_x()+1 < 8 && c.get_x()+1 >= 0) {
			if(c.get_y()+2 < 8 && c.get_y()+2 >= 0) {
				p = board.get_piece(c.get_x()+1, c.get_y()+2);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()-1 < 8 && c.get_x()-1 >= 0) {
			if(c.get_y()+2 < 8 && c.get_y()+2 >= 0) {
				p = board.get_piece(c.get_x()-1, c.get_y()+2);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()-1 < 8 && c.get_x()-1 >= 0) {
			if(c.get_y()-2 < 8 && c.get_y()-2 >= 0) {
				p = board.get_piece(c.get_x()-1, c.get_y()-2);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
		}
		}
		if(c.get_x()+1 < 8 && c.get_x()+1 >= 0) {
			if(c.get_y()-2 < 8 && c.get_y()-2 >= 0) {
				p = board.get_piece(c.get_x()+1, c.get_y()-2);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()+2 < 8 && c.get_x()+2 >= 0) {
			if(c.get_y()+1 < 8 && c.get_y()+1 >= 0) {
				p = board.get_piece(c.get_x()+2, c.get_y()+1);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()+2 < 8 && c.get_x()+2 >= 0) {
			if(c.get_y()-1 < 8 && c.get_y()-1 >= 0) {
				p = board.get_piece(c.get_x()+2, c.get_y()-1);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()-2 < 8 && c.get_x()-2 >= 0) {
			if(c.get_y()+1 < 8 && c.get_y()+1 >= 0) {
				p = board.get_piece(c.get_x()-2, c.get_y()+1);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		if(c.get_x()-2 < 8 && c.get_x()-2 >= 0) {
			if(c.get_y()-1 < 8 && c.get_y()-1 >= 0) {
				p = board.get_piece(c.get_x()-2, c.get_y()-1);
				if(p!= null && p.get_owner()!= this.get_owner()) {
					if(p instanceof Knight) {
						enemies.add(p);
					}
				}
			}
		}
		
		return enemies;
	}
	
	/*
	 * Checa por pecas na diagonal
	 */
	ArrayList<Piece> enemy_diagonal(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();

//		diag sup
		for(int i = 0; c.get_x()+i < 8 && c.get_y()+i < 8; i ++) {
			Piece p = board.get_piece(c.get_x()+i, c.get_y()+i);
			if(p!= null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Bishop) {
						enemies.add(p);
					}
					else if(i == 1 && (p instanceof King || (p instanceof Pawn && p.get_owner() == 1))) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
				
			}
		}
		for(int i = 0; c.get_x()-i >= 0 && c.get_y()+i < 8; i ++) {
			Piece p = board.get_piece(c.get_x()-i, c.get_y()+i);
			if(p!= null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Bishop) {
						enemies.add(p);
					}
					else if(i == 1 && (p instanceof King || (p instanceof Pawn && p.get_owner() == 1))) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		for(int i = 0; c.get_x()-i >= 0 && c.get_y()-i >= 0; i ++) {
			Piece p = board.get_piece(c.get_x()-i, c.get_y()-i);
			if(p!= null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Bishop) {
						enemies.add(p);
					}
					else if(i == 1 && (p instanceof King || (p instanceof Pawn && p.get_owner() == 2))) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		for(int i = 0; c.get_x()+i < 8 && c.get_y()-i >= 0; i ++) {
			Piece p = board.get_piece(c.get_x()+i, c.get_y()-i);
			if(p!= null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Bishop) {
						enemies.add(p);
					}
					else if(i == 1 && (p instanceof King || (p instanceof Pawn && p.get_owner() == 2))) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
		}
		return enemies;
	}
	
	
	ArrayList<Piece> enemy_inline(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
//		int x = c.get_x();
//		int y = c.get_y();
		
		System.out.print("1\n");

		enemies.addAll(enemy_vertical(c));
		System.out.print("2\n");

		enemies.addAll(enemy_horizontal(c));
		System.out.print("3\n");

		enemies.addAll(enemy_diagonal(c));
		System.out.print("4\n");

		enemies.addAll(enemy_knight(c));
		System.out.print("5\n");

		
		return enemies;
		

	}
	
	boolean checkForQRB(ArrayList<Piece> enemies, char mode, Coordinate c) {
		for(Piece enemy: enemies) {
			if(mode == 'h') {
				if(enemy instanceof Queen || enemy instanceof Rook) {
					if(enemy.getCoord().get_y() == c.get_y()) {
						return true;
					}
				}
			}
			if(mode == 'v') {
				if(enemy instanceof Queen || enemy instanceof Rook) {
					if(enemy.getCoord().get_x() == c.get_x()) {
						return true;
					}
				}
			}
			if(mode == 'd' || mode == 'e') {
				if(enemy instanceof Queen || enemy instanceof Bishop) {
					if(Math.abs(enemy.getCoord().get_x()-c.get_x()) == Math.abs(enemy.getCoord().get_y()-c.get_y())) {
						return true;
					}
				}
			}
			
			if(enemy instanceof Queen || enemy instanceof Rook || enemy instanceof Bishop) {
				
			}
		}
		return false;
	}



	/*
	 * Checa os movimentos possiveis para o rei
	 */
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		boolean blockedHor = false;
		boolean blockedVertical = false;
		boolean blockedDiagDir = false;
		boolean blockedDiagEsq= false;
		
//		Check for enemies
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y));
			if(checkForQRB(enemies, 'h', new Coordinate(this.getCoord().x+1, this.getCoord().y))) {
				blockedHor = true;
			}
		}
		
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y));
			if(checkForQRB(enemies, 'h', new Coordinate(this.getCoord().x-1, this.getCoord().y))) {
				blockedHor = true;
			}
		}
		
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
			if(checkForQRB(enemies,'d',new Coordinate(this.getCoord().x+1, this.getCoord().y+1))) {
				blockedDiagDir = true;
			}
		}
		
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
			if(checkForQRB(enemies, 'd',new Coordinate(this.getCoord().x-1, this.getCoord().y-1))) {
				blockedDiagDir = true;
			}
		}
		
		if(board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
			ArrayList<Piece> enemies =  enemy_inline(new Coordinate(this.getCoord().x, this.getCoord().y+1));
			if(checkForQRB(enemies, 'v', new Coordinate(this.getCoord().x, this.getCoord().y+1))) {
				blockedVertical = true;
			}
		}
		if(board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x, this.getCoord().y-1));
			if(checkForQRB(enemies, 'v', new Coordinate(this.getCoord().x, this.getCoord().y-1))) {
				blockedVertical = true;
			} 
		}
		
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
			ArrayList<Piece> enemies = enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
			if(checkForQRB(enemies, 'e', new Coordinate(this.getCoord().x-1, this.getCoord().y+1))) {
				blockedDiagEsq = true;
			} 
		}
		
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
			ArrayList<Piece> enemies =  enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
			if(checkForQRB(enemies, 'e', new Coordinate(this.getCoord().x+1, this.getCoord().y-1))) {
				blockedDiagEsq = true;
			}
		}
		
		
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y)) {
			if(blockedHor || enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y)).size()>0) {}
			else {
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y));
				}
			}
		}
		
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y)) {
			if(blockedHor || enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y)).size() > 0) {}
			else {
				Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y));
				}
			}
		}
		
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y+1)) {
			if(blockedDiagDir || enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y+1)).size() > 0) {}
			else {
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y+1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y+1));
				}
			}
		}
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y-1)) {
			if(blockedDiagDir || enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y-1)).size() > 0) {}
			else {
				Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y-1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y-1));
				}
			}
		}
		
		
		if(board.verify_xy(this.getCoord().x, this.getCoord().y+1)) {
			if(blockedVertical || enemy_inline(new Coordinate(this.getCoord().x, this.getCoord().y+1)).size() > 0) {}
			else {
				Piece p = board.get_piece(this.getCoord().x, this.getCoord().y+1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+1));
				}
			}
		} 
		if(board.verify_xy(this.getCoord().x, this.getCoord().y-1)) {
			if(blockedVertical  || enemy_inline(new Coordinate(this.getCoord().x, this.getCoord().y-1)).size() > 0) {} 
			else {
				Piece p = board.get_piece(this.getCoord().x, this.getCoord().y-1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-1));
				}
			}
		}
		
		if(board.verify_xy(this.getCoord().x-1, this.getCoord().y+1)) {
			if(blockedDiagEsq || enemy_inline(new Coordinate(this.getCoord().x-1, this.getCoord().y+1)).size() > 0) {
			}
			else {
				Piece p = board.get_piece(this.getCoord().x-1, this.getCoord().y+1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x-1, this.getCoord().y+1));
				}
			}
		}
		
		if(board.verify_xy(this.getCoord().x+1, this.getCoord().y-1)) {
			if(blockedDiagEsq || enemy_inline(new Coordinate(this.getCoord().x+1, this.getCoord().y-1)).size() > 0) {
			}
			else {
				Piece p = board.get_piece(this.getCoord().x+1, this.getCoord().y-1);
				if(p != null && p.color != this.color) { 
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
				} 
				else if(p == null) {
					lst.add(new Coordinate(this.getCoord().x+1, this.getCoord().y-1));
				}
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
	
	int testCheckMate(ArrayList<Piece> enemies) throws CoordinateInvalid {
		ArrayList<Coordinate> mlist = this.move_list();
		int nMoves = mlist.size();
		int blockedMoves = 0;
		
		for(Piece enemy: enemies) {
			for(Coordinate esc: mlist) {
				if(enemy.check_move(esc)) {
					blockedMoves ++;
				} 
			}
		}
	
		
		if(blockedMoves >= nMoves) {
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
//		int foeMoves = 0;
		ArrayList<Piece> enemies  = this.enemy_inline(this.getCoord());
		if(enemies.size() > 0){
			ArrayList <Piece> pieces = this.board.getPlayerPieces(this.owner);

			for(Piece enemy: enemies) {
				if(this.get_owner() == 1) {
					this.board.gInfo.isP1inCheck = true;
				} else {
					this.board.gInfo.isP2inCheck = true;
				}
	
				for(Piece p:pieces) {
					if(p == this) {
						continue;
					}
					for(Coordinate pc: p.move_list()) {
						if(enemy.blockedMove(enemy, p, pc, this.getCoord())) {
							savingMoves ++;
							break;
						}
					}
				}
			}
			if(savingMoves > enemies.size()) {
				return 1;
			} else  {
				return testCheckMate(enemies);
			}
		}
		if(this.get_owner() == 1) {
			this.board.gInfo.isP1inCheck = false;
		} else {
			this.board.gInfo.isP2inCheck  = false;
		}
			
		
		return 0;
	}

	
	boolean canCastling(Coordinate c) throws CoordinateInvalid {
		if((this.type != 'k')) {
			return false;
		}
		if(this.nMoves > 0) {
			return false;
		}
		
		/*
		 * GERANDO STACK OVERFLOW
		 */
		
		
//		if(c.get_x() == 7) {
//			if(this.enemy_inline(new Coordinate(6,this.getCoord().get_y())) != null) {
//				return  false;
//			}
//		}
//		System.out.print("---> 1 \n");
//		if(c.get_x() == 0) {
//			if(this.enemy_inline(new Coordinate(2,this.getCoord().get_y())) != null) {
//				return  false;
//			}
//		}

		Piece rook = this.board.get_piece(c.get_x(), c.get_y());
		if(!(rook instanceof Rook) || rook.nMoves > 0) {
			return false;
		}
		int xini = this.getCoord().get_x();
		int yini = this.getCoord().get_y();
		int xfim = rook.getCoord().get_x();

		if(xfim > xini) {
			xini ++;
			while(xfim > xini) {
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
