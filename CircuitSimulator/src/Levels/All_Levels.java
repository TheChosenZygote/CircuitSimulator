package Levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class All_Levels {
	private ArrayList<AbstractLevel> levels = new ArrayList<>();
	final int num = 10; // number of levels
	
	public All_Levels() {
		levels.addAll(Arrays.asList(
				new Level1(), new Level2(), new Level3(),
				new Level4(), new Level5(), new Level6(), 
				new Level7(), new Level8(), new Level9()
				));
	}
	
	public AbstractLevel getLevel(int i) {
		return levels.get(i);
	}
	
}
