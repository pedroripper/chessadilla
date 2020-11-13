package view;

public class ViewFacade {
	private static StartingFrame sFrame = null;
	
	public ViewFacade() {
		sFrame = StartingFrame.get_startingFrame();
		
		}
}
