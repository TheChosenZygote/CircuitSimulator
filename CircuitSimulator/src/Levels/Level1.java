package Levels;

public class Level1 extends AbstractLevel{

	public Level1() {
		//SET POSITIONS HERE
		size = 3;
		size_r1 = 1;
		size_r2 = 2;
		size_s = 0;
		r1remaining = size_r1;
		r2remaining = size_r2;
		sremaining = size_s;
	}
	
	@Override
	public boolean findRes() {
		double finalRes;
		for(int i=0; i<size; i++) {
			if(r[i] == 0) {
				System.out.println("Missing resistors");
				return false;
			}
		}
		finalRes = r[0] + 1/(1/r[1] + 1/r[2]);
		if(finalRes == 2.5)
			return true;
		return false;	
	}

}
