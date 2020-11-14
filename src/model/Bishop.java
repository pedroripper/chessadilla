package model;

import java.util.ArrayList;

class Bishop extends Piece{
	public Bishop(Color c, int x, int y) {
		super(c, x, y);
		type = 'b';
	}




	
	public ArrayList<Coordinate> move_list() throws CoordinateInvalid {

		ArrayList<Coordinate> lst = new ArrayList<Coordinate>();
		int i = 1;
		boolean dir_sup = board.verify_xy(this.coord.x + i, this.coord.y + i);
		boolean dir_inf = board.verify_xy(this.coord.x + i, this.coord.y - i);
		boolean esq_sup = board.verify_xy(this.coord.x - i, this.coord.y + i);
		boolean esq_inf = board.verify_xy(this.coord.x - i, this.coord.y - i);
		boolean avanca_dir_sup = true;
		boolean avanca_dir_inf = true; 
		boolean avanca_esq_sup = true; 
		boolean avanca_esq_inf = true; 
		
		while(dir_sup && dir_inf && esq_sup && esq_inf ) {
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
