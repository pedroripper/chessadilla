package controller;

import java.util.ArrayList;

import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

public class ControllerFacade {
	StartingController sc = null;
	
	public ControllerFacade() {
		sc = StartingController.get_starting_controller();
	}
	
	public void init() {
		sc.start();
	}
	
	ArrayList<Coordinate> pre_move(int x, int y) throws CoordinateInvalid {
		return ModelFacade.possible_moves(x,y);
	}
}
