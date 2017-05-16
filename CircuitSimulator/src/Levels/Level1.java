package Levels;

import java.util.Arrays;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level1 extends AbstractLevel{

	/*
		(0)Left: 100, 93
		(1)Right top: 300, 45
		(2)Right bot: 300, 143
		(3)switch for confirmation: 487,213
	 */
	public Level1() {
		//SET POSITIONS HERE
		super(4);
		level = 1;
		size = 4;
		size_r1 = 1;
		size_r2 = 2;
		size_s = 1;
		r1remaining = size_r1;
		r2remaining = size_r2;
		sremaining = size_s;
		r_wanted = 2.5;
		x.addAll(Arrays.asList(100,300,300,487)); // remember to change the last one!
		y.addAll(Arrays.asList(93,45,143,213));   // same
		this.setR(3, Double.MAX_VALUE);
		try {
			images.add(ImageIO.read(new File("images/circuit lv3 1.png"))); // 0 -- open
			images.add(ImageIO.read(new File("images/circuit lv3 2.png"))); // 1 -- closed
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double findRes() {
		assert(isFull());
		double finalRes;
		finalRes = r.get(0) + 1/(1/r.get(1) + 1/r.get(2));
		return finalRes;
	}
}
