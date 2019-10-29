package engine.imagework;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AO extends RO {
	protected ArrayList<Image> anim = new ArrayList<>();
	private File dir;
	protected int am;
	protected double curFrame=0;
	private double speed =0.05;
	public AO(int x, int y, int w, int h, double angle,boolean high,File dir) {
		super(x, y, w, h, angle,high);
		setDir(dir);
		image=anim.get(0);
	}
	public AO(int x, int y, int w, int h, double angle,boolean high,ArrayList<Image> anim) {
		super(x, y, w, h, angle,high);
		this.anim = anim;
		image=anim.get(0);
	}
	public void setDir(File dir){
		this.dir=dir;
		File[] files = dir.listFiles();
		am=files.length;
		for(File f :files) {
			try {
				anim.add(ImageIO.read(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void update(long time) {
		curFrame+=time*speed;
		am = anim.size();
		if((int)curFrame>am-1) {
			curFrame=0;
		}
		image = anim.get((int)curFrame);
	}

}
