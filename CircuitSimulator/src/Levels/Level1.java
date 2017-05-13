package Levels;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Level1 extends AbstractLevel{

	public Level1() {
		x.addAll(Arrays.asList(99,300,300));
		y.addAll(Arrays.asList(93,45,143));
		r.addAll(Arrays.asList(0.0,0.0,0.0));
		//SET POSITIONS HERE
		size = 3;
		size1 = 1;
		size2 = 2;
		r1remaining = size1;
		r2remaining = size2;
		try {
			image = ImageIO.read(new File("images/circuit1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public boolean findRes() {
		double finalRes;
		for(int i=0; i<size; i++) {
			if(r.get(i) == 0) {
				System.out.println("Missing resistors");
				return false;
			}
		}
		finalRes = r.get(0) + 1/(1/r.get(1) + 1/r.get(2));
		if(finalRes == 2.5)
			return true;
		return false;
		
	}

}
