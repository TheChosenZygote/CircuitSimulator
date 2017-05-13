package Levels;
import java.util.ArrayList;

public abstract class  AbstractLevel {
	static int size; 	//num for res1+res2+switch
	static int size_r1; // num for resistor 1
	static int size_r2; // num for resistor 2
	static int size_s;
	int r1remaining;
	int r2remaining;
	int sremaining;
	
	ArrayList<Double> r = new ArrayList<>();  // resistance for each "box"
	ArrayList<Integer> x = new ArrayList<>(); // x position
	ArrayList<Integer> y = new ArrayList<>(); // y position
	
	
	double r_wanted;
	
	public AbstractLevel() {
		for (int i = 0; i < size; i++) {
			r.set(i,Double.MIN_VALUE);
		}
	}
	
	public ArrayList<Double> getR() {
		return r;
	}
	public int getR1remaining() {
		return r1remaining;
	}
	public int getR2remaining() {
		return r2remaining;
	}
	public double getR_wanted() {
		return r_wanted;
	}
	public static int getSize() {
		return size;
	}
	public int getSremaining() {
		return sremaining;
	}
	public ArrayList<Integer> getX() {
		return x;
	}
	public ArrayList<Integer> getY() {
		return y;
	}
	public static int getSize_r1() { 
		return size_r1; 
	}
	public static int getSize_r2() { 
		return size_r2; 
	}
	public static int getSize_s() { 
		return size_s; 
	}
	
	public void setR(int pos, double res) {
		if (r.get(pos) == Double.MIN_VALUE) { // nothing there yet
			r.set(pos, res);
			if (res == 1) {
				r1remaining--;
			}
			else if (res == 2) {
				r2remaining--;
			}
			else if ((res == 0) || (res == Double.MAX_VALUE)) {
				sremaining--;
			}
			else {
				System.out.println("unknown input");
			}
		}
		else { // resetting
			removeR(pos);
			setR(pos, res);
		}
	}
	public void removeR(int pos) {
		if (r.get(pos) != Double.MIN_VALUE) { // something has been created
			if (r.get(pos) == 1) {
				r1remaining++;
			}
			else if (r.get(pos) == 2) {
				r2remaining++;
			}
			else if ((r.get(pos) == 0) || (r.get(pos) == Double.MAX_VALUE)) {
				sremaining++;
			}
			r.set(pos,Double.MIN_VALUE);
		}
		else { // nothing there yet
			System.out.println("This position was initially empty");
		}
	}
	
	public boolean isFull() {
		for (int i = 0; i < size; i++) {
			if (r.get(i) == Double.MIN_VALUE) {
				return false;
			}
		}
		return true;
	}
	
	public abstract double findRes();
	public boolean checkAnswer() {
		if (findRes() == r_wanted) {
			return true;
		}
		return false;
	}
}
