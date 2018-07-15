import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GameResetController extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4927309660316362345L;
	public volatile boolean requestReset = false;
	KeyListener resetButton = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				requestReset = true;
				break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	public GameResetController(){
		setBounds(5,5,10,10);
		setVisible(false);
		setFocusable(true);
		addKeyListener(resetButton);
	}
}
