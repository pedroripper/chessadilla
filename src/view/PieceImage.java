package view;

enum PieceImage {

	W_BISHOP("./imagem/b_bispo.gif"),
	W_KNIGHT("./imagem/b_cavalo.gif"),
	W_QUEEN("./imagem/b_dama.gif"),
	W_PAWN("./imagem/b_peao.gif"),
	W_KING("./imagem/b_rei.gif"),
	W_ROOK("./imagem/b_torre.gif"),
	
	B_BISHOP("./imagem/p_bispo.gif"),
	B_KNIGHT("./imagem/p_cavalo.gif"),
	B_QUEEN("./imagem/p_dama.gif"),
	B_PAWN("./imagem/p_peao.gif"),
	B_KING("./imagem/p_rei.gif"),
	B_ROOK("./imagem/p_torre.gif"),
	
	C_BISHOP("./imagem/CyanB.gif"),
	C_KNIGHT("./imagem/CyanN.gif"),
	C_QUEEN("./imagem/CyanQ.gif"),
	C_PAWN("./imagem/CyanP.gif"),
	C_KING("./imagem/CyanK.gif"),
	C_ROOK("./imagem/CyanR.gif"),
	
	P_BISHOP("./imagem/PurpleB.gif"),
	P_KNIGHT("./imagem/PurpleN.gif"),
	P_QUEEN("./imagem/PurpleQ.gif"),
	P_PAWN("./imagem/PurpleP.gif"),
	P_KING("./imagem/PurpleK.gif"),
	P_ROOK("./imagem/PurpleR.gif")
	;
	
	final String string;

	private PieceImage(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}

}
