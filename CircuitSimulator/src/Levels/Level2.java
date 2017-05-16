package Levels;

public class Level2 extends AbstractLevel{
	
	/*
	(0)Left top: 44, 11
	(1)Left bot: 44, 123
	(2)Mid: 206, 50
	(3)Right top: 352, 12
	(4)Right bot: 352, 124
	(5)switch for confirmation:
	 */
	public Level2() {
		// TODO Auto-generated constructor stub
		size = 6;
    size_r1 = 3;
    size_r2 = 2;
		size_s = 1;
    r1remaining = size_r1;
		r2remaining = size_r2;
		sremaining = size_s;
    r_wanted = 2.5;
    x.addAll(Arrays.asList(44,44,206,352,352,522)); // remember to change the last one!
		y.addAll(Arrays.asList(11,123,50,12,124,227));   // same
		this.setR(size - 1, Double.MAX_VALUE);
	}

	@Override
	public double findRes() {
		assert(isFull());
		double finalRes;
		finalRes = 1/(1/r.get(0) + 1/r.get(1)) + r.get(2) + 1/(1/r.get(3) + 1/r.get(4));
		return finalRes;
	}
	
}
