import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel{
	JButton reset = new JButton("Reset");
	public ToolbarPanel(){
		setLayout(new GridLayout(3,1,0,20));
		add(this.reset);
}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
	}
}
