package view;

import model.Coordinate;

public class ViewFacade {
	ViewFacade vf= null;
	private static GameFrame gFrame = null;
	
	public ViewFacade get_vireFacade() {
		if(vf == null) {
			vf = new ViewFacade();
		}
		return vf;
	}
	
	public void init_graphics() {
		StartingFrame sFrame = StartingFrame.get_startingFrame();
		sFrame.init_frame();
	}
	
	
	
	public static void add_piece(Coordinate coord, char type, char color) {
		if(gFrame == null) {
			gFrame = GameFrame.get_GameFrame();
		}
		gFrame.load_piece(coord, type, color);
	}
	/*
	public int x_to_coord_x(int x) {
		return gFrame.pos_to_coord_x(x);
	}
	
	public int y_coord_y(int y) {
		return gFrame.pos_to_coord_y(y);
	}*/
}
