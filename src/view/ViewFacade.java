package view;

import model.Coordinate;

public class ViewFacade {
	private static ViewFacade vf= null;
	private GameFrame gFrame = null;
	private StartingFrame sFrame = null;
	
	public static ViewFacade get_viewFacade() {
		if(vf != null) {
			return  vf;
		}
		vf = new ViewFacade();
		vf.sFrame = StartingFrame.get_startingFrame();
		vf.gFrame = GameFrame.get_GameFrame();
		
		return vf;
	}
	
	public void init_graphics() {
		vf.sFrame.init_frame();
	}
	
	public void init_gameFrame() {
		vf.gFrame.setVisible(true);
		vf.gFrame.prepareGUI();
		vf.gFrame.setVisible(true);
	}

	public void refresh() {
		vf.gFrame.repaint();
	}
	
	
}
