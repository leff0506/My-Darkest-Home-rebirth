package engine.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;

import engine.imagework.AO;
import engine.imagework.RO;
import engine.imagework.View;
import engine.input.Input;
import engine.level.Level;
import engine.math.MathHelper;
import engine.objects.Battery;
import engine.objects.Coll;
import engine.trig.AudioTrigger;
import engine.trig.Trigger;

public class Player extends AO {
	public ArrayList<RO> ten = new ArrayList<>();
	public ArrayList<RO> ten1 = new ArrayList<>();
	private int RadWOT=200;//radious without torch
	private Level lvl;
	private double speed=0.20;//0.20
	private double speedAnim=0.01;//0.3
	private PointerInfo a;
	private Point mouse;
	private double Tangle=Math.toRadians(50);
	private double speedTorch=0.005;
	public static int RAD_MAX = 350;//350
	public static int RAD = RAD_MAX;
	private double minusR=0;
	public boolean isTorch=false;
	public static boolean canTorch = true;
	private long upTorch =0;
	private final int TIME_T = 700;//time to change torch
	private Polygon right = new Polygon();
	private Polygon left = new Polygon();
	private Point p1=new Point(),p2=new Point(),p3=new Point(),p4=new Point();
	private int ox,oy;
	private AffineTransform transform;
	private int[] x1= new int[3],y1 = new int[3];
	private int px,py;
	private double p1y,p1x,tg,p1x1,p1y1,tg1;
	private double d1, d2, d3;
    boolean has_neg, has_pos;
    private Point t1=new Point(),t2=new Point(),t3=new Point(),t4=new Point();
    private Polygon shadow = new Polygon();
    private Polygon wt = new Polygon();
    private BufferedImage tourchPNG;
    private BufferedImage ovalPNG;
    public boolean go;
    private Image ti;
    public static boolean zak = false;
    private double nak=0;
	public Player(int x, int y, int w, int h, double angle, File dir,Level lvl) {
		super(x, y, w, h, angle,false, dir);
		
		
		this.lvl=lvl;
		upTg();
		// TODO Auto-generated constructor stub
	}
	public Player(int x, int y, int w, int h, double angle,Level lvl,ArrayList<Image> anim) {
		super(x, y, w, h, angle,false, anim);
		this.lvl=lvl;
		upTg();
		// TODO Auto-generated constructor stub
	}
	private void upTg() {
		try {
			tourchPNG = ImageIO.read(new File("img/work/tg.png"));
			ovalPNG = ImageIO.read(new File("img/work/oval.png"));
			ti = ImageIO.read(new File("img/player/1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public  void update(long time) {
		for(Battery a : lvl.bat) {
			if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), a.box)&&a.r) {
				a.c();
				canTorch=true;
				zak=false;
				nak=0;
				RAD=RAD_MAX;
			}
		}
		for(AudioTrigger a : lvl.aud) {
			if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), a.box)) {
				a.event();
			}
		}
//		if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), lvl.co.box)) {
//			lvl.co.event();
//		}
		if(isTorch) {
			lvl.taqnaya.col=false;
		}else {
			lvl.taqnaya.col=true;
		}
		if(!zak&&isTorch&&RAD>100) {
			nak+=time*speedTorch;
			RAD=RAD_MAX-(int)nak;
		}
		if(RAD<=100) {
			zak=true;
		}
		if(!canTorch) {
			upTorch+=time;
		}
		if(!canTorch&&upTorch>=TIME_T) {
			canTorch=true;
			upTorch=0;
		}
		if(zak)isTorch=false;
		go=false;

		if(Input.isKeyDown(KeyEvent.VK_W)&&View.run) {
			int ch = (int)(time*speed);
			moveY(ch);
			for(Coll c : lvl.col) {
				if(c.col)
				if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), c.box)) {
					moveY(-ch);
				}

			}
			go=true;
		}
		if(Input.isKeyDown(KeyEvent.VK_D)&&View.run) {
			int ch = -(int)(time*speed);
			moveX(ch);
			for(Coll c : lvl.col) {
				if(c.col)
				if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), c.box)) {
					moveX(-ch);
				}
			}
			go=true;
		}
		if(Input.isKeyDown(KeyEvent.VK_S)&&View.run) {
			int ch = -(int)(time*speed);
			moveY(ch);
			for(Coll c : lvl.col) {
				if(c.col)
				if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), c.box)) {
					moveY(-ch);
				}
			}
			go=true;
		}
		if(Input.isKeyDown(KeyEvent.VK_A)&&View.run) {
			int ch = (int)(time*speed);
			moveX(ch);
			for(Coll c : lvl.col) {
				if(c.col)
				if(MathHelper.inter(new Rectangle(center.x-w/2,center.y-h/2,getW(),h), c.box)) {
					moveX(-ch);
				}
			}
			go=true;
		}
		if(Input.isKeyDown(KeyEvent.VK_E)) {
			if(canTorch&&!zak) {
				isTorch=!isTorch;
				canTorch=false;
			}
			
		}
		if(!isTorch&&go) {
			curFrame+=time*speedAnim;
			
			am = anim.size();
			if((int)curFrame>am-1) {
				curFrame=0;
			}
			
			image = anim.get((int)curFrame);
		}else {
			curFrame=0;
			image = anim.get((int)curFrame);
		}
		collision();
		a = MouseInfo.getPointerInfo();
		mouse = a.getLocation();
		double m =MathHelper.angle(new Point(loc.x+w/2,loc.y+h/2),mouse);
		Point a = MathHelper.rotate(new Point(center.x, center.y+100), center,angle);
		Point b = MathHelper.rotate(new Point(center.x, center.y+100),center,m);
		double t = MathHelper.angle(a, center, b);
		if(t>Math.PI) {
			t-=Math.PI*2;
		}
		angle-=t*0.1;
		if(angle>2*Math.PI) {
			angle-=Math.PI*2;
		}
		if(angle<0) {
			angle=2*Math.PI+angle;
		}
		
		updateReal();
		
		
		
	}
	private void collision() {
		
	}
	public synchronized void moveX(int x) {
		
		
		for(RO r:lvl.bag) {
			r.moveX(x);
			r.updateReal();
		}
		for(RO r:lvl.map) {
			r.moveX(x);
			r.updateReal();
		}
		for(Coll arr :lvl.col) {
			arr.moveX(x);
			arr.updateReal();
		}
		for(RO arr :lvl.aud) {
			arr.moveX(x);
			arr.updateReal();
		}
		for(RO arr :lvl.bat) {
			arr.moveX(x);
			arr.updateReal();
		}
//		lvl.co.moveX(x);
//		lvl.co.updateReal();
		
	}
	public synchronized void moveTo(int x,int y) {
		
		int deltx = x-loc.x;
		int delty = y-loc.y;
		for(RO r:lvl.bag) {
			r.moveX(-deltx);
			r.updateReal();
		}
		for(RO r:lvl.map) {
			r.moveX(-deltx);
			
			r.updateReal();
		}
		for(Coll arr :lvl.col) {
			arr.moveX(-deltx);
			arr.updateReal();
		}
		for(RO r:lvl.bag) {
			r.moveY(-delty);
			r.updateReal();
		}
		for(RO r:lvl.map) {
			r.moveY(-delty);
			r.updateReal();
		}
		for(Coll arr :lvl.col) {
			arr.moveY(-delty);
			arr.updateReal();
		}
		for(RO arr :lvl.aud) {
			arr.moveY(-delty);
			arr.updateReal();
		}
		for(RO arr :lvl.aud) {
			arr.moveX(-deltx);
			arr.updateReal();
		}
		for(RO arr :lvl.bat) {
			arr.moveY(-delty);
			arr.updateReal();
		}
		for(RO arr :lvl.bat) {
			arr.moveX(-deltx);
			arr.updateReal();
		}
//		lvl.co.moveX(-delty);
//		lvl.co.updateReal();
//		lvl.co.moveY(-delty);
//		lvl.co.updateReal();

	}
	public synchronized void moveY(int y) {
		
		for(RO r:lvl.bag) {
			r.moveY(y);
			r.updateReal();
		}
		for(RO r:lvl.map) {
			r.moveY(y);
			r.updateReal();
		}
		for(Coll arr :lvl.col) {
			arr.moveY(y);
			arr.updateReal();
		}
		for(RO arr :lvl.aud) {
			arr.moveY(y);
			arr.updateReal();
		}
		for(RO arr :lvl.bat) {
			arr.moveY(y);
			arr.updateReal();
		}
//		lvl.co.moveY(y);
//		lvl.co.updateReal();
	}
	@Override
	public void render(Graphics2D g) {
		if(isTorch) {
			drawTorch(g);
			image=ti;
		}
		
		super.render(g);
		if(!isTorch) {
			drawNTorch(g);
		}
	}

	
	public void renderTen(Graphics2D g,RO k) {
		if(isTorch) {
			drawRoTen(k,g);
		}
	}
	private void updateTen(){
		ten.clear();
		for(RO r :lvl.map) {
			if(r.high&&inside(r)) {
				ten.add(r);
			}
		}

	}

	private void drawRoTen(RO r,Graphics2D g) {
		for(int i = 1 ; i < r.box.size();i++) {
			drawSideTen(r.box.get(i),r.box.get(i-1),g);
		}
		drawSideTen(r.box.get(0),r.box.get(r.box.size()-1),g);

	}
	private void drawSideTen(Point a,Point b,Graphics2D g) {
		shadow.reset();
		t1=a;
		t2=b;
		t3= new Point(t2.x,t2.y-2000);
		t4= new Point(t1.x,t1.y-2000);
		
		t3= MathHelper.rotate(t3,t2,Math.PI-MathHelper.angle(new Point(0,100), new Point(0,0),new Point(t2.x-center.x,t2.y-center.y)));
		t4= MathHelper.rotate(t4,t1,Math.PI-MathHelper.angle(new Point(0,100), new Point(0,0),new Point(t1.x-center.x,t1.y-center.y)));
//		t4= MathHelper.rotate(t4,t1,Math.PI+MathHelper.angle(new Point(t4.x-center.x,t4.y-center.y), new Point(0,0),new Point(0,100)));
		shadow.addPoint(t1.x, t1.y);
		shadow.addPoint(t2.x, t2.y);
		shadow.addPoint(t3.x, t3.y);
		shadow.addPoint(t4.x, t4.y);
		g.setColor(new Color(9,9,9));
		g.fillPolygon(shadow);
	}
	public  void sort() {
		selectionSort(lvl.map);
		
	}
	private void selectionSort(ArrayList<RO> list) 
	{ 
	    int i, j, min_idx; 
	  
	    // One by one move boundary of unsorted subarray 
	    for (i = 0; i < list.size()-1; i++) 
	    { 
	        // Find the minimum element in unsorted array 
	        min_idx = i; 
	        for (j = i+1; j < list.size(); j++) 
	          if (val(list.get(j)) > val(list.get(min_idx))) 
	            min_idx = j; 
	  
	        // Swap the found minimum element with the first element 
//	        System.out.println("was "+list .get(i).getX());
	        swap(list,min_idx, i); 
//	        System.out.println("is"+list .get(i).getX());
	    } 
	} 
	private double val(RO r) {
		double min1 = Double.MAX_VALUE;
		   for(Point p :r.box) {
			   double temp = p.distance(center);
			   if(temp<min1) {
				   min1=temp;
			   }
		   }
		   return min1;
	}
	private void swap(ArrayList<RO> list,int i,int j){
		
		RO temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		
		
	}
	
	float sign (Point p1, Point p2, Point p3)
	{
	    return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}

	boolean isInside (Point pt, Point v1, Point v2, Point v3)
	{
	    
	    d1 = sign(pt, v1, v2);
	    d2 = sign(pt, v2, v3);
	    d3 = sign(pt, v3, v1);

	    has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
	    has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

	    return !(has_neg && has_pos);
	}
	public boolean inside(RO r) {
		ox= loc.x+w/2;
		oy= loc.y+h/2;
		
		
		p1.x  =-2000;
		p1.y = -2000;
		p2.x  = -2000;
		p2.y = loc.y+h/2;
		p3.x  =Toolkit.getDefaultToolkit().getScreenSize().width+2000;
		p3.y = loc.y+h/2;
		p4.x =Toolkit.getDefaultToolkit().getScreenSize().width+2000;
		p4.y=-2000;
		for(Point p : r.box) {
			if(isInside(p,MathHelper.rotate(p1, center, angle),MathHelper.rotate(p2, center, angle),MathHelper.rotate(p3, center, angle))||isInside(p,MathHelper.rotate(p1, center, angle),MathHelper.rotate(p3, center, angle),MathHelper.rotate(p4, center, angle))) {
				return true;
			}
		}
		return false;
		
	}
	private void drawNTorch(Graphics2D g) {
		
		Shape circle = new Ellipse2D.Double(loc.x+w/2- RadWOT-5, loc.y+h/2- RadWOT-5, 2*RadWOT+10, 2*RadWOT+10);
		g.setColor(new Color(0,0,0,150));
		g.drawImage(ovalPNG,loc.x+w/2-RadWOT-10,loc.y+h/2-RadWOT-10,2*RadWOT+20,2*RadWOT+20,null);
		g.fill(circle);
		g.setColor(Color.BLACK);
		transform = g.getTransform();
		wt.reset();
		wt.addPoint(loc.x+w/2-2500,loc.y+h/2-2500);
		wt.addPoint(loc.x+w/2-2500,loc.y+h/2+5);
		wt.addPoint(loc.x+w/2-RadWOT,loc.y+h/2+5);
		wt.addPoint(loc.x+w/2-RadWOT,loc.y+h/2);
		for(int i = -RadWOT;i<=RadWOT;i+=2) {
			wt.addPoint(loc.x+w/2+i,(int)(loc.y+h/2-Math.sqrt(RadWOT*RadWOT - i*i)));
		}
		wt.addPoint(loc.x+w/2+RadWOT,loc.y+h/2);
		wt.addPoint(loc.x+w/2+RadWOT,loc.y+h/2+5);
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2+5);
		
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2);
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2-2500);

		g.fillPolygon(wt);
		wt.reset();
		wt.addPoint(loc.x+w/2-2500,loc.y+h/2+2500);
		wt.addPoint(loc.x+w/2-2500,loc.y+h/2-5);
		wt.addPoint(loc.x+w/2-RadWOT,loc.y+h/2-5);
		wt.addPoint(loc.x+w/2-RadWOT,loc.y+h/2);
		for(int i = -RadWOT;i<=RadWOT;i+=2) {
			wt.addPoint(loc.x+w/2+i,(int)(loc.y+h/2+Math.sqrt(RadWOT*RadWOT - i*i)));
		}
		wt.addPoint(loc.x+w/2+RadWOT,loc.y+h/2);
		wt.addPoint(loc.x+w/2+RadWOT,loc.y+h/2-5);
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2-5);
		
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2);
		wt.addPoint(loc.x+w/2+2500,loc.y+h/2+2500);

		

		g.fillPolygon(wt);
		
		g.setTransform(transform);
	}
	private void drawTorch(Graphics2D g) {
		transform = g.getTransform();
		
		g.rotate(angle, loc.x+w/2, loc.y+h/2);
		g.drawImage(tourchPNG, (int)(ox - Math.tan(Tangle/2)*RAD)-2-70,loc.y+h/2-RAD-10 , (int)(2*Math.tan(Tangle/2)*RAD)+144,90,null);
		g.setColor(Color.BLACK);
		//rect
		g.fillRect(loc.x+w/2-2500,loc.y+h/2-5-h/2,5000,2500);


		
		right.reset();
		left.reset();
		
		px = loc.x+w/2;
		py = loc.y+h/2-100;
		ox=loc.x+w/2;
		oy=loc.y+h/2;
		p1y = Math.sin(Tangle/2)*(px-ox)+Math.cos(Tangle/2)*(py-oy);
		p1x = Math.cos(Tangle/2)*(px-ox)-Math.sin(Tangle/2)*(py-oy);
		tg = p1y/p1x;
		
		x1[0]=ox-1;
		y1[0]=oy+2-h/2;
		x1[1]=ox+2500;
		y1[1]=(int)((x1[1]-ox)*tg+oy);
		x1[2]=x1[1];
		y1[2]=y1[0];
		right.addPoint(x1[0], y1[0]);
		right.addPoint(x1[1], y1[1]);
		right.addPoint(x1[2], y1[2]);
		
		p1y1 = Math.sin(-Tangle/2)*(px-ox)+Math.cos(-Tangle/2)*(py-oy);
		p1x1 = Math.cos(-Tangle/2)*(px-ox)-Math.sin(-Tangle/2)*(py-oy);
		tg1 = p1y1/p1x1;

		x1[0]=ox+1;
		y1[0]=oy+2-h/2;
		x1[1]=ox-2500;
		y1[1]=(int)((x1[1]-ox)*tg1+oy);
		x1[2]=x1[1];
		y1[2]=y1[0];
		left.addPoint(x1[0], y1[0]);
		left.addPoint(x1[1], y1[1]);
		left.addPoint(x1[2], y1[2]);
		
		Polygon temp = new Polygon();
		temp.addPoint((int)(ox - Math.tan(Tangle/2)*RAD), loc.y+h/2-RAD);
		temp.addPoint((int)(ox + Math.tan(Tangle/2)*RAD), loc.y+h/2-RAD);
		temp.addPoint(ox, oy);
		g.drawPolygon(temp);
		
		g.fillPolygon(right); 
		g.fillPolygon(left); 
		
		g.fillRect(ox-2100, oy-2500, 4200, 2500-RAD);
		g.setTransform(transform);
	}
}
