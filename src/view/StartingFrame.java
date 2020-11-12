package view;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class StartingFrame extends JFrame  {
	JButton b_newGame = new JButton("Novo Jogo");
	JButton b_loadGame = new JButton("Carregar Jogo");
	Image logo;
	JLabel title = new JLabel("Xadrez");
	JPanel p = new JPanel();
	
	public StartingFrame() throws URISyntaxException{
		try {
			logo = ImageIO.read(new File(getClass().getResource("/pices-1/b_bispo.gif").toURI()));
			}
		catch(IOException e) {
			System.out.println("oa");
			System.out.println(e.getMessage());
			System.exit(1); 
		}
		title.setFont(new Font("Verdana", Font.PLAIN, 90));
//		title.setVerticalAlignment(SwingConstants.CENTER);
		p.add(title);
//		p.add(new JLabel(new ImageIcon("./img/main.png")));
		p.add(b_newGame);
		p.add(b_loadGame);
		p.setBackground(Color.WHITE);
		getContentPane().add(p); 
		setSize(400,500);
		
	}
	
}
