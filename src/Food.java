import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Food extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int foodValue = 100;
	Dimension screenBounds;
	
	public Food(Dimension windowSize) {
		this.screenBounds = new Dimension((int)windowSize.getWidth()-30, (int)windowSize.getHeight()-50);
		setBounds(0, 0, 10, 10);
		spawnFood();
	}

	public void spawnFood() {
		int x = (int)(Math.random() * ((screenBounds.getWidth() - 10)/10))*10+5;
		int y = (int)(Math.random() * ((screenBounds.getHeight() - 10)/10))*10+5;
		setLocation(x, y);
		setBackground(Color.YELLOW);
		setVisible(true);
	}
	
	public int eatFood() {
		setVisible(false);
		spawnFood();
		return foodValue;
	}
}
