package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerFacade;
import model.CoordinateInvalid;

public class StartingFrame extends JFrame  {
	private static StartingFrame sFrame = null;
	ControllerFacade controller = null;
	JButton b_newGame = new JButton("Novo Jogo");
	JButton b_loadGame = new JButton("Carregar Jogo");
	JLabel title = new JLabel("Xadrez");
	JPanel p = new JPanel();
	
	
	
	public static StartingFrame get_startingFrame(){
		if(sFrame != null) {
			return sFrame;
		} 
		sFrame = new StartingFrame();
		sFrame.controller = ControllerFacade.get_controllerFacade();
		return  sFrame; 
	}
		
	void init_frame() {
		sFrame.setVisible(true);

		title.setFont(new Font("Verdana", Font.PLAIN, 90));

		p.add(title);
		
		p.add(b_newGame);
		b_newGame.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e)
	      {
		    try {
		    	
				sFrame.controller.startGame();
			} catch (CoordinateInvalid e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
	    });
		
		
		p.add(b_loadGame);
		b_loadGame.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e)
	      {
		    try {
				//sFrame.controller.startGame();
		    	int response;
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    	        "Arquivo de texto", "txt");
		    	chooser.setFileFilter(filter);
				//chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setDialogTitle("Escolha um arquivo para carregar");
				response = chooser.showOpenDialog(null);
				
				if(response == JFileChooser.APPROVE_OPTION) {
					try {
						FileReader file = new FileReader(chooser.getSelectedFile().getAbsoluteFile());
						
						controller.loadGame(file);
						//file.write(s);
						//file.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} catch (CoordinateInvalid e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
	    });
		p.setBackground(Color.WHITE);
		getContentPane().add(p); 
		setSize(400,500);
		
	}
	
	
	
	
}
