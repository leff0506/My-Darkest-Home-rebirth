package engine.imagework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;




import java.awt.Graphics;

import java.awt.Image;



import javax.swing.JPanel;



public class ImagePanel extends JPanel{

	 private BufferedImage image;

	 

	 public Image getImage() {

	     return image;

	 }

	

	 public void setImage(BufferedImage image) {

	     this.image = image;

	 }

	 

	 public void paintComponent(Graphics g) {

//	     super.paintComponent(g);

	     if(image != null){

	         g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	     }

	 }

}
