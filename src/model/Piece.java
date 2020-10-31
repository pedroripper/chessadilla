package model;


abstract class Piece {
	protected enum Color{
		white, black;
	}
	protected char type;
	protected Color color; // Cor do objeto
	protected int x; // Posicao da peca na coordenada x
	protected int y; // Posicao da peca na coordenada y
	Board board = Board.get_board();

	public Piece(Color c, int x, int y) {
		this.color =  c;
		this.x = x;
		this.y = y;
	}
	
	
	
	/*
	exibe a lista de movimentos disponiveis
	*/
	public abstract void move_list() throws CoordinateInvalid;
	
	/*
	movimenta a piece
	*/
	public abstract void move(int x, int y) throws CoordinateInvalid;
	
}
