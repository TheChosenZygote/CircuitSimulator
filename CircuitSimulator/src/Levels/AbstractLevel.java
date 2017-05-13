package Levels;
import java.util.ArrayList;

// a note to front-end: notice the resetting part in setR

public abstract class AbstractLevel {
	static int size; 	//num for res1+res2+switch
	static int level;
	// the confirmation switch is always the last one -basic level settings
	static int size_r1; // num for resistor 1
	static int size_r2; // num for resistor 2
	static int size_s;	// num for switches (including the confirmation switch)
	int r1remaining;
	int r2remaining;
	int sremaining;
	// for check and calculation
	ArrayList<Double> r = new ArrayList<>();  // resistance for each "box"
	ArrayList<Integer> x = new ArrayList<>(); // x position
	ArrayList<Integer> y = new ArrayList<>(); // y position
	// new game-like features: need to discuss more!
	double r_wanted;
	double i_wanted;
	static int lifes;
	int lremaining;
	int pointsEarned;
	int doubleHits;
	// constructor
	public AbstractLevel() {
		for (int i = 0; i < size; i++) {
			r.set(i,Double.MIN_VALUE);
		}
	}
	// all get functions
	public ArrayList<Double> getR() {return r;}
	public int getR1remaining() {return r1remaining;}
	public int getR2remaining() {return r2remaining;}
	public double getR_wanted() {return r_wanted;}
	public static int getSize() {return size;}
	public int getSremaining() {return sremaining;}
	public ArrayList<Integer> getX() {return x;}
	public ArrayList<Integer> getY() {return y;}
	public static int getSize_r1() {return size_r1; }
	public static int getSize_r2() {return size_r2; }
	public static int getSize_s() {return size_s; }
	public static int getLifes() {return lifes;}
	public int getLremaining() {return lremaining;}
	public int getDoubleHits() {return doubleHits;}
	public double getI_wanted() {return i_wanted;}
	public static int getLevel() {return level;}
	public int getPointsEarned() {return pointsEarned;}
	
	// set and remove
	public boolean confirm() {
		// called when (pos == size - 1) && (res == 0)
		if (isFull()) {
			if (match()) { // full and correct
				System.out.println("Great! You got it!");
				// ... level up maybe?
				return true;
			}
			else { // full, but not the exact circuit I want
				System.out.println("Whoops! Something was wrong.");
				// ... punishment?
				return false;
			}
		}
		else { // not full yet
			System.out.println("Whoops! You haven't finished the circuit yet.");
			// ... punishment? 
			return false;
		}
	}
	
	public void setR(int pos, double res) {
		if ((pos == size - 1) && (res == 0)) { // the user is confirming his/her completion 
			confirm();
		}
		else if (r.get(pos) == Double.MIN_VALUE) { // nothing there yet
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
			/*===front-end ATTENTION auto-return of components!===*/
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
	
	// check and calculate
	public boolean isFull() {
		for (int i = 0; i < size; i++) {
			if (r.get(i) == Double.MIN_VALUE) {
				return false;
			}
		}
		return true;
	}
	public abstract double findRes();
	public boolean match() {
		if (findRes() == r_wanted) {
			return true;
		}
		return false;
	}
}
