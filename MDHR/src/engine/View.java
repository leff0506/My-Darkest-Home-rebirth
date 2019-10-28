package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

public class View extends JPanel {




    private AffineTransform transform;

    public static boolean run = true;

    private Polygon pol = new Polygon();

    public View() {

        super();

    }

    @Override

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.drawString("lol",15,15);
    }

    public void update(long time) {

    }

}
