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
	
	C_BISHOP("./imagem/CyanB.png"),
	C_KNIGHT("./imagem/CyanN.png"),
	C_QUEEN("./imagem/CyanQ.png"),
	C_PAWN("./imagem/CyanP.png"),
	C_KING("./imagem/CyanK.png"),
	C_ROOK("./imagem/CyanR.png"),
	
	P_BISHOP("./imagem/PurpleB.png"),
	P_KNIGHT("./imagem/PurpleN.png"),
	P_QUEEN("./imagem/PurpleQ.png"),
	P_PAWN("./imagem/PurpleP.png"),
	P_KING("./imagem/PurpleK.png"),
	P_ROOK("./imagem/PurpleR.png")
	;
	
	final String string;

	private PieceImage(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}

}
