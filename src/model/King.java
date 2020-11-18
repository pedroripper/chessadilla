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
//		// vertical check
//		for (int i = 0; i < 8; i++) {
//			Piece p = board.get_piece(c.x, i);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			} else if(p != null && p.color == this.color){
//				break;
//			}
//        }
//		for (int j = 0; j < 8; j++) {
//			Piece p = board.get_piece(j, c.y);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			} else if(p != null && p.color == this.color){
//				break;
//			}
//        }
//		// Diagonal check
//		int fx = c.x;
//		for (int k = c.y; k < 8; k++) {
//			if(fx >= 8) {
//				break;
//			}
//			Piece p = board.get_piece(fx, k);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			}
//			else if(p != null && p.color == this.color){
//				break;
//			}
//			fx +=1;
//        }
//		fx = c.x;
//		for (int l = c.y; l >= 0; l--) {
//			if(fx >= 8) {
//				break;
//			}
//			Piece p = board.get_piece(fx, l);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			}
//			else if(p != null && p.color == this.color){
//				break;
//			}
//			fx +=1;
//        }
//		fx = c.x;
//		for (int k = c.y; k < 8; k++) {
//			if(fx < 0) {
//				break;
//			}
//			Piece p = board.get_piece(fx, k);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			} else if(p != null && p.color == this.color){
//				break;
//			}
//			fx -=1;
//        }
//		fx = c.x;
//		for (int k = c.y; k >= 0; k--) {
//			if(fx < 0) {
//				break;
//			}
//			Piece p = board.get_piece(fx, k);
//			if(p != null && p.color != this.color) {
//				p.move_list();
//				if(p.check_move(c)) {
//					return true;
//				}
//			} else if(p != null && p.color == this.color){
//				break;
//			}
//			fx -=1;
//        }
//		// knight check
//		Piece knight;
//		if(board.verify_xy(c.x+1, c.y+2)) {
//			knight = board.get_piece(c.x+1, c.y+2);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x-1, c.y+2)) {
//			knight = board.get_piece(c.x-1, c.y+2);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x+1, c.y-2)) {
//			knight = board.get_piece(c.x+1, c.y-2);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x-1, c.y-2)) {
//			knight = board.get_piece(c.x-1, c.y-2);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x+2, c.y+1)) {
//			knight = board.get_piece(c.x+2, c.y+1);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x+2, c.y-1)) {
//			knight = board.get_piece(c.x+2, c.y-1);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x-2, c.y-1)) {
//			knight = board.get_piece(c.x-2, c.y-1);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//		if(board.verify_xy(c.x-2, c.y+1)) {
//			knight = board.get_piece(c.x-2, c.y+1);
//			if(knight != null && knight.color != this.color && knight instanceof Knight) {
//				knight.move_list();
//				if(knight.check_move(c)) {
//					return true;
//				}
//			}
//		}
//
//		
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
			if(enemy_inline(new Coordinate(x,y))  == null) {
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
	
	int isInCheck() throws CoordinateInvalid {
		ArrayList<Coordinate> mlist = this.move_list();
		ArrayList<Coordinate> saveMove = new ArrayList<Coordinate>();
		int nMoves = mlist.size();
		int blockedMoves = 0;
		if(this.enemy_inline(this.coord) != null) {
			System.out.print("\nExiste enemy inline\n");
			for(Coordinate c: mlist) {
				Piece enemy = enemy_inline(c);
				if(enemy != null) {
					ArrayList <Coordinate> enemyMoves = enemy.move_list();
//					for(Coordinate enemyCoord: enemyMoves) {
						
					
					if(this.owner == 1) {
						for(Piece[] pl: board.b) {
							for(Piece p: pl) {
								if(p != null) {
								if(p.owner == 2) {
									continue;
								}
	//						System.out.print(p.type+"\n");
							
								if(p instanceof King) {
									continue;
								}
								if(board.get_piece(p.coord.get_x(), p.coord.get_y()) ==  null) {
									continue;
								}
								p.move_list();
								for(Coordinate pc: p.moveList) {
									if(enemy.check_move(pc)) {
										if(blockedMove(enemy, p, pc, this.coord)) {
											
											System.out.print("Tem alguem pra salvar o rei \n");
											System.out.print("O tipo -> " + p.type +" \n");
	
											System.out.print(p.type+p.owner+p.coord.get_x()+p.coord.get_y()+"\n");
											saveMove.add(p.coord);
											saveMove.add(pc);
											blockedMoves --;
											board.gInfo.mustMove_p1 = saveMove;
											return 1;
										}
									}
								}
							}
							}
						}
					} else {
//						for(Piece p: board.gInfo.p2_pieces) {
//							if(p != null) {
//								if(p instanceof King) {
//									continue;
//								}
//								for(Coordinate pc: p.moveList) {
//									if(enemy.blockedMove(enemy,p, pc, c)) {
//										System.out.print("Tem alguem pra salvar o rei");
//										saveMove.add(p.coord);
//										saveMove.add(pc);
//										blockedMoves --;
//										board.gInfo.mustMove_p1 = saveMove;
//										return 1;
//									}
//								}
//							}
//						}
					}
					
//				}
					}
				blockedMoves ++;
			} 
		}
		System.out.print("Movimentos bloqueados -> " +  blockedMoves + "\n");
		if(nMoves <= blockedMoves && nMoves != 0) {

		return 2;
		}
		return 0;
	}

}
