package view;

import model.Coordinate;

public class ViewFacade {
	private static StartingFrame sFrame = null;
	private static GameFrame gFrame = null;
	
	public ViewFacade() {
		sFrame = StartingFrame.get_startingFrame();
	}
	
	public void init_graphics() {
		sFrame.init_frame();
	}
	
	
	
	public void add_piece(Coordinate coord, char type, char color) {
		if(gFrame == null) {
			gFrame = GameFrame.get_GameFrame();
		}
		gFrame.load_piece(coord, type, color);
	}
}
