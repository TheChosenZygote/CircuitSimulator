package Levels;

public class Level3 extends AbstractLevel{
	/*
		(0)Left: 62, 100
		(1)Right top: 289, 2
		(2)Right mid: 285, 142
		(3)Right bot: 285, 212
		(4)switch for confirmation Integer.MIN_VALUE, Integer.MIN_VALUE
	 */
	
	public Level3() {
		// TODO Auto-generated constructor stub
		super(5);
		size = 5;
		size_r1 = 2;
		size_r2 = 2;
		size_s = 1;
		r1remaining = size_r1;
		r2remaining = size_r2;
		sremaining = size_s;
		r_wanted = 1.5;
		x.addAll(Arrays.asList(62,289,285,285,512)); // remember to change the last one!
		y.addAll(Arrays.asList(100,2,142,212,233));   // same
		this.setR(4, Double.MAX_VALUE);
	}
	@Override
	public double findRes() {
		// TODO Auto-generated method stu
		assert(isFull());
		double finalRes;
		finalRes = r.get(0) + (1/r.get(1) + 1/(1/(1/r.get(2) + 1/r.get(3) )));
		return finalRes;
	}
	
}
