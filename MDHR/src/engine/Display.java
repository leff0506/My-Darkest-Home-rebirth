package engine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.midi.SysexMessage;
import javax.swing.*;

import engine.audio.AudioManager;
import engine.imagework.ImagePanel;
import engine.imagework.RO;
import engine.imagework.View;
import engine.input.Input;
import engine.level.Level;

public class Display extends JFrame {
	private View display;
	public Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	public boolean running;
	private Level lvl;
	private ArrayList<Component> com = new ArrayList<>();
	BufferedImage pl=null,pl1=null,ex=null,ex1=null;
	Display t;
	JPanel panel ;
	public Display(Level lvl) {
		super();
		t=this;
		this.lvl=lvl;
		setSize(size);
		setLocation(0,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		
//		setAlwaysOnTop(true);
//		start();
		
		menu();
		
	}
	private void menu(){
		panel = new JPanel();
		panel.setBounds(0,0,size.width,size.height);
		panel.setLayout(null);
		ImagePanel p = new ImagePanel();
		p.setBounds(0, 0, size.width, size.height);
		try {
			p.setImage(ImageIO.read(new File("img/menu/1.jpg")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			pl = ImageIO.read(new File("img/menu/Play1.png"));
			pl1 = ImageIO.read(new File("img/menu/Play2.png"));
			ex = ImageIO.read(new File("img/menu/Exit1.png"));
			ex1 = ImageIO.read(new File("img/menu/Exit2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImagePanel play = new ImagePanel();
		play.setBounds(40, size.height/2, 300, 100);
		play.setImage(pl);
		play.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				play.setImage(pl);
				t.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				play.setImage(pl1);
				t.repaint();
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				start();
			}
		});
		panel.add(play);
		panel.add(p);
		ImagePanel exit = new ImagePanel();
		exit.setBounds(40, size.height/2+120, 300, 100);
		exit.setImage(ex);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				exit.setImage(ex);
				t.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setImage(ex1);
				t.repaint();
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});
		panel.add(play);
		panel.add(exit);
		panel.add(p);
		add(panel);
		com.add(play);
		com.add(exit);
		com.add(p);
		setVisible(true);
		
	}
	private void start() {
		
		
		 getContentPane().removeAll();
		 repaint();
		addKeyListener(new Input());
		display = new View();
		
		display.setLayout(null);
		display.setSize(size);
		display.setBackground(new Color(9,9,9));
		add(display);
		
		repaint();
		display.setLevel(lvl);
		run();
	}
	private void run() {
		running = true;
		Thread th = new Thread(()->{
			long was = System.currentTimeMillis();
//			long fps = System.currentTimeMillis();
//			int amFps=0;
			long is;
			while(true) {
				is = System.currentTimeMillis();
				if(is-was>=1000/60.0) {
					
					
					updateInput();
					display.update(is-was);
					repaint();
					was=is;
//					amFps++;
				}
//				if(is-fps>=1000) {
//					System.out.println(amFps);
//					fps=is;
//					amFps=0;
//				}
			}
		},"Game loop");
		th.start();
	}
	private void updateInput() {
		if(Input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			setVisible(false);
			dispose();
			System.exit(0);
		}
	}
	
}
