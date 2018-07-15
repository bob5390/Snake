
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WindowHandler extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel contentPane;
	
	public WindowHandler(String windowName) {
		contentPane = new JPanel();
		setTitle(windowName);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize((int)screenResolution.getWidth()/2, (int)screenResolution.getHeight()/2);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setMaximumSize(getSize());
		setContentPane(contentPane);
		setVisible(true);
	}

	public Component add(Component comp) {
		if(comp != null) {
			contentPane.add(comp);
			return comp;
		} else {
			throw new NullPointerException("Component is null!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setSize(int width, int height) {
		resize(width, height);
		setLocationRelativeTo(null);
	}
	
	public Dimension getResolution() {
		return screenResolution;
	}
	
	public Dimension getWindowSize() {
		Dimension windowSize = getSize();
		return windowSize;
	}
}
