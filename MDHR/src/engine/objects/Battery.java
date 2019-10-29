package engine.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.io.File;

import engine.audio.AudioManager;
import engine.imagework.RO;
import engine.player.Player;

public class Battery extends RO{
	private int temp = 1;

	private Polygon p = new Polygon();
	public boolean r=true;
	public Battery(int x, int y, int w, int h, double angle,Image im) {
		super(x, y, w, h, angle, false);
		image=im;
		
		// TODO Auto-generated constructor stub
	}
	public void c() {
		r=false;
	}
	@Override
	public void render(Graphics2D g) {
		if(r) {
			super.render(g);
		}
	}
//	public void update(long time) {
//		ellapsed+=time;
//		if(ellapsed>7000) {
//			
//		}
//	}

}
