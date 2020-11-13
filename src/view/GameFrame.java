package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


class GameFrame extends Frame{
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
	
	/*
	 * prepareGUI: set da tela do jogo
	 */
	private void prepareGUI() {
		setSize(450,550);
		addWindowListener(new WindowAdapter() {
		     public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
		     }        
		 }); 
	}
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		drawBoardFrame(g2);
		drawBoard(g2);
	}
	
	/*
	 * toggleColor:  faz o toggle da cor de g2 de branco para preto
	 */
	private void toggleColor(Graphics2D g2) {
		if(g2.getColor() == Color.WHITE) {
			g2.setColor(Color.BLACK);
		} else {
			g2.setColor(Color.WHITE);
		}
	}
	
	/*
	 * drawBoardFrame: desenha o frame da board
	 */
	private void drawBoardFrame(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		Area s = new Area(new Rectangle2D.Double(0, 80, 450, 450));
		g2.fill(s);
	}
	
	/*
	 * drawBoard: Desenha a board
	 */
	private void drawBoard(Graphics2D g2) {
		for(int j = 0; j < 8; j ++) {
			for(int i = 0; i < 8; i ++) {
				Area s = new Area(new Rectangle2D.Double(i*55 + 5, j*55 + 85, 55, 55));
				toggleColor(g2);
				g2.fill(s);
				if(i == 7) {
					toggleColor(g2);
				}
			}
		}
		
		
	}
	
}