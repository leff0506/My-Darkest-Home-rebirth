package engine.imagework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.math.MathHelper;

public class RO {//render object
	protected Point loc= new Point(),lu= new Point(),ld= new Point(),ru= new Point(),rd= new Point();
	public ArrayList<Point> box = new ArrayList<>();
	protected ArrayList<Point> struct = new ArrayList<>();
	public int w;
	public int h;
	protected double angle;
	protected Image image;
	public Point center=new Point();
	private Polygon p = new Polygon();
	public boolean high=false;
	private AffineTransform transform;
	private int offset=2;
	public RO(int x, int y, int w, int h, double angle,boolean high,File file) {
		super();
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.high=high;
		this.loc.x = x;
		this.loc.y = y;
		this.w = w;
		this.h = h;
		this.lu.x=x+offset;
		this.lu.y=y+offset;
		this.ru.x=x+w-offset;
		this.ru.y=y+offset;
		this.rd.x=x+w-offset;
		this.rd.y=y+h-offset;
		this.ld.x=x+offset;
		this.ld.y=y+h-offset;
		box.add(lu);
		box.add(ru);
		box.add(rd);
		box.add(ld);
		struct.add(lu);
		struct.add(ru);
		struct.add(rd);
		struct.add(ld);
		this.angle = angle;
		int ox=loc.x+w/2;
		int oy=loc.y+h/2;
		center.x=ox;
		center.y=oy;
		updateReal();
	}
	public RO(int x, int y, int w, int h, double angle,boolean high,Image img) {
		super();
		
		image = img;
		this.high=high;
		this.loc.x = x;
		this.loc.y = y;
		this.w = w;
		this.h = h;
		this.lu.x=x;
		this.lu.y=y;
		this.ru.x=x+w;
		this.ru.y=y;
		this.rd.x=x+w;
		this.rd.y=y+h;
		this.ld.x=x;
		this.ld.y=y+h;
		box.add(lu);
		box.add(ru);
		box.add(rd);
		box.add(ld);
		struct.add(lu);
		struct.add(ru);
		struct.add(rd);
		struct.add(ld);
		this.angle = angle;
		int ox=loc.x+w/2;
		int oy=loc.y+h/2;
		center.x=ox;
		center.y=oy;
		updateReal();
	}
	public RO(int x, int y, int w, int h, double angle,boolean high) {
		super();
		this.high=high;
		this.loc.x = x;
		this.loc.y = y;
		this.w = w;
		this.h = h;
		this.lu.x=x;
		this.lu.y=y;
		this.ru.x=x+w;
		this.ru.y=y;
		this.rd.x=x+w;
		this.rd.y=y+h;
		this.ld.x=x;
		this.ld.y=y+h;
		box.add(lu);
		box.add(ru);
		box.add(rd);
		box.add(ld);
		struct.add(lu);
		struct.add(ru);
		struct.add(rd);
		struct.add(ld);
		this.angle = angle;
		int ox=loc.x+w/2;
		int oy=loc.y+h/2;
		center.x=ox;
		center.y=oy;
		updateReal();
	}
	public void updateReal() {
		int ox=loc.x+w/2;
		int oy=loc.y+h/2;
		center.x=ox;
		center.y=oy;
//		lu.x=(int)(Math.cos(angle)*(loc.x-ox)-Math.sin(angle)*(loc.y-oy)+ox);
//		lu.y=(int)(Math.sin(angle)*(loc.x-ox)+Math.cos(angle)*(loc.y-oy)+oy);
		
//		lu=MathHelper.rotate(new Point(loc), center,angle);
//		ru=MathHelper.rotate(new Point(loc.x+w,loc.y),  center,angle);
//		rd=MathHelper.rotate(new Point(loc.x+w,loc.y+h),  center,angle);
//		ld=MathHelper.rotate(new Point(loc.x,loc.y+h),  center,angle);
		for(int i = 0 ; i < box.size();i++) {
			box.set(i, MathHelper.rotate(struct.get(i), center,angle));
		}
//		ru.x=(int)(Math.cos(angle)*(loc.x+w-ox)-Math.sin(angle)*(loc.y-oy)+ox);
//		ru.y=(int)(Math.sin(angle)*(loc.x+w-ox)+Math.cos(angle)*(loc.y-oy)+oy);
//		
//	
//		rd.x=(int)(Math.cos(angle)*(loc.x+w-ox)-Math.sin(angle)*(loc.y+h-oy)+ox);
//		rd.y=(int)(Math.sin(angle)*(loc.x+w-ox)+Math.cos(angle)*(loc.y+h-oy)+oy);
//		
//		
//		ld.x=(int)(Math.cos(angle)*(loc.x-ox)-Math.sin(angle)*(loc.y+h-oy)+ox);
//		ld.y=(int)(Math.sin(angle)*(loc.x-ox)+Math.cos(angle)*(loc.y+h-oy)+oy);
//		System.out.println("lu : "+lu.x+" "+lu.y);
	}
	public int getX() {
		return loc.x;
		
	}
	public void moveX(int x) {
		this.loc.x+=x;
		for(Point p:struct) {
			p.x+=x;
		}
		for(Point p:box) {
			p.x+=x;
		}
		updateReal();
	}
	public void moveY(int y) {
		this.loc.y+=y;
		for(Point p:struct) {
			p.y+=y;
		}
		for(Point p:box) {
			p.y+=y;
		}
		updateReal();
	}
	public void setX(int x) {
		this.loc.x = x;
	
		updateReal();
	}
	public int getY() {
		return loc.y;
	}
	public void setY(int y) {
		this.loc.y = y;
		updateReal();
	}
	
	public Point getLu() {
		return lu;
	}
	public void setLu(Point lu) {
		this.lu = lu;
	}
	public Point getLd() {
		return ld;
	}
	public void setLd(Point ld) {
		this.ld = ld;
	}
	public Point getRu() {
		return ru;
	}
	public void setRu(Point ru) {
		this.ru = ru;
	}
	public Point getRd() {
		return rd;
	}
	public void setRd(Point rd) {
		this.rd = rd;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public double getAndgle() {
		return angle;
	}
	public void setRotation(double angle) {
		this.angle = angle;
		updateReal();
	}
	public void rotate(double angle) {
		this.angle += angle;
		if(angle>2*Math.PI) {
			angle=0;
		}
		if(angle<0) {
			angle=2*Math.PI+angle;
		}
		updateReal();
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void render(Graphics2D g) {
		transform = g.getTransform();
		g.rotate(angle,loc.x+w/2,loc.y+h/2);
		g.drawImage(image, loc.x, loc.y, w, h, null);
		g.setTransform(transform);
//		g.setColor(Color.RED);
//		g.fillRect(loc.x, loc.y, w, h);
	}
}
