package Components;
import java.awt.Image;

public abstract class AbstractResistor extends AbstractComponent{
	double resistance;
	double current;
	double volt;
	public AbstractResistor(double x, double y, boolean i, // component
							double r, double c, double v) { // resistor
		super(x, y, i);
		resistance = r;
		current = c;
		volt = v;
	}
	public double getCurrent() {
		return current;
	}
	public double getResistance() {
		return resistance;
	}
	public double getVolt() {
		return volt;
	}
	public void setCurrent(double current) {
		this.current = current;
	}
	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	public void setVolt(double volt) {
		this.volt = volt;
	}
}
