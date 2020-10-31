package model;

public class Piece {
	protected enum Color{
		white, black;
	}
	protected char type;
	protected Color color; // Cor do objeto
	protected int x; // Posicao da peca na coordenada x
	protected int y; // Posicao da peca na coordenada y
	public Piece(Color c, int x, int y) {
		this.color =  c;
		this.x = x;
		this.y = y;
	}
}
