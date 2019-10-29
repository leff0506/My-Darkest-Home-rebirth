package engine.math;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class MathHelper {
	private final static double E = 1e-7;
	public static double angle(Point loc,Point mouse) {
		
		return Math.atan2(loc.y-mouse.y,loc.x-mouse.x)-Math.PI/2;
	}
	public static double angle(Point a,Point b,Point c) {
		double cos = (d(b,c)*d(b,c)+d(a,b)*d(a,b)-d(a,c)*d(a,c))/(2*d(b,c)*d(a,b));
		
		double deg = Math.acos(cos);
//		System.out.println("cos : "+deg);
		if((c.x-b.x)*(a.y-b.y)-(a.x-b.x)*(c.y-b.y)<0) {
			deg=Math.PI*2-deg;
		}

		return deg;
	}
	private static double d(Point a,Point b) {
		return a.distance(b);
	}
//	lu.x=(int)(Math.cos(angle)*(loc.x-ox)-Math.sin(angle)*(loc.y-oy)+ox);
//	lu.y=(int)(Math.sin(angle)*(loc.x-ox)+Math.cos(angle)*(loc.y-oy)+oy);
	public static Point rotate(Point p,Point center,double angle) {
		Point answ=new Point();
		answ.x=(int)(Math.cos(angle)*(p.x-center.x)-Math.sin(angle)*(p.y-center.y)+center.x);
		answ.y=(int)(Math.sin(angle)*(p.x-center.x)+Math.cos(angle)*(p.y-center.y)+center.y);
		return answ;
	}
	public static boolean inter(Rectangle r,ArrayList<Point> box) {
		return  inter(new Point(r.x+r.width,r.y+r.height/2),box)||inter(new Point(r.x+r.width/2,r.y),box)||inter(new Point(r.x+r.width/2,r.y+r.height),box)||inter(new Point(r.x,r.y+r.height/2),box)||inter(new Point(r.x,r.y+r.height/3*2),box)||inter(new Point(r.x+r.width/3*2,r.y+r.height),box)||inter(new Point(r.x+r.width/3*2,r.y+r.height/3*2),box)||inter(new Point(r.x+r.width/3*2,r.y),box)||inter(new Point(r.x,r.y+r.height/3),box)||inter(new Point(r.x+r.width/3,r.y+r.height),box)||inter(new Point(r.x+r.width/3,r.y+r.height/3),box)||inter(new Point(r.x+r.width/3,r.y),box)||inter(new Point(r.x,r.y),box)||inter(new Point(r.x+r.width,r.y),box)||inter(new Point(r.x+r.width,r.y+r.height),box)||inter(new Point(r.x,r.y+r.height),box);
	}
	public static boolean inter(Point p,ArrayList<Point> box) {
		int am =0;
		for(int i = 1;i<box.size();i++) {
			if(cross(p.x,p.y,box.get(i).x,box.get(i).y,box.get(i-1).x,box.get(i-1).y)) {
				am++;
			}
		}
		if(cross(p.x,p.y,box.get(0).x,box.get(0).y,box.get(box.size()-1).x,box.get(box.size()-1).y)) {
			am++;
		}
		return (am%2==1?true:false);
		
	}
	private static boolean cross(double x0, double y0, double x1, double y1, double x2, double y2){
	    return (y1 < y0 && y2 > y0 - E || y2 < y0 && y1 > y0 - E) && (y0 * (x1 - x2) + y1 * x2 - x1 * y2) / (y1 - y2) < x0;
	}
	public static int vec(Point center,Point a,Point b) {
		return (a.x-center.x)*(b.y-center.y)-(a.y-center.y)*(b.x-center.x);
	}
	
	public static boolean isOverlapping(Rectangle r1,Rectangle r2) {
		return r1.intersects(r2);
	}
	
}
