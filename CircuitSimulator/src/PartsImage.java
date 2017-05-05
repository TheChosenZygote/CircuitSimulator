import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PartsImage {
	ArrayList<BufferedImage> imageset = new ArrayList<BufferedImage>();
	ArrayList<String> image_name =new ArrayList<String>();
	BufferedImage resistor;
	BufferedImage On_off;
	BufferedImage power_supply;
	 public PartsImage(){
		 try {
				resistor = ImageIO.read(new File("images/Resistor.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		 try {
				On_off = ImageIO.read(new File("images/Switch.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		 try {
				power_supply = ImageIO.read(new File("images/Power_supply.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		 this.imageset.add(power_supply);
		 this.image_name.add("Power Supply");
		 this.imageset.add(resistor);
		 this.image_name.add("Resistor");
		 this.imageset.add(On_off);
		 this.image_name.add("Switch");

	 }
	 public int get_size(){
		 return this.imageset.size();
	 }
	 
}
