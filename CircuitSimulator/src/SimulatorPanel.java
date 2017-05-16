
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
	PartsImage partsimage = new PartsImage();
	ArrayList<Integer> newPartsX = new ArrayList<Integer>();
	ArrayList<Integer> newPartsY = new ArrayList<Integer>();
	ArrayList<Integer> newPartsIndex = new ArrayList<Integer>();
	int levelIndex;
	int motionX;
	int motionY;
	int motionIndex = -1;
	
	AbstractLevel currentLevel = new Level1();
	BufferedImage level1;
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
		initialDraw(g2, levelIndex);
		drawNewparts(g2);
		if (motionIndex != -1)
		{drawPartsNow(g2);}
		g2.dispose();
	}

	private void drawPartsNow(Graphics2D g2) {
		g2.drawImage(partsimage.imageset.get(motionIndex), motionX, motionY, null);
	}

	private void drawNewparts(Graphics2D g2) {
		for (int i = 0; i < newPartsIndex.size(); i++)
		{g2.drawImage(partsimage.imageset.get(newPartsIndex.get(i)),newPartsX.get(i),newPartsY.get(i), null);}
		
	}

	private void initialDraw(Graphics2D g2, int levelIndex) {
		int partsSize = partsimage.imageset.size();
		for (int i = 0; i < partsSize; i++){
		g2.drawImage(partsimage.imageset.get(i), 0, i*(menu_parts_height+menu_text_height) + (menu_parts_height-partsimage.imageset.get(i).getHeight()) / 2, null);
		g2.drawString(partsimage.image_name.get(i), 0, (i+1)*(menu_parts_height+menu_text_height));
		g2.drawImage(currentLevel.getImage1(), circuit_offsetX, circuit_offsetY,null);
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
		for (int i = 0; i < currentLevel.getSize(); i++)
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
}
