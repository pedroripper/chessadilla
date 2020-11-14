package view;


class PieceView {
	String pieceT;
	PieceImage pI;
	
	PieceView(char color, char type) {
		setColor(color, type);
		
	}
	
	/*
	 * setColor: recebe como parametro um char que representa a cor da peca
	 * e outro que representa o tipo da peca
	 */
	void setColor(char  c, char t) {
		if(c == 'b' && t == 'b') pI = PieceImage.B_BISHOP;
		if(c == 'b' && t == 'k') pI = PieceImage.B_KING;
		if(c == 'b' && t == 'c') pI = PieceImage.B_KNIGHT;
		if(c == 'b' && t == 'p') pI = PieceImage.B_PAWN;
		if(c == 'b' && t == 'q') pI = PieceImage.B_QUEEN;
		if(c == 'b' && t == 'r') pI = PieceImage.B_ROOK;
		
		if(c == 'w' && t == 'b') pI = PieceImage.W_BISHOP;
		if(c == 'w' && t == 'k') pI = PieceImage.W_KING;
		if(c == 'w' && t == 'c') pI = PieceImage.W_KNIGHT;
		if(c == 'w' && t == 'p') pI = PieceImage.W_PAWN;
		if(c == 'w' && t == 'q') pI = PieceImage.W_QUEEN;
		if(c == 'w' && t == 'r') pI = PieceImage.W_ROOK;
	
		if(c == 'c' && t == 'b') pI = PieceImage.C_BISHOP;
		if(c == 'c' && t == 'k') pI = PieceImage.C_KING;
		if(c == 'c' && t == 'c') pI = PieceImage.C_KNIGHT;
		if(c == 'c' && t == 'p') pI = PieceImage.C_PAWN;
		if(c == 'c' && t == 'q') pI = PieceImage.C_QUEEN;
		if(c == 'c' && t == 'r') pI = PieceImage.C_ROOK;
		
		if(c == 'p' && t == 'b') pI = PieceImage.P_BISHOP;
		if(c == 'p' && t == 'k') pI = PieceImage.P_KING;
		if(c == 'p' && t == 'c') pI = PieceImage.P_KNIGHT;
		if(c == 'p' && t == 'p') pI = PieceImage.P_PAWN;
		if(c == 'p' && t == 'q') pI = PieceImage.P_QUEEN;
		if(c == 'p' && t == 'r') pI = PieceImage.P_ROOK;
		}
	
	String get_piece_file() {
		return pI.toString();
	}
	
}

	

