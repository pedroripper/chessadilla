package model;

import java.util.ArrayList;

class Queen extends Piece{

	public Queen(Color c, int x, int y, int o, char type) {
		super(c, x, y, o,type);
	}

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {

		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		int i = 1;
		boolean sup = this.board.verify_xy(this.getCoord().x, this.getCoord().y + i);
		boolean dir_sup = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y + i);
		boolean dir = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y);
		boolean dir_inf = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y - i);
		boolean inf = this.board.verify_xy(this.getCoord().x, this.getCoord().y - i);
		boolean esq_sup = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y + i);
		boolean esq = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y);
		boolean esq_inf = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y - i);
		boolean avanca_sup = true;
		boolean avanca_dir_sup = true;
		boolean avanca_dir = true;
		boolean avanca_dir_inf = true;
		boolean avanca_inf = true;
		boolean avanca_esq_sup = true;
		boolean avanca_esq = true;
		boolean avanca_esq_inf = true; 
		
		while(dir_sup || dir_inf || esq_sup || esq_inf|| sup || inf ||  dir || esq) {
			if(sup) {
				Piece p_sup = this.board.get_piece(this.getCoord().x, this.getCoord().y+i);
				
				// Encontrou uma peca
				if(p_sup != null) {
					// Peca encontrada eh inimiga
					if(p_sup.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+i));
					} 
					
					avanca_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y+i));
				}
				
			}
			if(dir) {
				Piece p_dir = this.board.get_piece(this.getCoord().x+i, this.getCoord().y);
				
				// Encontrou uma peca
				if(p_dir != null) {
					// Peca encontrada eh inimiga
					if(p_dir.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y));
					} 
					
					avanca_dir = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y));
				}
				
			}
			if(inf) {
				Piece p_inf = this.board.get_piece(this.getCoord().x, this.getCoord().y-i);
				
				// Encontrou uma peca
				if(p_inf != null) {
					// Peca encontrada eh inimiga
					if(p_inf.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-i));
					} 
					
					avanca_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x, this.getCoord().y-i));
				}
				
			}
			if(esq) {
				Piece p_esq = this.board.get_piece(this.getCoord().x-i, this.getCoord().y);
				
				// Encontrou uma peca
				if(p_esq != null) {
					// Peca encontrada eh inimiga
					if(p_esq.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y));
					} 
					
					avanca_esq = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y));
				}
				
			}
			if(dir_sup) {
				Piece p_dir_sup = this.board.get_piece(this.getCoord().x+i, this.getCoord().y+i);
				
				// Encontrou uma peca
				if(p_dir_sup != null) {
					// Peca encontrada eh inimiga
					if(p_dir_sup.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y+i));
					} 
					
					avanca_dir_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y+i));
				}
				
			}
			if(dir_inf) {
				Piece p_dir_inf = this.board.get_piece(this.getCoord().x+i, this.getCoord().y-i);
				
				// Encontrou uma peca
				if(p_dir_inf != null) {
					// Peca encontrada eh inimiga
					if(p_dir_inf.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y-i));
					} 
					
					avanca_dir_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x+i, this.getCoord().y-i));
				}
			}
			if(esq_sup) {
				Piece p_esq_sup = this.board.get_piece(this.getCoord().x-i, this.getCoord().y+i);
				
				// Encontrou uma peca
				if(p_esq_sup != null) {
					// Peca encontrada eh inimiga
					if(p_esq_sup.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y+i));
					} 
					
					avanca_esq_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y+i));
				}
				
			}
			if(esq_inf) {
				Piece p_esq_inf = this.board.get_piece(this.getCoord().x-i, this.getCoord().y-i);
				
				// Encontrou uma peca
				if(p_esq_inf != null) {
					// Peca encontrada eh inimiga
					if(p_esq_inf.color != this.color) {
						lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y-i));
					} 
					
					avanca_esq_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.getCoord().x-i, this.getCoord().y-i));
				}
				
			}
			
			// Verifica se continua procurando naquela direcao
			i++;
			if(avanca_sup) {
				sup = this.board.verify_xy(this.getCoord().x, this.getCoord().y + i);
			}
			else {
				sup = false;
			}
			
			if(avanca_dir) {
				dir = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y);
			}
			else {
				dir = false;
			}
			
			if(avanca_inf) {
				inf = this.board.verify_xy(this.getCoord().x, this.getCoord().y - i);
			}
			else {
				inf = false;
			}
			
			if(avanca_esq) {
				esq = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y);
			}
			else {
				esq = false;
			}
			
			if(avanca_dir_sup) {
				dir_sup = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y + i);
			}
			else {
				dir_sup = false;
			}
			
			if(avanca_dir_inf) {
				dir_inf = this.board.verify_xy(this.getCoord().x + i, this.getCoord().y - i);
			}
			else {
				dir_inf = false;
			}
			
			if(avanca_esq_sup) {
				esq_sup = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y + i);
			}
			else {
				esq_sup = false;
			}
			
			if(avanca_esq_inf) {
				esq_inf = this.board.verify_xy(this.getCoord().x - i, this.getCoord().y - i);
			}
			else {
				esq_inf = false;
			}
		}
		this.moveList = lst;
		return lst;
	}



	int testCheck() throws CoordinateInvalid {
		return 0;
	}

	int testCheckMate(Piece enemy) throws CoordinateInvalid {
		return 0;
	}

	
}
