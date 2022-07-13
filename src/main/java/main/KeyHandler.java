package main;

import java.awt.event.*;
import java.io.*;

public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	/**
	 * Invoked when a key has been typed. See the class description for {@link KeyEvent} for a definition of a key typed event.
	 * @param e the event to be processed
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		//unused
	}

	/**
	 * Invoked when a key has been pressed. See the class description for {@link KeyEvent} for a definition of a key pressed event.
	 * @param e the event to be processed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code){
			case KeyEvent.VK_W -> upPressed = true;
			case KeyEvent.VK_S -> downPressed = true;
			case KeyEvent.VK_A -> leftPressed = true;
			case KeyEvent.VK_D -> rightPressed = true;
		}
	}

	/**
	 * Invoked when a key has been released. See the class description for {@link KeyEvent} for a definition of a key released event.
	 * @param e the event to be processed
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code){
			case KeyEvent.VK_W -> upPressed = false;
			case KeyEvent.VK_S -> downPressed = false;
			case KeyEvent.VK_A -> leftPressed = false;
			case KeyEvent.VK_D -> rightPressed = false;
		}
	}
}
