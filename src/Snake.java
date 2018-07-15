import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Snake extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<JPanel> tails = new ArrayList<>();
	ArrayList<Location> prevTailLocations = new ArrayList<>();
	int score = 0;
	Location currentLocation;
	Location prevLocation;
	Dimension screenBounds;
	final int movementSpeed = 10;
	public enum Direction {NORTH, SOUTH, EAST, WEST};
	public static Direction movementDirection = Direction.SOUTH;
	public volatile boolean paused = false;
	
	KeyListener controller = new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				if(movementDirection != Direction.SOUTH && !paused && currentLocation.getX() != prevLocation.getX())
					movementDirection = Direction.NORTH;
				break;
			case KeyEvent.VK_S:
				if(movementDirection != Direction.NORTH && !paused && currentLocation.getX() != prevLocation.getX())
					movementDirection = Direction.SOUTH;
				break;
			case KeyEvent.VK_A:
				if(movementDirection != Direction.EAST && !paused && currentLocation.getY() != prevLocation.getY())
					movementDirection = Direction.WEST;
				break;
			case KeyEvent.VK_D:
				if(movementDirection != Direction.WEST && !paused && currentLocation.getY() != prevLocation.getY())
					movementDirection = Direction.EAST;
				break;
			case KeyEvent.VK_SPACE:
				paused = !paused;
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
	
	public Snake(Location startLocation, Dimension windowSize) {
		currentLocation = startLocation;
		prevLocation = startLocation;
		this.screenBounds = new Dimension((int)windowSize.getWidth()-20, (int)windowSize.getHeight()-40);
		setBounds(currentLocation.getX(), currentLocation.getY(), 10, 10);
		setBackground(Color.RED);
		setFocusable(true);
		setVisible(true);
		addKeyListener(controller);
	}

	public void eatFood(int value) {
		score += value;
		JPanel newTail = new JPanel();
		if(prevTailLocations.size() != 0) {
			newTail.setBounds(prevTailLocations.get(prevTailLocations.size()-1).getX(), prevTailLocations.get(prevTailLocations.size()-1).getY(), 10, 10);
		} else {
			newTail.setBounds(prevLocation.getX(), prevLocation.getY(), 10, 10);
		}
		newTail.setBackground(Color.RED);
		newTail.setVisible(true);
		tails.add(newTail);
		prevTailLocations.add(new Location(0, 0));
	}
	
	public void move() {
		prevLocation = new Location(currentLocation); // MAKE NEW LOCATION, OTHERWISE YOU PASS THE CURRENT LOCATION BY REFERENCE CAUSING THE HEAD AND TAIL TO STACK
		switch(movementDirection) {
		case NORTH:
			currentLocation.addY(-movementSpeed);
			break;
		case SOUTH:
			currentLocation.addY(movementSpeed);
			break;
		case EAST:
			currentLocation.addX(movementSpeed);
			break;
		case WEST:
			currentLocation.addX(-movementSpeed);
			break;
		}
		System.out.println("Cur loc after move, X: " + currentLocation.getX() + " Y: " + currentLocation.getY());
		System.out.println("Prev loc after move, X: " + prevLocation.getX() + " Y: " + prevLocation.getY());
		if(tails.size() != 0) {
			for(int i = 0; i < tails.size(); i++) {
				Location tailLoc = new Location(tails.get(i).getX(), tails.get(i).getY());
				prevTailLocations.set(i, tailLoc);
			}
		}
		updateLocations();
	}
	
	private void updateLocations() {
		setLocation(currentLocation.getX(), currentLocation.getY());
		if(tails.size() != 0) {
			for(int i = 0; i < tails.size(); i++) {
				if(i == 0) tails.get(i).setLocation(prevLocation.getX(), prevLocation.getY());
				else tails.get(i).setLocation(prevTailLocations.get(i-1).getX(), prevTailLocations.get(i-1).getY());
			}
		}
	}
	
	public void updateTails(WindowHandler contentPane) {
		if(tails.size() != 0) {
			for(int i = 0; i < tails.size(); i++) {
				contentPane.add(tails.get(i));
				tails.get(i).setVisible(true);
			}
		}
	}
	
	public boolean shouldKill() {
		boolean shouldKill = false;
		if(currentLocation.isEqual(prevLocation)) shouldKill = true;
		else {
			for(int i = 0; i < prevTailLocations.size(); i++) {
				if(currentLocation.isEqual(prevTailLocations.get(i))) shouldKill = true;
			}
			if(!shouldKill) {
				if(currentLocation.getX() > screenBounds.getWidth() || 
						currentLocation.getY() > screenBounds.getHeight() ||
						currentLocation.getX() < 0 || currentLocation.getY() < 0) shouldKill = true;
				
			}
		}
		return shouldKill;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addSnake(WindowHandler contentPane) {
		contentPane.add(this);
		setVisible(true);
		updateTails(contentPane);
	}
	
	public void reset() {
		score = 0;
		currentLocation = new Location(5,5);
		prevLocation = new Location(currentLocation);
		for(int i = 0; i < tails.size(); i++) {
			tails.get(i).setVisible(false);
		}
		tails.clear();
		prevTailLocations.clear();
		movementDirection = Direction.SOUTH;
		paused = true;
	}
}
