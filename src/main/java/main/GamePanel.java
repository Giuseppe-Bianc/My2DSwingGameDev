package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

	static final int ORIGINAL_TILE_SIZE = 16, SCALE = 3;
	static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
	static final int MAX_SCREEN_COL = 16, MAX_SCREEN_ROW = 12;
	static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; //768
	static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; //576

	int FPS = 60;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	int playerX = 100, playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/(double)FPS, delta = 0;
		long lastTime = System.nanoTime(), currentTime, timer = 0;
		int drawCount = 0;


		while (gameThread != null){
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000){
				System.out.println("FPS: "+ drawCount);
				timer = 0;
				drawCount = 0;
			}
		}
	}

	public void update(){
		if (keyH.upPressed){
			playerY -= playerSpeed;
		} else if (keyH.downPressed){
			playerY += playerSpeed;
		} else if (keyH.leftPressed){
			playerX -= playerSpeed;
		} else if (keyH.rightPressed){
			playerX += playerSpeed;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);

		g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
		g2.dispose();
	}
}
