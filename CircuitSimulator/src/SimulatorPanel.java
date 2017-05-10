import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	//NewParts newParts = new NewParts();
	PartsImage partsimage = new PartsImage();
	ArrayList<Integer> newPartsX = new ArrayList<Integer>();
	ArrayList<Integer> newPartsY = new ArrayList<Integer>();
	ArrayList<Integer> newPartsIndex = new ArrayList<Integer>();
	int levelIndex;
	int motionX;
	int motionY;
	int motionIndex = -1;
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
		// TODO Auto-generated method stub
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
		}
	}

	
	
	
	public void checkCorPic(int x, int y){
		for (int i = 0; i < partsimage.get_size() ; i++)
		{if ( i*(menu_parts_height + menu_text_height) < y  && y < (i+1)*(menu_parts_height + menu_text_height))
				{motionIndex = i;
				break;}
			}
		}
	public Boolean checkCollsion (int x, int y, int levelIndex){
		Boolean collision  = false;
		for (int i = 0; i < newPartsIndex.size(); i++)
			{
				if (checkCorFCollsion(x,y,i))
				{
					collision = true;
					JOptionPane.showMessageDialog(null, "Illegal position to release.", "Illegal Move", JOptionPane.ERROR_MESSAGE);
	    			return collision;
				}
			}
		return collision;
	}
	public Boolean checkCorFCollsion(int x, int y, int i){
		Boolean collision = false;
		ArrayList<Integer>Cor = CorOfPic(i);
		if(
		((x > Cor.get(0)) && (x < Cor.get(1)) 
		&& (y > Cor.get(2)) && (y < Cor.get(3))) || // top left
		(x+partsimage.imageset.get(motionIndex).getWidth() > Cor.get(0) && 
		x+partsimage.imageset.get(motionIndex).getWidth() < Cor.get(1) &&
		(y > Cor.get(2)) && (y < Cor.get(3))) || // top right
		( (x > Cor.get(0)) && (x < Cor.get(1)) &&
		y+partsimage.imageset.get(motionIndex).getHeight() > Cor.get(2) &&
		y+partsimage.imageset.get(motionIndex).getHeight() < Cor.get(3)) || //bottom left
		(x+partsimage.imageset.get(motionIndex).getWidth() > Cor.get(0) && 
		 x+partsimage.imageset.get(motionIndex).getWidth() < Cor.get(1) &&
		 y+partsimage.imageset.get(motionIndex).getHeight() > Cor.get(2) &&
			y+partsimage.imageset.get(motionIndex).getHeight() < Cor.get(3)) || // bottom right
		(x+(partsimage.imageset.get(motionIndex).getWidth())/2 > Cor.get(0) && 
		x+(partsimage.imageset.get(motionIndex).getWidth())/2 < Cor.get(1) &&
		(y > Cor.get(2)) && (y < Cor.get(3))) || // top mid
		( (x > Cor.get(0)) && (x < Cor.get(1)) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 > Cor.get(2) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 < Cor.get(3)) || // left mid
		(x+(partsimage.imageset.get(motionIndex).getWidth())/2 > Cor.get(0) && 
		x+(partsimage.imageset.get(motionIndex).getWidth())/2 < Cor.get(1) &&
		y+partsimage.imageset.get(motionIndex).getHeight() > Cor.get(2) &&
		y+partsimage.imageset.get(motionIndex).getHeight() < Cor.get(3)) || // bottom mid
		(x+partsimage.imageset.get(motionIndex).getWidth() > Cor.get(0) && 
		x+partsimage.imageset.get(motionIndex).getWidth() < Cor.get(1) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 > Cor.get(2) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 < Cor.get(3)) || // right mid
		(x+(partsimage.imageset.get(motionIndex).getWidth())/2 > Cor.get(0) && 
		x+(partsimage.imageset.get(motionIndex).getWidth())/2 < Cor.get(1) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 > Cor.get(2) &&
		y+(partsimage.imageset.get(motionIndex).getHeight())/2 < Cor.get(3)) // mid
		){
			collision = true;
		}
		return collision;
	}
	
	public ArrayList<Integer> CorOfPic(int i){
		ArrayList<Integer> Cor= new ArrayList<Integer>();
		Cor.add(newPartsX.get(i));
		Cor.add(newPartsX.get(i)+partsimage.imageset.get(newPartsIndex.get(i)).getWidth());
		Cor.add(newPartsY.get(i));
		Cor.add(newPartsY.get(i)+partsimage.imageset.get(newPartsIndex.get(i)).getHeight());
		return Cor;
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
		if (x>menu_Width && motionIndex != -1 && !checkCollsion(x, y, levelIndex)){
			newPartsIndex.add(motionIndex);
			newPartsX.add(x);
			newPartsY.add(y);
			
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
		motionX = x;
		motionY = y;
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
