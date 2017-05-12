package Levels;

public abstract class  AbstractLevel {
	static int size; //num for res1+res2
	static int size1; // num for resistor 1
	static int size2; // num for resistor 2
	int r1remaining;
	int r2remaining;
	double[] r = new double[size];
	int[] x = new int[size];
	int[] y = new int[size];
	public abstract boolean findRes();
	
	public int[] getX() { return x; }
	public int getX(int pos) { return x[pos]; }
	public int[] getY() { return y; }
	public int getY(int pos) { return y[pos]; }
	public int getSize() { return size;}
	public int getSize1() { return size1; }
	public int getSize2() { return size2; }
	
	public void setR(int pos, int res) {
		r[pos] = res;
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
		if(r[pos] == 1) {
			r1remaining++;
		}
		else if(r[pos] == 2) {
			r2remaining++;
		}
		else {
			System.out.println("This position was initially empty");
		}
		r[pos] = 0;
	}
	
}
