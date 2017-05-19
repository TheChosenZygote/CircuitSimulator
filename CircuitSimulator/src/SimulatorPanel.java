
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import Levels.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;

public class SimulatorPanel extends JPanel implements MouseListener, MouseMotionListener{
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	public static final int menu_Width = 100;
	public static final int menu_parts_height = 50;
	public static final int menu_text_height = 10;
	public static final int circuit_offsetX = 200;
	public static final int circuit_offsetY = 100;
	public static final int box_width = 100;
	public static final int box_height = 49;
	public static final int switchWidth = 24;
	public static final int switchHeight = 26;
	PartsImage partsimage = new PartsImage();
	ArrayList<Integer> newPartsX = new ArrayList<Integer>();
	ArrayList<Integer> newPartsY = new ArrayList<Integer>();
	ArrayList<Integer> newPartsIndex = new ArrayList<Integer>();
	int gameState = 0;
	int levelIndex = 0;
	int motionX;
	int motionY;
	int motionIndex = -1;
	SwitchPanel sw = new SwitchPanel();
	Boolean swi = false;
	All_Levels a_l = new All_Levels();
	AbstractLevel currentLevel = a_l.getLevel(levelIndex);
	Boolean sw_back = true;
	public SimulatorPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//setBackground(Color.BLACK);
		setFocusable(true);
		setDoubleBuffered(true);
		requestFocus();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		if(gameState == 0) { // Start Screen
			BufferedImage startScreen = null;
			try {
				startScreen = ImageIO.read(new File("images/StartScreen.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}			
			g2.drawImage(startScreen, 0, 0, null);
		}
		else if(gameState == 1) { // Start menu
			BufferedImage menu = null;
			try {
				menu = ImageIO.read(new File("images/Menu.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}			
			g2.drawImage(menu, 0, 0, null);
		}
		else if(gameState == 2 ) { // New Game
			initialDraw(g2, levelIndex, swi);
			drawNewparts(g2);
			if (motionIndex != -1)
			{drawPartsNow(g2);}
			sw.setBounds(currentLevel.getX(currentLevel.getSize()-1) + circuit_offsetX, currentLevel.getY(currentLevel.getSize()-1) + circuit_offsetY, switchWidth, switchHeight);	
			sw.addMouseListener(new SwitchPanelListenser());
			this.add(this.sw);
			g2.dispose();
		}
		else if(gameState == 3) { // Continue Game
			
		}
		else if(gameState == 4) { // Level Select
			BufferedImage menu = null;
			try {
				menu = ImageIO.read(new File("images/LevelSelectLocked1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}			
			g2.drawImage(menu, 0, 0, null);
		}
		else if(gameState == 5) { // Exit
			System.exit(0);
		}
	}

	private void drawPartsNow(Graphics2D g2) {
		g2.drawImage(partsimage.imageset.get(motionIndex), motionX, motionY, null);
	}

	private void drawNewparts(Graphics2D g2) {
		for (int i = 0; i < newPartsIndex.size(); i++)
		{g2.drawImage(partsimage.imageset.get(newPartsIndex.get(i)),newPartsX.get(i),newPartsY.get(i), null);}
		
	}

	private void initialDraw(Graphics2D g2, int levelIndex, Boolean swi) {
		int partsSize = partsimage.imageset.size();
		for (int i = 0; i < partsSize; i++){
		g2.drawImage(partsimage.imageset.get(i), 0, i*(menu_parts_height+menu_text_height) + (menu_parts_height-partsimage.imageset.get(i).getHeight()) / 2, null);
		g2.drawString(partsimage.image_name.get(i), 0, (i+1)*(menu_parts_height+menu_text_height));
		if (swi == false)
		{g2.drawImage(currentLevel.getImage1(), circuit_offsetX, circuit_offsetY,null);}
		else if (swi == true){
		g2.drawImage(currentLevel.getImage2(), circuit_offsetX, circuit_offsetY,null);}
		}
	}

	
	
	
	public void checkCorPic(int x, int y){
		for (int i = 0; i < partsimage.get_size() ; i++)
		{if ( i*(menu_parts_height + menu_text_height) < y  && y < (i+1)*(menu_parts_height + menu_text_height))
				{motionIndex = i;
				break;}
			}
		}
	public int checkCollsion (int x, int y, AbstractLevel currentLevel){
		for (int i = 0; i < currentLevel.getSize() - currentLevel.getSize_s() ; i++)
			{
				if (checkCorFCollsion(x,y,i))
				{
	    			return i;
				}
			}
		if (motionIndex != -1){
		JOptionPane.showMessageDialog(null, "Illegal position to release.", "Illegal Move", JOptionPane.ERROR_MESSAGE);}
		return -1;
	}
	public Boolean checkCorFCollsion(int x, int y, int i){
		Boolean collision = false;
		if (x>currentLevel.getX(i)+circuit_offsetX && x<currentLevel.getX(i)+box_width+circuit_offsetX &&
				y>currentLevel.getY(i)+circuit_offsetY && y< currentLevel.getY(i)+box_height+circuit_offsetY){
			return true;
		}
		return collision;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(gameState == 0) {
			gameState = 1;
		}
		else if(gameState == 1) {
			if(e.getX() > 230 && e.getX() < 670 && e.getY() > 140 && e.getY() < 219) {
				gameState = 2;
			}
			else if(e.getX() > 230 && e.getX() < 670 && e.getY() > 250 && e.getY() < 329) {
				gameState = 3;
			}
			else if(e.getX() > 230 && e.getX() < 670 && e.getY() > 360 && e.getY() < 439) {
				gameState = 4;
			}
			else if(e.getX() > 230 && e.getX() < 670 && e.getY() > 470 && e.getY() < 549) {
				gameState = 5;
			}
		}
		else if (gameState == 4) {
			if(e.getX() > 29 && e.getX() < 246 && e.getY() > 508 && e.getY() < 576) {
				gameState = 1;
			}
		}
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		int boxIndex = checkCollsion(x, y, currentLevel);
		int res = 0;
		if (motionIndex == 0){res = 1;}
		else if (motionIndex == 1){res = 2;}
		if (x>menu_Width && motionIndex != -1 && boxIndex != -1){
			newPartsIndex.add(motionIndex);
			newPartsX.add(currentLevel.getX(boxIndex)+circuit_offsetX);
			newPartsY.add(currentLevel.getY(boxIndex)+circuit_offsetY);
			currentLevel.setR(boxIndex, res);
		}
		motionIndex = -1;
		this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if (motionIndex != -1)
		{motionX= 0;
		motionY = 0;
		motionIndex = -1;
		JOptionPane.showMessageDialog(null, "You cannot go out the frame.", "Illegal Move", JOptionPane.ERROR_MESSAGE);}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if (x < menu_Width)
		{checkCorPic(x,y);}
		motionX = x - 50;
		motionY = y - 25;
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private class SwitchPanelListenser implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (swi == false)
				{if (sw_back){
				boolean complete = currentLevel.confirm();
				if (complete)
				{swi = true;
				int choice = JOptionPane.showOptionDialog(null, "Great! You Got it!", "Level Complete", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Return","Level Up"}, null);
				if (choice == 1){
					levelIndex++;
					currentLevel = a_l.getLevel(levelIndex);
					gui_reset();
					sw_back = false;
				}
				}
				else if (!complete){
				int choice = JOptionPane.showOptionDialog(null, "Whoops! Something was wrong.", "Level Incomplete",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null, new Object[]{"Return","Try Agian"},null);
				if (choice == 1){
					gui_reset();
					sw_back = false;
				}}
				}
				if (!sw_back)
					{sw_back = true;}
				//sw_back = true;
				SimulatorPanel.this.repaint();
				}
			else if (swi == true)
				{//swi = false;
			 SimulatorPanel.this.repaint();}
		}
		private void gui_reset() {
			newPartsX.clear();
			newPartsY.clear();
			newPartsIndex.clear();
			swi = false;
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
