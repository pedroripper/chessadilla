package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GameFrame extends Frame{
	Image logo = null;
	File f = null;
	
	
	
	public GameFrame() {
		System.out.println("Carregando a GameFrame");

		prepareGUI();
		try {
			f = new File("./imagem/p_bispo.gif");
			logo = ImageIO.read(f);		
			}
		catch(IOException e) {
			System.out.println("oa");
			System.out.println(e.getMessage());
			System.exit(1); 
		}
	}
	
	private void prepareGUI() {
		setSize(500,500);
		addWindowListener(new WindowAdapter() {
		     public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
		     }        
		 }); 
	}
	

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	//	g2.setBackground(Color.WHITE);
		if(logo != null){
			g2.drawImage(logo, 50, 50, 50, 50, null);
		}
	
	}
	
	
}
