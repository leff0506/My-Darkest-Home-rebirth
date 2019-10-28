package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.View;


public class Input implements KeyListener{
	public static boolean[] keys;
	public Input() {
		keys = new boolean[300];
	}
	public static boolean isKeyDown(int key){
		return keys[key];
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		if(View.run)
		keys[e.getKeyCode()]=true;
		System.out.println(e.getKeyChar());
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
