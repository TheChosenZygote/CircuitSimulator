package Levels;

import java.lang.Integer;

public abstract class  AbstractLevel {
	static int size; //num for res1+res2+switch
	static int size_r1; // num for resistor 1
	static int size_r2; // num for resistor 2
	static int size_s;
	int r1remaining;
	int r2remaining;
	int sremaining;
	
	double[] r = new double[size]; // resistance for each "box"
	int[] x = new int[size]; // x position
	int[] y = new int[size]; // y position
	
	public AbstractLevel() {
		for (int i = 0; i < size; i++) {
			r[i] = Integer.MIN_VALUE;
		}
	}
	
	public int[] getX() { return x; }
	public int getX(int pos) { return x[pos]; }
	public int[] getY() { return y; }
	public int getY(int pos) { return y[pos]; }
	public static int getSize_r1() { return size_r1; }
	public static int getSize_r2() { return size_r2; }
	public static int getSize_s() { return size_s; }
	
	public abstract boolean findRes();
	
	public void setR(int pos, int res) {
		if (r[pos] == Integer.MIN_VALUE) { // nothing there yet
			r[pos] = res;
			if (res == 1) {
				r1remaining--;
			}
			else if (res == 2) {
				r2remaining--;
			}
			else if ((res == 0) || (res == Integer.MAX_VALUE)) {
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
		if (r[pos] != Integer.MIN_VALUE) { // something has been created
			if (r[pos] == 1) {
				r1remaining++;
			}
			else if (r[pos] == 2) {
				r2remaining++;
			}
			else if ((r[pos] == 0) || (r[pos] == Integer.MAX_VALUE)) {
				sremaining++;
			}
			r[pos] = Integer.MIN_VALUE;
		}
		else { // nothing there yet
			System.out.println("This position was initially empty");
		}
	}
	
}
