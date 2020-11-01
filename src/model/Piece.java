package model;

import java.util.ArrayList;

abstract class Piece {
	protected enum Color{
		white, black;
	}
	protected char type;
	protected Color color; // Cor do objeto
	Coordinate coord;
	Board board = Board.get_board();

	public Piece(Color c, int x, int y) {
		this.color =  c;
		this.coord = new Coordinate(x,y);
	}
	
	
	
	/*
	exibe a lista de movimentos disponiveis
	*/
	public abstract ArrayList<Coordinate> move_list() throws CoordinateInvalid;
	
	/*
	movimenta a piece
	*/
	public abstract void move(int x, int y) throws CoordinateInvalid;
	
}
