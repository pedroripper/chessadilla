package controller;

import java.util.ArrayList;

import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

public class ControllerFacade {
	static ControllerFacade cf  = null;
	static ModelFacade mf = ModelFacade.getModelFacade();
	GameController gc = null;
	StartingController sc = null;
	
	
	public static ControllerFacade get_controllerFacade() {
		if(cf ==  null) {
			cf = new ControllerFacade();
		}
		cf.sc  =  StartingController.get_starting_controller();
		cf.gc = GameController.get_gameController();
		return cf;
	}
	
	public void init() {
		sc.start();
	}
	
	public static ArrayList<Coordinate> pre_move(int x, int y) throws CoordinateInvalid {
		return ModelFacade.possible_moves(x,y);
	}

	public void startGame() throws CoordinateInvalid {
		gc.startGame();
	}
	/*
	public int owner(int x,int y) throws CoordinateInvalid {
		return mf.get_owner(x,y);
	}*/

	
	
}
