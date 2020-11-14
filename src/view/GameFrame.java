package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Coordinate;
import model.CoordinateInvalid;
import model.ModelFacade;

class GameFrame extends Frame{
	private static GameFrame gFrame = null;
	private static ModelFacade model = null;
	Image logo = null;
	File f = null;
	private Graphics2D g2 = null;
	
	public static GameFrame get_GameFrame(){
		if(gFrame != null) {
			return gFrame;
		}
		gFrame = new GameFrame();
		model = new ModelFacade();
		return gFrame;
	}
	
//	public GameFrame() {
//		System.out.println("Carregando a GameFrame");
//
//		prepareGUI();
//		try {
//			f = new File("./imagem/p_bispo.gif");
//			logo = ImageIO.read(f);		
//			}
//		catch(IOException e) {
//			System.out.println("oa");
//			System.out.println(e.getMessage());
//			System.exit(1); 
//		}
//	}
	
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
		g2 = (Graphics2D)g;
		drawBoardFrame();
		drawBoard();
		try {
			model.newGame();
		} catch (CoordinateInvalid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	Coordinate pos_to_coord(int x, int y) {
		Coordinate c = new Coordinate(x,y);
		return c;
	}
	
	int coord_to_pos_x(int x) {
		return (5+ 55*x);
	}
	
	int coord_to_pos_y(int y) {
		return (85+ 55*y);
	}
	
	

	
	void load_piece(Coordinate matrix_index, char type, char c) {
		display_piece(matrix_index.get_x(), matrix_index.get_y(), type, c);
	}
	
	private void display_piece(int x, int y, char t, char c) {
		Image img;
		PieceView pv = new PieceView(c,t);
		try {
			File f = new File(pv.get_piece_file());
			img = ImageIO.read(f);
			g2.drawImage(img, coord_to_pos_x(x), coord_to_pos_y(y), null);
			}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1); 
		}
		

	}

	
	/*
	 * update_board() : faz o refresh das pieces no board
	 */
	void update_board() {
		
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
	
}
