package engine.imagework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import engine.audio.AudioManager;
import engine.level.Level;
import engine.objects.Battery;

public class View extends JPanel {
	
	public Level lvl;
	private AffineTransform transform;
	public static boolean run = true;
	private Polygon pol = new Polygon();
	public View() {
		super();
		AudioManager.play("bh", 100);
	}
	public void setLevel(Level lvl1) {
		lvl=lvl1;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(lvl.player.isTorch)
		lvl.player.sort();
		for(RO r : lvl.bag) {
			transform = g2.getTransform();
			r.render(g2);
			g2.setTransform(transform);
		}
		for(RO r :lvl.map) {
			if(lvl.player.isTorch)
			if(r.high&&lvl.player.inside(r)) {
				transform = g2.getTransform();
				lvl.player.renderTen(g2, r);
				g2.setTransform(transform);
			}
			transform = g2.getTransform();
			r.render(g2);
			g2.setTransform(transform);
		}
//		for(RO r : lvl.aos) {
//			transform = g2.getTransform();
//			r.render(g2);
//			g2.setTransform(transform);
//		}
		transform = g2.getTransform();
		g2.setColor(Color.RED);
		for(Battery r : lvl.bat) {
			
			r.render(g2);
		}
//		g2.setColor(Color.RED);
//		lvl.co.render(g2);
//		
//		g2.setTransform(transform);
		g2.setTransform(transform);
		for(RO r :lvl.col) {
			if(lvl.player.isTorch)
			if(r.high&&lvl.player.inside(r)) {
				transform = g2.getTransform();
				lvl.player.renderTen(g2, r);
				g2.setTransform(transform);
			}
//			transform = g2.getTransform();
//			r.render(g2);
//			g2.setTransform(transform);
		}
		
//		transform = g2.getTransform();
//		g2.setColor(Color.RED);
//		for(Coll r : lvl.col) {
//			
//			r.render(g2);
//		}
//		g2.setTransform(transform);
//		
//		for(RO r : lvl.aud) {
//			
//			r.render(g2);
//		}
		
//		transform = g2.getTransform();
//		lvl.player.rendTen1(g2);
//		g2.setTransform(transform);
//		for(RO r : lvl.tens) {
//			transform = g2.getTransform();
//			r.render(g2);
//			g2.setTransform(transform);
//		}
		transform = g2.getTransform();
		lvl.player.render(g2);
		g2.setTransform(transform);
	}
	public void update(long time) {
		if(run)
		lvl.update(time);
	}
}
