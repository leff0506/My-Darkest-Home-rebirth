package engine.objects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

import engine.imagework.RO;

public class Coll extends RO{
	private Polygon p = new Polygon();
	public boolean col=true;
	public Coll(int x, int y, int w, int h, double angle, boolean high,boolean col) {
		super(x, y, w, h, angle, high);
		this.col=col;
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics2D g) {
		p.reset();
		for(Point j: box) {
			p.addPoint(j.x, j.y);
		}
		g.drawPolygon(p);
	}
	public void setBox(ArrayList<Point> box) {
		this.box=(ArrayList<Point>) box.clone();
		this.struct=(ArrayList<Point>) box.clone();
	}


}
