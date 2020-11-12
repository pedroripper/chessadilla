package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class StartingFrame extends JFrame  {
	JButton b_newGame = new JButton("Novo Jogo");
	JButton b_loadGame = new JButton("Carregar Jogo");
	JLabel title = new JLabel("Xadrez");
	JPanel p = new JPanel();
	
	public StartingFrame(){
		title.setFont(new Font("Verdana", Font.PLAIN, 90));
//		title.setVerticalAlignment(SwingConstants.CENTER);
		p.add(title);
		p.add(new JLabel(new ImageIcon("img/main.png")));
		p.add(b_newGame);
		p.add(b_loadGame);
		p.setBackground(Color.WHITE);
		getContentPane().add(p); 
		setSize(400,500);
		
	}
	
}
