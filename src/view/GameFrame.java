package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Observer.Observer;
import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

import controller.ControllerFacade;

class GameFrame extends JFrame implements Observer, MouseListener {
	private static GameFrame gFrame = null;
//	private ModelFacade model = null;
	ControllerFacade controller = null;
	File f = null;
	private ArrayList<PieceView> pImages = new ArrayList<PieceView>();
	Graphics2D g2 = null;
	private boolean IsInPreMove = false;
	ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
	ArrayList<String> piecesInCheck = new ArrayList<String>();
	ArrayList<Coordinate> mustMovep1  = new ArrayList<Coordinate>();
	ArrayList<Coordinate> mustMovep2  = new ArrayList<Coordinate>();
	
	public static GameFrame get_GameFrame(){
		if(gFrame != null) {
			return gFrame;
		}
		gFrame = new GameFrame();
		gFrame.addMouseListener(gFrame);
		gFrame.controller = ControllerFacade.get_controllerFacade();
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
		super.paint(g);
		gFrame.g2 = (Graphics2D)g;
		drawBoardFrame();
		drawBoard();
//		gFrame.model.add_observer(gFrame);
		try {
			ArrayList<String> encoded_pieces = gFrame.controller.get_piecesToDisplay();
			decode_pieces(encoded_pieces);
		} catch (CoordinateInvalid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(possibleMoves.size() > 0) {
			display_possible_moves();
		}
		if(piecesInCheck.size() > 0) {
			System.out.print("ATENCAO");
			displayPieceInCheck();
		}
		if(gFrame.controller.get_turn() == 1) {
			if(mustMovep1.size()>0) {
				displayMustMoves(1);
			}
		}
		if(gFrame.controller.get_turn() == 2) {
			if(mustMovep2.size()>0) {
				displayMustMoves(2);
			}
		}
		
		update_status_display();
		
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
	
	
	void decode_pieces(ArrayList<String> encoded_pieces) throws CoordinateInvalid{
		pImages.removeAll(pImages);
		for(String encod : encoded_pieces) {
			int i = Integer.valueOf(encod.charAt(0) - 48);
			int j = Integer.valueOf(encod.charAt(1) - 48);
//			System.out.print(encod + "\n");
			char type = encod.charAt(2);
			char color = encod.charAt(3);
//			System.out.print(i + " " + j +  "\n");
			PieceView p = new PieceView(coord_to_pos_x(i), coord_to_pos_y(j), color, type);
			pImages.add(p);
		}
		for(PieceView pieceImg: pImages) {   
			display_piece(pieceImg.display_img(), pieceImg.x, pieceImg.y);
		}
	}
	
	private void display_piece(Image img, int x, int y) throws CoordinateInvalid {
			gFrame.g2.drawImage(img, x, y, 55, 55, null);

	}
				

	
	private void displayMustMoves(int player) {
		gFrame.g2.setColor(Color.BLUE);
		gFrame.g2.setStroke(new BasicStroke(5));
		if(player == 1) {
			System.out.print("\np1 -->"+mustMovep1.get(0).get_x() + coord_to_pos_x(mustMovep1.get(0).get_y())+"\n");
			gFrame.g2.drawRect(coord_to_pos_x(mustMovep1.get(0).get_x()), coord_to_pos_x(mustMovep1.get(0).get_y()), 55, 55);
			gFrame.g2.drawRect(coord_to_pos_x(mustMovep1.get(1).get_x()),coord_to_pos_x(mustMovep1.get(1).get_y()), 55, 55);
		}
		if(player == 2) {
			System.out.print("P2"+ mustMovep2.get(0).get_x() + coord_to_pos_x(mustMovep2.get(0).get_y())+"\n");

			gFrame.g2.drawRect(coord_to_pos_x(mustMovep2.get(0).get_x()),coord_to_pos_x(mustMovep2.get(0).get_y()), 55, 55);
			gFrame.g2.drawRect(coord_to_pos_x(mustMovep2.get(1).get_x()), coord_to_pos_x(mustMovep2.get(1).get_y()), 55, 55);
		}
	}
	
	void displayPieceInCheck() {
		for(String cod: piecesInCheck) {
			int x = Integer.valueOf(cod.charAt(0) - 48);
			int y = Integer.valueOf(cod.charAt(1) - 48);
			char state = cod.charAt(2);
			gFrame.g2.setStroke(new BasicStroke(5));
			if(state == '1') {
				System.out.print(state);
				System.out.print("Esta em xeque");
				gFrame.g2.setColor(Color.YELLOW);
				gFrame.g2.drawRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
			} else {
				System.out.print(state);

				System.out.print("Esta em xeque-mate");
				gFrame.g2.setColor(Color.RED);
				gFrame.g2.drawRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
			}
		}
	}
	
	void display_possible_moves() {
		gFrame.g2.setColor(Color.GREEN);
		gFrame.g2.setStroke(new BasicStroke(5));
		for(Coordinate c: possibleMoves) {
			gFrame.g2.drawRect(coord_to_pos_x(c.get_x()), coord_to_pos_y(c.get_y()), 55, 55);
		}
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
		String status = "Vez do jogador " + gFrame.controller.get_turn();
		g2.drawString(status, 5, 55);
	}
	
		int x1,y1,x2,y2;

	@Override
	public void mouseClicked(MouseEvent e) {		
		if (e.getButton() == 1) {
//			Nenhuma peca foi selecionada ainda
			if(!IsInPreMove) {
				for(PieceView i: pImages) {
					x1 = pos_to_coord_x(e.getX());
					y1 = pos_to_coord_y(e.getY());
					if(gFrame.controller.get_turn() == 1) {
						if(mustMovep1.size()>0) {
							if(x1 != mustMovep1.get(0).get_x() || y1 != mustMovep1.get(0).get_y()) {
								return;
							}
						}
					}
					if(gFrame.controller.get_turn() == 2) {
						if(mustMovep2.size()>0) {
							if(x1 != mustMovep2.get(0).get_x() || y1 != mustMovep2.get(0).get_y()) {
								return;
							}
						}
					}
					try {
						if(i.contains(e.getX(), e.getY()) && gFrame.controller.get_turn() == gFrame.controller.get_owner(x1, y1)) {
							System.out.print("Selecionado para ver movimentos possiveis \n");
							IsInPreMove = true;
							possibleMoves = ControllerFacade.pre_move(x1,y1);
							if(possibleMoves.size() == 0) {
								System.out.print("Não há movimentos possiveis para  essa peca \n");
								IsInPreMove = false;
								return;
							}
							else {
								repaint();
								return;
							}
							
						}
					} catch (CoordinateInvalid e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.print("Não foi selecionada uma peca\n");
			}
//			peca ja foi selecionada
			else if(possibleMoves.size() > 0) {
				x2 = pos_to_coord_x(e.getX());
				y2 = pos_to_coord_y(e.getY());
				if(x2 == x1 && y2 == y1) {
					System.out.print("Desmarcou a peca");
					IsInPreMove = false;
					possibleMoves.removeAll(possibleMoves);
					repaint();
					return;
				}
				
				
				for(Coordinate i: possibleMoves) {
					if(i.get_x() == x2 && i.get_y() == y2) {
						System.out.print("Selecionou uma posicao valida\n");
						try {
							ControllerFacade.make_move(x1, y1, x2, y2);
							piecesInCheck = ControllerFacade.isThereCheck();
							if(piecesInCheck.size() > 0) {
								mustMovep1 = ControllerFacade.getMustMoves(1);
								mustMovep2  = ControllerFacade.getMustMoves(2);
							}
							else {
								mustMovep1 = new ArrayList<Coordinate>();
								mustMovep2  = new ArrayList<Coordinate>();
							}
							possibleMoves.removeAll(possibleMoves);
							IsInPreMove = false;
//							repaint();
						} catch (CoordinateInvalid e1) {
							e1.printStackTrace();
						} // Corrigir erro
						return;
					}
				}
				System.out.print("Selecionou posicao errada");
				IsInPreMove = false;
				possibleMoves.removeAll(possibleMoves);
				repaint();
			}
			
		}
		else if (e.getButton() == 2) {
			System.out.print("OI");
//				chooser.set
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
