package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import model.Coordinate;
import model.CoordinateInvalid;
//import model.Piece;
import controller.ControllerFacade;

class GameFrame extends Frame implements MouseListener {
	private static GameFrame gFrame = null;
//	private ModelFacade model = null;
	ControllerFacade controller = null;
	File f = null;
	private ArrayList<PieceView> pImages = new ArrayList<PieceView>();
	Graphics2D g2 = null;
	private boolean IsInPreMove = false;
	ArrayList<Coordinate> possibleMoves = new ArrayList<Coordinate>();
	ArrayList<String> piecesInCheck = new ArrayList<String>();

	boolean shouldDisplayPromotion;
	boolean isFrozen;
	
	public static GameFrame get_GameFrame(){
		if(gFrame != null) {
			return gFrame;
		}
		gFrame = new GameFrame();
		gFrame.addMouseListener(gFrame);
		gFrame.controller = ControllerFacade.get_controllerFacade();
		gFrame.shouldDisplayPromotion = false;
		gFrame.isFrozen = false;
		return gFrame;
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
//		super.paint(g);
		
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
			displayPieceInCheck();
		} else {
			update_status_display(false);
		}
		if(gFrame.shouldDisplayPromotion) {
			displayPromotion();
		}
		
		
	}


	void togglePromotion() {
		gFrame.shouldDisplayPromotion = !gFrame.shouldDisplayPromotion;
	}

	void displayPromotion() {
		JPopupMenu popup =  new JPopupMenu();
		JMenuItem rookOption = new JMenuItem("Torre");
		rookOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					popup.setVisible(false);
					gFrame.remove(popup);
					gFrame.togglePromotion();
					gFrame.controller.promote('r');
					piecesInCheck = ControllerFacade.isThereCheck();
				} catch (CoordinateInvalid e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem bishopOption = new JMenuItem("Bispo");
		bishopOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					popup.setVisible(false);
					gFrame.remove(popup);
					gFrame.togglePromotion();
					gFrame.controller.promote('b');
					piecesInCheck = ControllerFacade.isThereCheck();
				} catch (CoordinateInvalid e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem knightOption = new JMenuItem("Cavaleiro");
		knightOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					popup.setVisible(false);
					gFrame.remove(popup);
					gFrame.togglePromotion();
					gFrame.controller.promote('c');
					piecesInCheck = ControllerFacade.isThereCheck();
				} catch (CoordinateInvalid e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem queenOption = new JMenuItem("Rainha");
		queenOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					popup.setVisible(false);
					gFrame.remove(popup);
					gFrame.togglePromotion();
					gFrame.controller.promote('q');
					piecesInCheck = ControllerFacade.isThereCheck();
				} catch (CoordinateInvalid e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		popup.add(rookOption);
		popup.add(bishopOption);
		popup.add(queenOption);
		popup.add(knightOption);
		System.out.print("Deveria estar mostrando a promocao\n");
		popup.setVisible(true);
		
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
			char type = encod.charAt(2);
			char color = encod.charAt(3);
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
				
	int charToInt(char c) {
		return Integer.valueOf(c -  48);
	}
	
	
	void displayPieceInCheck() {
		for(String cod: piecesInCheck) {
			int x = Integer.valueOf(cod.charAt(0) - 48);
			int y = Integer.valueOf(cod.charAt(1) - 48);
			char state = cod.charAt(2);
			gFrame.g2.setStroke(new BasicStroke(5));
			if(state == '1') {
				gFrame.g2.setColor(Color.YELLOW);
				gFrame.g2.drawRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
				update_status_display(false);
			} else {
				gFrame.g2.setColor(Color.RED);
				gFrame.g2.drawRect(coord_to_pos_x(x), coord_to_pos_y(y), 55, 55);
				update_status_display(true);
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
	
	void update_status_display(boolean isOver) {
		g2.setColor(Color.BLACK);
		Font font = new Font ("Courier New", 1, 30);
		g2.setFont(font);
		if(isOver) {
			String status = "Xeque mate";
			g2.drawString(status, 5, 55);
			isFrozen = true;
		} else {
			if(isFrozen) {
				String status = "Partida congelada";
				g2.drawString(status, 5, 55);
			}
			else {
				String status = "Vez do jogador " + gFrame.controller.get_turn();
				g2.drawString(status, 5, 55);
			}
		}
	}
	
		int x1,y1,x2,y2;


	public void mouseClicked(MouseEvent e) {		
		if (e.getButton() == 1 && !isFrozen) {
//			Nenhuma peca foi selecionada ainda
			if(!IsInPreMove) {
				for(PieceView i: pImages) {
					x1 = pos_to_coord_x(e.getX());
					y1 = pos_to_coord_y(e.getY());
//					
					try {
						if(i.contains(e.getX(), e.getY()) && gFrame.controller.get_turn() == gFrame.controller.get_owner(x1, y1)) {
							System.out.print("\nSelecionado para ver movimentos possiveis \n");
							IsInPreMove = true;
							possibleMoves = ControllerFacade.pre_move(x1,y1);
							if(possibleMoves.size() == 0) {
								System.out.print("\nNão há movimentos possiveis para  essa peca \n");
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
				System.out.print("\nNão foi selecionada uma peca\n");
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
							boolean move = ControllerFacade.make_move(x1, y1, x2, y2);
							if(move) {
								piecesInCheck = ControllerFacade.isThereCheck();
								isFrozen = ControllerFacade.isFrozen();
								possibleMoves.removeAll(possibleMoves);
								IsInPreMove = false;
							} else {
								System.out.print("O movimento não pode ser realizado\n");
								possibleMoves.removeAll(possibleMoves);
								IsInPreMove = false;
							}
							
//							repaint();
						} catch (CoordinateInvalid e1) {
							e1.printStackTrace();
						} // Corrigir erro
						return;
					} 
				}
				
				System.out.print("\nSelecionou posicao errada\n");
				IsInPreMove = false;
				possibleMoves.removeAll(possibleMoves);
				repaint();
			}

			else if (e.getButton() == MouseEvent.BUTTON2) {
				int response;
				JFileChooser chooser = new JFileChooser();
				//chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setDialogTitle("Escolha um arquivo para salvar");
				response = chooser.showSaveDialog(null);
				
				if(response == JFileChooser.APPROVE_OPTION) {
					FileWriter file;
					try {
						file = new FileWriter(chooser.getSelectedFile().getAbsoluteFile());

						String s = controller.board_data_to_string();
						file.write(s);
						file.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
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
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}
