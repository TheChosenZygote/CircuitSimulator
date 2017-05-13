package Levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AbstractLevel {
	static int size; //num for res1+res2
	static int size1; // num for resistor 1
	static int size2; // num for resistor 2
	int r1remaining;
	int r2remaining;
	BufferedImage image;
	ArrayList<Double> r = new ArrayList<Double>();
	ArrayList<Integer> x = new ArrayList<Integer>();
	ArrayList<Integer> y = new ArrayList<Integer>();
	public boolean findRes() {
		return false;
	}
	
	public ArrayList<Integer> getX() { return x; }
	public int getX(int pos) { return x.get(pos); }
	public ArrayList<Integer> getY() { return y; }
	public int getY(int pos) { return y.get(pos); }
	public int getSize() { return size;}
	public int getSize1() { return size1; }
	public int getSize2() { return size2; }
	public BufferedImage getImage(){return image;}
	
	public void setR(int pos, double res) {
		r.set(pos, res);
		if(res == 1) {
			r1remaining--;
		}
		else if(res == 2) {
			r2remaining--;
		}
		else {
			System.out.println("unknown input");
		}
	}
	public void removeR(int pos) {
		if(r.get(pos) == 1) {
			r1remaining++;
		}
		else if(r.get(pos) == 2) {
			r2remaining++;
		}
		else {
			System.out.println("This position was initially empty");
		}
		r.set(pos, 0.0);
	}
	
}
