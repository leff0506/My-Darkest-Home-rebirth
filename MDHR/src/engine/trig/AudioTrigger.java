package engine.trig;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.audio.AudioManager;
import engine.imagework.RO;

public class AudioTrigger extends RO implements Trigger{
	public int temp = 1;
	private long ellapsed=0; 
	public String name;
	private Polygon p = new Polygon();
	private int vol;
	public AudioTrigger(int x, int y, int w, int h, double angle,String name,File file,int volume) {
		super(x, y, w, h, angle, false);
		this.vol=volume;
		
			try {
				AudioManager.upload(name, AudioSystem.getAudioInputStream(file));
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.name=name;
		// TODO Auto-generated constructor stub
	}
	public AudioTrigger(int x, int y, int w, int h, double angle,String name,int volume) {
		super(x, y, w, h, angle, false);
		this.vol=volume;
		this.name=name;
		// TODO Auto-generated constructor stub
	}
	public void play() {
		if(temp==1)
		AudioManager.play(name, vol);
		temp = 0;
	}
//	public void update(long time) {
//		ellapsed+=time;
//		if(ellapsed>7000) {
//			
//		}
//	}
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		p.reset();
		for(Point r:box) {
			p.addPoint(r.x, r.y);
		}
		g.drawPolygon(p);
	}
	@Override
	public void event() {
		play();
		
	}
}
