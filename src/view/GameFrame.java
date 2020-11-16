package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

import Observer.Observer;
import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

import controller.ControllerFacade;

class GameFrame extends Frame implements Observer {
	private static GameFrame gFrame = null;
	private static ModelFacade model = null;
	Image logo = null;
	File f = null;
	private ArrayList<PieceView> pImages = new ArrayList<PieceView>();
	private Graphics2D g2 = null;
	private boolean IsInPreMove = false;
	ArrayList<Coordinate> p = null;
	
	
	public static GameFrame get_GameFrame(){
		if(gFrame != null) {
			return gFrame;
		}
		gFrame = new GameFrame();
		model = new ModelFacade();
		return gFrame;

	}
	

	@Override
	public void update() {
		repaint();
	}
	
	/*
	 * prepareGUI: set da tela do jogo
	 */
	void prepareGUI() {
		setSize(450,550);
		addWindowListener(new WindowAdapter() {
		     public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
		     }        
		 }); 
	}
	
	
	public void paint(Graphics g) {
		if(g2 ==  null) {
			g2 = (Graphics2D)g;

			drawBoardFrame();
			drawBoard();
			try {
				model.add_observer(this);
				model.pieces_to_display();
				update_status_display();
				MouseHandler mHandler = new MouseHandler();
				gFrame.addMouseListener(mHandler);
			} catch (CoordinateInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			drawBoardFrame();
			drawBoard();
			update_status_display();

			try {
				refresh_pieces();
			} catch (CoordinateInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 *  converte posicao na tela para coordenada de matriz
	 */
	int pos_to_coord_x(int x) {
		int new_x;
		new_x = (int)((x-5)/55);
		return new_x;
	}
	
	int pos_to_coord_y(int y) {
		int new_y;
		new_y = (int)((y-85)/55);
		return new_y;
	}
	
	
	/*
	 * transforma x da coordenada da matriz em  x da posicao da tela
	 */
	int coord_to_pos_x(int x) {
		return (5+ 55*x);
	}
	
	/*
	 * transforma y da coordenada da matriz em  y da posicao da tela
	 */
	int coord_to_pos_y(int y) {
		return (85+ 55*y);
	}
	
	
	void load_piece(Coordinate matrix_index, char type, char c) {
		display_piece(matrix_index.get_x(), matrix_index.get_y(), type, c);
	}
	
	private void display_piece(int x, int y, char t, char c) {
		if(t == 'v') {
			if(x % 2 == y % 2) {
				g2.setColor(Color.WHITE);
				g2.clearRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
			} else {
				g2.setColor(Color.BLACK);
				g2.clearRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
			}
		}   
		try {
			PieceView pv = new PieceView(coord_to_pos_x(x),coord_to_pos_y(y),c,t);
			g2.drawImage(pv.display_img(), coord_to_pos_x(x), coord_to_pos_y(y), 55, 55, null);
			pImages.add(pv);
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1); 
		}		

	}
	
	
	void refresh_pieces() throws CoordinateInvalid {
		pImages.removeAll(pImages);
		model.pieces_to_display();
	}
	
	
	void display_possible_moves(Coordinate c) {
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(coord_to_pos_x(c.get_x()), coord_to_pos_y(c.get_y()), 55, 55);
	}

	/*
	 * toggleColor:  faz o toggle da cor de g2 de branco para preto
	 */
	private void toggleColor() {
		if(g2.getColor() == Color.WHITE) {
			g2.setColor(Color.BLACK);
		} else {
			g2.setColor(Color.WHITE);
		}
	}
	
	/*
	 * drawBoardFrame: desenha o frame da board
	 */
	private void drawBoardFrame() {
		g2.setColor(Color.GRAY);
		Area s = new Area(new Rectangle2D.Double(0, 80, 450, 450));
		g2.fill(s);
		
	}
	
	/*
	 * drawBoard: Desenha a board
	 */
	void drawBoard() {
		for(int j = 0; j < 8; j ++) {
			for(int i = 0; i < 8; i ++) {
				Area s = new Area(new Rectangle2D.Double(i*55 + 5, j*55 + 85, 55, 55));
				toggleColor();
				g2.fill(s);
				if(i == 7) {
					toggleColor();
				}
			}
		}
		
		
	}
	
	void update_status_display() {
		g2.setColor(Color.BLACK);
		Font font = new Font ("Courier New", 1, 30);
		g2.setFont(font);
		String status = "Vez do jogador " + model.get_turn();
		g2.drawString(status, 5, 55);
	}
	
	
	//ArrayList<Coordinate> p = pre_move(3,3);
	private class MouseHandler implements MouseListener  {
		

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if (e.getButton() == 1) {
				if(!IsInPreMove) {
					for(PieceView i: pImages) {
						int x,y;
						x = pos_to_coord_x(e.getX());
						y = pos_to_coord_y(e.getY());
						try {
							if(i.contains(e.getX(), e.getY()) && model.get_turn() == model.get_owner(x, y)) {
								try {
									p = ControllerFacade.pre_move(x,y);
									
									for(Coordinate j: p) {
										display_possible_moves(j);
									}
								} catch (CoordinateInvalid e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						} catch (CoordinateInvalid e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else if(p != null) {
					int x,y;
					x = pos_to_coord_x(e.getX());
					y = pos_to_coord_y(e.getY());
					for(Coordinate i: p) {
						if(i.get_x() == x && i.get_y() == y) {
							ControllerFacade.move(x, y); // Corrigir erro
						}
					}
				}
				
			}
			else if (e.getButton() == 2) {
					; // Falta implementar
			}
		}
	
	
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	
}
