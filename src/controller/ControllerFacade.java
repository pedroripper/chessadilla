package controller;

import java.util.ArrayList;

import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

public class ControllerFacade {
	static ControllerFacade cf  = null;
	GameController gc = null;
	StartingController sc = null;
	
	
	public static ControllerFacade get_controllerFacade() {
		if(cf !=  null) {
			return cf;
		}
		cf = new ControllerFacade();
		cf.sc  =  StartingController.get_starting_controller();
		cf.gc = GameController.get_gameController();
		return cf;
	}
	
	public void init() {
		cf.sc.start();
	}
	
	public static ArrayList<Coordinate> pre_move(int x, int y) throws CoordinateInvalid {
		return cf.gc.pre_move(x,y);
	}

	public void startGame() throws CoordinateInvalid {
		cf.gc.startGame();
	}
	
	
	/*
	public int owner(int x,int y) throws CoordinateInvalid {
		return mf.get_owner(x,y);
	}*/

	public ArrayList<String>  get_piecesToDisplay() throws CoordinateInvalid{
		
		return cf.gc.get_piecesToDisplay();
	}
	
	public int get_turn() {
		return cf.gc.get_turn();
	}
	
	public static void make_move(int x1, int y1, int x2,int y2) throws CoordinateInvalid {
		cf.gc.make_move(x1,y1,x2,y2);
	}

	public int get_owner(int x1, int y1) throws CoordinateInvalid {
		return cf.gc.get_owner(x1,y1);
	}

	public static ArrayList<String> isThereCheck() throws CoordinateInvalid {
		return cf.gc.isThereCheck();
	}

	public String board_data_to_string() {
		return cf.gc.model.board_data_to_string(cf.gc.model.get_board_data());
	}

	public boolean make_castling(int x1, int x2, int y1, int y2) throws CoordinateInvalid {
		return cf.gc.make_castling(x1, x2, y1, y2);
	}
	
	

	
	
}
