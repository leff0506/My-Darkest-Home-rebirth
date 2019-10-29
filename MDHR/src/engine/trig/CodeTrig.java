package engine.trig;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.imagework.RO;
import engine.imagework.View;
import engine.input.Input;
import engine.objects.Code;

public class CodeTrig extends RO implements Trigger{
	int i = 0;
	public CodeTrig(int x, int y, int w, int h, double angle, boolean high) {
		super(x, y, w, h, angle, high);
		// TODO Auto-generated constructor stub
	}
	public CodeTrig(int x, int y, int w, int h, double angle,boolean high,File file) {
		super(x,y,w,h,angle,high,file);
		
	}
	@Override
	public void event() {
		if(i==0) {
			View.run=false;
			Code co = new Code();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			View.run=true;
		}
		i=1;
		
	}

}
