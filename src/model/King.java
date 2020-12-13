package model;

import java.util.ArrayList;

class King extends Piece{

	public King(Color c, int x, int y, int o, char type) {
		super(c, x, y, o, type);
		nMoves = 0;
	}
	
	/*
	 * Checa por pecas inimigas na horizontal 
	 */
	ArrayList<Piece> enemy_horizontal(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
//		left
		for(int i = c.get_x()-1; i >= 0; i --) {
			if(board.verify_xy(i, c.get_y())) {
				Piece p = board.get_piece(i, c.get_y());
				if(p != null) {
					if(p.get_owner() != this.get_owner()) {
						if(p instanceof King && i ==  c.get_x()-1) {
							enemies.add(p);
						}
						else if(p instanceof Queen || p instanceof Rook) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
//		right
		for(int j = c.get_x()+1; j < 8; j ++) {
			if(board.verify_xy(j, c.get_y())) {
				Piece p = board.get_piece(j, c.get_y());
				if(p != null) {
					if(p.get_owner() != this.get_owner()) {
						if(p instanceof King && j ==  c.get_x()+1) {
							enemies.add(p);
						}
						else if(p instanceof Queen || p instanceof Rook) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		return enemies;
	}
	
	/*
	 * Checa por pecas na vertical
	 */
	ArrayList<Piece> enemy_vertical(Coordinate c) throws CoordinateInvalid {

		ArrayList<Piece> enemies = new ArrayList<Piece>();
		for(int j = c.get_y()+1; j < 8; j ++) {
			if(board.verify_xy(c.get_x(), j)) {
				Piece p = board.get_piece(c.get_x(),j);
				if(p != null) {
					if(p.get_owner() != this.get_owner()) {
						if(p instanceof King  && j == c.get_y()+1) {
							enemies.add(p);
						}
						else if(p instanceof Queen || p instanceof Rook) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}

		
		for(int j = c.get_y()-1; j >= 0; j --) {
			if(board.verify_xy(c.get_x(), j)) {
				Piece p = board.get_piece(c.get_x(),j);
				if(p != null) {
					if(p.get_owner() != this.get_owner()) {
						if(p instanceof  King && j == c.get_y()-1) {
							enemies.add(p);
						}
						else if(p instanceof Queen || p instanceof Rook) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
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
	 * Checa por pecas na diagonal direita
	 */
	ArrayList<Piece> enemy_diagonal_d(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();

//		diag sup
		for(int i = 1; c.get_x()+i < 8 && c.get_y()+i < 8; i ++) {
			if(board.verify_xy(c.get_x()+i, c.get_y()+i)) {
				Piece p = board.get_piece(c.get_x()+i, c.get_y()+i);
				if(p!= null) {
					if(p.get_owner() != this.get_owner()) {
						if((p instanceof King || (p instanceof Pawn && p.get_owner() == 2)) && i == 1) {
							enemies.add(p);
						}
						else if(p instanceof Queen || p instanceof Bishop) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
					
				}
			} else {
				break;
			}
		}
		for(int i = 1; c.get_x()-i >= 0 && c.get_y()-i >= 0; i ++) {
			if(board.verify_xy(c.get_x()-i, c.get_y()-i)) {
				Piece p = board.get_piece(c.get_x()-i, c.get_y()-i);
				if(p!= null) {
					if(p.get_owner() != this.get_owner()) {
						if((p instanceof King || (p instanceof Pawn && p.get_owner() == 1)) && i == 1) {
							enemies.add(p);
						}
						if(p instanceof Queen || p instanceof Bishop) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		return enemies;
	}
	/*
	 * Checa por pecas na diagonal esquerda
	 */
	ArrayList<Piece> enemy_diagonal_e(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
		
		for(int i = 1; c.get_x()-i >= 0 && c.get_y()+i < 8; i ++) {
			if(board.verify_xy(c.get_x()-i, c.get_y()+i)) {
			Piece p = board.get_piece(c.get_x()-i, c.get_y()+i);
			if(p!= null) {
				if(p.get_owner() != this.get_owner()) {
					if((p instanceof King || (p instanceof Pawn && p.get_owner() == 2)) && i == 1) {
						enemies.add(p);
					}
					if(p instanceof Queen || p instanceof Bishop) {
						enemies.add(p);
					}
					else {
						break;
					}
				} else {
					break;
				}
			}
			} else {
				break;
			}
		}
		
		for(int i = 1; c.get_x()+i < 8 && c.get_y()-i >= 0; i ++) {
			if(board.verify_xy(c.get_x()+i, c.get_y()-i)) {
				Piece p = board.get_piece(c.get_x()+i, c.get_y()-i);
				if(p!= null) {
					if(p.get_owner() != this.get_owner()) {
						if((p instanceof King || (p instanceof Pawn && p.get_owner() == 1)) && i == 1) {
							enemies.add(p);
						}
						if(p instanceof Queen || p instanceof Bishop) {
							enemies.add(p);
						}
						else {
							break;
						}
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}
		return enemies;
	}
	
	/*
	 * chama as checagens de inimigos
	 */
	ArrayList<Piece> enemy_inline(Coordinate c) throws CoordinateInvalid {
		ArrayList<Piece> enemies = new ArrayList<Piece>();
		enemies.addAll(enemy_vertical(c));
		enemies.addAll(enemy_horizontal(c));
		enemies.addAll(enemy_diagonal_d(c));
		enemies.addAll(enemy_diagonal_e(c));
		enemies.addAll(enemy_knight(c));
		return enemies;
	}
	
	/*
	 * Faz a checagem por rainha/torre/bispo que podem impedir o rei de
	 * andar em uma certa direcao
	 */
	boolean checkForQR(ArrayList<Piece> enemies, Coordinate c) {
		for(Piece enemy: enemies) {
			if(enemy instanceof Queen || enemy instanceof Rook) {
				if(enemy.getCoord().get_x() == c.get_x()) {
					if( Math.abs(enemy.getCoord().get_y()- c.get_y()) > 1) {
						return true;
					}
				} else if(enemy.getCoord().get_y() == c.get_y()){
					if(Math.abs(enemy.getCoord().get_x()- c.get_x()) > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	boolean checkForQB(ArrayList<Piece> enemies, Coordinate c) {
		for(Piece enemy: enemies) {
			if(enemy instanceof Queen || enemy instanceof Bishop) {
				if(Math.abs(enemy.getCoord().get_x()- c.get_x()) > 1 && Math.abs(enemy.getCoord().get_y()- c.get_y()) > 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * 
	 */
	boolean qrbNeighbors(Coordinate c) throws CoordinateInvalid {
		if(board.verify_xy(c.get_x(),c.get_y())) {
			Piece p = board.get_piece(c.get_x(),c.get_y());
			if(p != null) {
				if(p.get_owner() != this.get_owner()) {
					if(p instanceof Queen || p instanceof Rook || p instanceof Bishop) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/*
	 * Checa os movimentos possiveis para o rei
	 */
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {
		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();

		/*
		 * Moves na vertical
		 */
		ArrayList<Piece> eVertical = enemy_vertical(this.getCoord());
		if(eVertical.size() == 0 || !checkForQR(eVertical, this.getCoord())){
			if(board.verify_xy(this.getCoord().get_x(), this.getCoord().get_y()+1)) {
				ArrayList<Piece> eVertical1 = enemy_inline(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()+1));
				if(eVertical1.size() == 0 && !qrbNeighbors(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()-1))) {
					if(board.get_piece(this.getCoord().get_x(), this.getCoord().get_y()+1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()+1));
					}
					else if(board.get_piece(this.getCoord().get_x(), this.getCoord().get_y()+1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()+1));
					}
				}
			}
			if(board.verify_xy(this.getCoord().get_x(), this.getCoord().get_y()-1) && 
					!qrbNeighbors(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()+1))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()-1)).size() == 0) {
					if(board.get_piece(this.getCoord().get_x(), this.getCoord().get_y()-1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()-1));
					}
					else if(board.get_piece(this.getCoord().get_x(), this.getCoord().get_y()-1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x(), this.getCoord().get_y()-1));
					}
				}
			}
		}
		
		/*
		 * Moves na horizontal
		 */
		ArrayList<Piece> eHorizontal = enemy_horizontal(this.getCoord());
		if(eHorizontal.size() == 0 ||  !checkForQR(eHorizontal, this.getCoord())){
			if(board.verify_xy(this.getCoord().get_x()+1, this.getCoord().get_y()) &&
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y())).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()));
					}
					else if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()));
					}
				}
			}
			if(board.verify_xy(this.getCoord().get_x()-1, this.getCoord().get_y())&&
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y())).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()));
					}
					else if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()));
					}
				}
			}
		}
		/*
		 * Moves na diagonal direita
		 */
		ArrayList<Piece> eDiagDir = enemy_diagonal_d(this.getCoord());
		if(eDiagDir.size() == 0 || !checkForQB(eDiagDir, this.getCoord())){
			if(board.verify_xy(this.getCoord().get_x()+1, this.getCoord().get_y()+1) && 
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()-1))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()+1)).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()+1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()+1));
					}
					else if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()+1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()+1));
					}
				}
			}
			if(board.verify_xy(this.getCoord().get_x()-1, this.getCoord().get_y()-1) &&
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()+1))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()-1)).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()-1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()-1));
					}
					else if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()-1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()-1));
					}
				}
			}
		}
		/*
		 * Moves na diagonal esquerda
		 */
		ArrayList<Piece> eDiagEsq = enemy_diagonal_e(this.getCoord());
		if(eDiagEsq.size() == 0 || !checkForQB(eDiagEsq, this.getCoord())){
			if(board.verify_xy(this.getCoord().get_x()-1, this.getCoord().get_y()+1) &&
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()-1))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()+1)).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()+1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()+1));
					}
					else if(board.get_piece(this.getCoord().get_x()-1, this.getCoord().get_y()+1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()+1));
					}
				}
			}
			if(board.verify_xy(this.getCoord().get_x()+1, this.getCoord().get_y()-1) &&
					!qrbNeighbors(new Coordinate(this.getCoord().get_x()-1, this.getCoord().get_y()+1))) {
				if(enemy_inline(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()-1)).size() == 0) {
					if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()-1) == null) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()-1));
					}
					else if(board.get_piece(this.getCoord().get_x()+1, this.getCoord().get_y()-1).owner != this.owner) {
						lst.add(new Coordinate(this.getCoord().get_x()+1, this.getCoord().get_y()-1));
					}
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
	
	
	/*
	 * Checa se o rei se pode salvar 
	 */
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
			System.out.print("ESTOU EM  XEQUE MATE\n");
			return 2;
		}
		else {
			return 1;
		}
	
	}
	
	/*
	 * Testa se existe alguma peça pode salvar o rei
	 */
	int testCheck() throws CoordinateInvalid {
		
		System.out.print("Estou checando por xeques \n");
		
//		this.board.gInfo.p1_foe = null;
//		this.board.gInfo.p2_foe = null;
		int savingMoves = 0;
//		int foeMoves = 0;
		ArrayList<Piece> enemies  = this.enemy_inline(this.getCoord());
		if(enemies.size() > 0){
			ArrayList <Piece> pieces = this.board.getPlayerPieces(this.owner);
			if(this.get_owner() == 1) {
				board.gInfo.p1_foe = enemies;
			} else {
				board.gInfo.p2_foe = enemies;
			}
			if(this.get_owner() == 1) {
				this.board.gInfo.isP1inCheck = true;
			} else {
				this.board.gInfo.isP2inCheck = true;
			}				
			boolean saves = false;
			for(Piece p:pieces) {
				for(Coordinate pc: p.move_list()) {
					for(Piece enemy: enemies) {

					if(p == this) {
						continue;
					}
						if(enemy.blockedMove(enemy, p, pc, this.getCoord())) {
							saves = true;
							savingMoves ++;
							continue;
						} else {
							saves = false;
							break;
						}
					}
					if(saves == true) {
						return 1;
					}
				}
				if(saves == true) {
					return 1;
				}
			}
			if(saves == false) {

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

	/*
	 * Checa se o roque é possivel
	 */
	boolean canCastling(Coordinate c) throws CoordinateInvalid {
		if((this.type != 'k')) {
			return false;
		}
		if(this.nMoves > 0) {
			return false;
		}
		
		
		if(c.get_x() == 7) {
			if(this.enemy_inline(new Coordinate(6,this.getCoord().get_y())).size() > 0) {
				return  false;
			}
		}

		if(c.get_x() == 0) {
			if(this.enemy_inline(new Coordinate(2,this.getCoord().get_y())).size() > 0) {
				return  false;
			}
		}

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
