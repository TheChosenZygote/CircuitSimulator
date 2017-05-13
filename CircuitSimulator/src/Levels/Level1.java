package Levels;

public class Level1 extends AbstractLevel{

	public Level1() {
		//SET POSITIONS HERE
		size = 4;
		size_r1 = 1;
		size_r2 = 2;
		size_s = 1;
		r1remaining = size_r1;
		r2remaining = size_r2;
		sremaining = size_s;
		r_wanted = 2.5;
		this.setR(3, Double.MAX_VALUE);
	}
	
	@Override
	public double findRes() {
		assert(isFull());
		double finalRes;
		finalRes = r.get(0) + 1/(1/r.get(1) + 1/r.get(2));
		return finalRes;
	}
}
