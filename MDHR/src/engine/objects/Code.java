package engine.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.*;

import engine.imagework.RO;
import engine.math.MathHelper;

public class Code {
	private JFrame frame;
	private JPanel panel;
	private JEditorPane jep;
	public Code() {
		frame = new JFrame("Code");
		frame.setSize(700, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		panel = new JPanel();
		jep = new JEditorPane();
		jep.setBounds(0,0,700,500);
		jep.setEditable(false);
		jep.setText(""
				+ "	private void drawRoTen(RO r,Graphics2D g) {\r\n" + 
				"		for(int i = 1 ; i < r.box.size();i++) {\r\n" + 
				"			drawSideTen(r.box.get(i),r.box.get(i-1),g);\r\n" + 
				"		}\r\n" + 
				"		drawSideTen(r.box.get(0),r.box.get(r.box.size()-1),g);\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"	private void drawSideTen(Point a,Point b,Graphics2D g) {\r\n" + 
				"		shadow.reset();\r\n" + 
				"		t1=a;\r\n" + 
				"		t2=b;\r\n" + 
				"		t3= new Point(t2.x,t2.y-2000);\r\n" + 
				"		t4= new Point(t1.x,t1.y-2000);\r\n" + 
				"		\r\n" + 
				"		t3= MathHelper.rotate(t3,t2,Math.PI-MathHelper.angle(new Point(0,100), new Point(0,0),new Point(t2.x-center.x,t2.y-center.y)));\r\n" + 
				"		t4= MathHelper.rotate(t4,t1,Math.PI-MathHelper.angle(new Point(0,100), new Point(0,0),new Point(t1.x-center.x,t1.y-center.y)));\r\n" + 
				"//		t4= MathHelper.rotate(t4,t1,Math.PI+MathHelper.angle(new Point(t4.x-center.x,t4.y-center.y), new Point(0,0),new Point(0,100)));\r\n" + 
				"		shadow.addPoint(t1.x, t1.y);\r\n" + 
				"		shadow.addPoint(t2.x, t2.y);\r\n" + 
				"		shadow.addPoint(t3.x, t3.y);\r\n" + 
				"		shadow.addPoint(t4.x, t4.y);\r\n" + 
				"		g.setColor(new Color(9,9,9));\r\n" + 
				"		g.fillPolygon(shadow);\r\n" + 
				"	}");
		panel.add(jep);
		frame.add(panel);
		frame.setVisible(true);
	}
}
