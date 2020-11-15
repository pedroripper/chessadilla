package model;

import java.util.ArrayList;

class Queen extends Piece{

	public Queen(Color c, int x, int y, int o) {
		super(c, x, y, o);
		type = 'q';
	}

	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {

		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		int i = 1;
		boolean sup = board.verify_xy(this.coord.x, this.coord.y + i);
		boolean dir_sup = board.verify_xy(this.coord.x + i, this.coord.y + i);
		boolean dir = board.verify_xy(this.coord.x + i, this.coord.y);
		boolean dir_inf = board.verify_xy(this.coord.x + i, this.coord.y - i);
		boolean inf = board.verify_xy(this.coord.x, this.coord.y - i);
		boolean esq_sup = board.verify_xy(this.coord.x - i, this.coord.y + i);
		boolean esq = board.verify_xy(this.coord.x - i, this.coord.y);
		boolean esq_inf = board.verify_xy(this.coord.x - i, this.coord.y - i);
		boolean avanca_sup = true;
		boolean avanca_dir_sup = true;
		boolean avanca_dir = true;
		boolean avanca_dir_inf = true;
		boolean avanca_inf = true;
		boolean avanca_esq_sup = true;
		boolean avanca_esq = true;
		boolean avanca_esq_inf = true; 
		
		while(dir_sup && dir_inf && esq_sup && esq_inf ) {
			if(sup) {
				Piece p_sup = board.get_piece(this.coord.x, this.coord.y+i);
				
				// Encontrou uma peca
				if(p_sup != null) {
					// Peca encontrada eh inimiga
					if(p_sup.color != this.color) {
						lst.add(new Coordinate(this.coord.x, this.coord.y+1));
					} 
					
					avanca_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x, this.coord.y+1));
				}
				
			}
			if(dir) {
				Piece p_dir = board.get_piece(this.coord.x+i, this.coord.y);
				
				// Encontrou uma peca
				if(p_dir != null) {
					// Peca encontrada eh inimiga
					if(p_dir.color != this.color) {
						lst.add(new Coordinate(this.coord.x+1, this.coord.y));
					} 
					
					avanca_dir = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x+1, this.coord.y));
				}
				
			}
			if(inf) {
				Piece p_inf = board.get_piece(this.coord.x, this.coord.y-i);
				
				// Encontrou uma peca
				if(p_inf != null) {
					// Peca encontrada eh inimiga
					if(p_inf.color != this.color) {
						lst.add(new Coordinate(this.coord.x, this.coord.y-1));
					} 
					
					avanca_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x, this.coord.y-1));
				}
				
			}
			if(esq) {
				Piece p_esq = board.get_piece(this.coord.x-1, this.coord.y);
				
				// Encontrou uma peca
				if(p_esq != null) {
					// Peca encontrada eh inimiga
					if(p_esq.color != this.color) {
						lst.add(new Coordinate(this.coord.x-1, this.coord.y));
					} 
					
					avanca_esq = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x-1, this.coord.y));
				}
				
			}
			if(dir_sup) {
				Piece p_dir_sup = board.get_piece(this.coord.x+i, this.coord.y+i);
				
				// Encontrou uma peca
				if(p_dir_sup != null) {
					// Peca encontrada eh inimiga
					if(p_dir_sup.color != this.color) {
						lst.add(new Coordinate(this.coord.x+1, this.coord.y+1));
					} 
					
					avanca_dir_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x+1, this.coord.y+1));
				}
				
			}
			if(dir_inf) {
				Piece p_dir_inf = board.get_piece(this.coord.x+i, this.coord.y-i);
				
				// Encontrou uma peca
				if(p_dir_inf != null) {
					// Peca encontrada eh inimiga
					if(p_dir_inf.color != this.color) {
						lst.add(new Coordinate(this.coord.x+1, this.coord.y-1));
					} 
					
					avanca_dir_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x+1, this.coord.y-1));
				}
			}
			if(esq_sup) {
				Piece p_esq_sup = board.get_piece(this.coord.x-i, this.coord.y+i);
				
				// Encontrou uma peca
				if(p_esq_sup != null) {
					// Peca encontrada eh inimiga
					if(p_esq_sup.color != this.color) {
						lst.add(new Coordinate(this.coord.x-1, this.coord.y+1));
					} 
					
					avanca_esq_sup = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x-1, this.coord.y+1));
				}
				
			}
			if(esq_inf) {
				Piece p_esq_inf = board.get_piece(this.coord.x-i, this.coord.y-i);
				
				// Encontrou uma peca
				if(p_esq_inf != null) {
					// Peca encontrada eh inimiga
					if(p_esq_inf.color != this.color) {
						lst.add(new Coordinate(this.coord.x-1, this.coord.y-1));
					} 
					
					avanca_esq_inf = false;
				} // Nao encontrou uma peca
				else {
					lst.add(new Coordinate(this.coord.x-1, this.coord.y-1));
				}
				
			}
			
			// Verifica se continua procurando naquela direcao
			i++;
			if(avanca_sup) {
				sup = board.verify_xy(this.coord.x, this.coord.y + i);
			}
			else {
				sup = false;
			}
			
			if(avanca_dir) {
				dir = board.verify_xy(this.coord.x + i, this.coord.y);
			}
			else {
				dir = false;
			}
			
			if(avanca_inf) {
				inf = board.verify_xy(this.coord.x, this.coord.y - i);
			}
			else {
				inf = false;
			}
			
			if(avanca_esq) {
				esq = board.verify_xy(this.coord.x - i, this.coord.y);
			}
			else {
				esq = false;
			}
			
			if(avanca_dir_sup) {
				dir_sup = board.verify_xy(this.coord.x + i, this.coord.y + i);
			}
			else {
				dir_sup = false;
			}
			
			if(avanca_dir_inf) {
				dir_inf = board.verify_xy(this.coord.x + i, this.coord.y - i);
			}
			else {
				dir_inf = false;
			}
			
			if(avanca_esq_sup) {
				esq_sup = board.verify_xy(this.coord.x - i, this.coord.y + i);
			}
			else {
				esq_sup = false;
			}
			
			if(avanca_esq_inf) {
				esq_inf = board.verify_xy(this.coord.x - i, this.coord.y - i);
			}
			else {
				esq_inf = false;
			}
		}
		this.moveList = lst;
		return lst;
	}

	
	
}
