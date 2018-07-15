
import javax.swing.JTextField;

public class windowTest {
	static WindowHandler testWindow;
	static Food food;
	static Snake snake;
	public static volatile boolean shouldRun = true;
	public static volatile boolean playing = true;
	
	public static void main(String[] args) {
		testWindow = new WindowHandler("Snake");
		food = new Food(testWindow.getWindowSize());
		snake = new Snake(new Location(5, 5), testWindow.getWindowSize());
		testWindow.add(food);
		snake.addSnake(testWindow);
		testWindow.repaint();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// GAME LOOP
				while(shouldRun) {
					snake.setVisible(true);
					while(playing) {
						while(snake.paused) snake.requestFocus();
						snake.requestFocus();
						snake.move();
						if(shouldEat()) {
							snake.eatFood(food.eatFood());
							snake.updateTails(testWindow);
						}
						if(snake.shouldKill()) playing = false;
						sleep(100);
						testWindow.repaint();
					}
					int width = 70;
					if(snake.getScore() > 9900) {
						width = 90;
					}
					JTextField scoreDisplay = new JTextField();
					scoreDisplay.setBounds(testWindow.getWidth()/2-width+15, testWindow.getHeight()/2-30, width, 20);
					scoreDisplay.setText("Score: " + Integer.toString(snake.getScore()));
					scoreDisplay.setVisible(true);
					testWindow.add(scoreDisplay);
					testWindow.repaint();
					GameResetController reset = new GameResetController();
					reset.setVisible(true);
					testWindow.add(reset);
					testWindow.repaint();
					while(!playing) {
						reset.requestFocus();
						if(reset.requestReset) {
							snake.reset();
							food.eatFood();
							scoreDisplay.setVisible(false);
							reset.requestReset = false;
							reset.setVisible(false);
							snake.setVisible(false);
							testWindow.repaint();
							playing = true;
						}
					}
				}
			}
			
		}).start();
	}

	private static void sleep(long sleepDelayMillis) {
		try {
			Thread.sleep(sleepDelayMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean shouldEat() {
		if(snake.getX() == food.getX() && snake.getY() == food.getY()) return true;
		else return false;
	}
}
