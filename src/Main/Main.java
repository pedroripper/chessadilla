package Main;
import java.net.URISyntaxException;

import view.StartingFrame;
import view.ViewFacade;

public class Main {
	
	public static void main(String []args) throws URISyntaxException {
//		System.out.printl;
		ViewFacade view = new ViewFacade();
		view.init_graphics();
	}
}
