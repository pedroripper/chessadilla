package Main;
import java.net.URISyntaxException;

import controller.ControllerFacade;

public class Main {
	
	public static void main(String []args) throws URISyntaxException {
//		System.out.printl;
		ControllerFacade cf = new ControllerFacade();
		cf.init();
	}
}
