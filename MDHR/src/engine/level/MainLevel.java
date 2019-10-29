package engine.level;

import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.audio.AudioManager;
import engine.imagework.RO;
import engine.imagework.Textures;
import engine.objects.Battery;
import engine.objects.Coll;
import engine.player.Player;
import engine.trig.AudioTrigger;
import engine.trig.CodeTrig;

public class MainLevel extends Level{
	private ArrayList<Point> points= new ArrayList<>();
	
	public MainLevel() {
		Textures.uploadS("table1", new File("./img/inter/table1.png"));
		Textures.uploadS("wall5x1", new File("./img/inter/wall5x1.png"));
		Textures.uploadS("wall1x2", new File("./img/inter/wall1x2.png"));
		Textures.uploadS("bat", new File("./img/map/bat.png"));
		AudioManager.clip();

		player = new Player(Toolkit.getDefaultToolkit().getScreenSize().width/2-25,Toolkit.getDefaultToolkit().getScreenSize().height/2-25,45,45,0,new File("img/player/anim"),this);
		aud.add(new AudioTrigger(0, 0,3000,3000, 0, "bg", new File("./wav/horror/bg.wav"),50));
		aud.add(new AudioTrigger(700, 300, 620, 620, 0, "shopot", new File("./wav/horror/shopot_cut.wav"),20));
//		aud.add(new AudioTrigger(900, 1300, 620, 620, 0, "shopot",30));
		aud.add(new AudioTrigger(1410, 800, 300, 40, 0, "blood", new File("./wav/horror/blood.wav"),30));
		aud.add(new AudioTrigger(900, 1500, 40, 300, 0, "scream", new File("./wav/horror/screamer_cut.wav"),30));
		aud.add(new AudioTrigger(1900, 1500, 40, 180, 0, "louch", new File("./wav/horror/lough_cut.wav"),30));
		aud.add(new AudioTrigger(1900, 2100, 40, 180, 0, "louch",new File("./wav/horror/lough_cut.wav"), 30));
//		co = new CodeTrig(700, 700, 50, 50, 0, false,new File("img/map/code.png"));
		bag.add(new RO(0,0,2500,3000,0,false,new File("./img/map/gl.png")));
		bat.add(new Battery(850,700,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(850,900,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(1650,400,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(1610,1200,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(1410,1200,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(300,1400,15,25,0,Textures.texS.get("bat")));
		bat.add(new Battery(2020,2020,15,25,0,Textures.texS.get("bat")));
//		map.add(new RO(700,700,55,70,0,false,Textures.texS.get("table1")));
//		map.add(new RO(600,700,55,70,0,false,Textures.texS.get("table1")));
//		map.add(new RO(500,700,55,70,0,false,Textures.texS.get("table1")));
//		map.add(new RO(400,700,55,70,0,false,Textures.texS.get("table1")));
		player.moveTo(600, 350);

		addCollision();
//		tens.add(new RO(300,600,505,30,0,true,Textures.texS.get("wall5x1")));
//		tens.add(new RO(300,630,30,202,0,true,Textures.texS.get("wall1x2")));
//		tens.add(new RO(300,832,505,30,0,true,Textures.texS.get("wall5x1")));
//		
		
	}
	private void addCollision() {
		
		int x=770,y=325;
		int w=63,h=1325;
		Coll left_left= new Coll(x,y,w,h,0,true,true);
		w=550;
		h=73;
		Coll left_up = new Coll(x,y,w,h,0,true,true);
		x=800;
		y=996;
		w=540;
		h=15;
		Coll middle_left_thin = new Coll(x,y,w,h,0,true,true);
		x=1310;
		y=325;
		w=40;
		h=353;
		Coll middle_up_thin = new Coll(x,y,w,h,0,true,true);
		x=1310;
		y=772;
		w=40;
		h=547;
		Coll middle_up_thin1 = new Coll(x,y,w,h,0,true,true);
		x=1145;
		y=772;
		w=15;
		h=106;
		Coll left_up_tiny = new Coll(x,y,w,h,0,true,true);
		x=1145;
		y=772;
		w=200;
		h=15;
		Coll left_up_tiny1 = new Coll(x,y,w,h,0,true,true);
		x=1145;
		y=955;
		w=16;
		h=304;
		Coll left_up_tiny2 = new Coll(x,y,w,h,0,true,true);
		points.clear();
		x=1320;
		y=300;
		points.add(new Point(x,y));
		points.add(new Point(x+200,y-200));
		points.add(new Point(x+200,y-83));
		points.add(new Point(x,y+130));
		
		Coll nakl = new Coll(0,0,w,h,0,true,true);
		nakl.setBox(points);
		
		x=1400;
		y=190;
		w=400;
		h=50;
		Coll up_up = new Coll(x,y,w,h,0,true,true);
		
		
		points.clear();
		x=1900;
		y=300;
		points.add(new Point(x,y));
		points.add(new Point(x-200,y-200));
		points.add(new Point(x-200,y-78));
		points.add(new Point(x-40,y+100));
		
		Coll up_right_nakl=new Coll(0,0,w,h,0,true,true);
		up_right_nakl.setBox(points);
		
		x=1850;
		y=320;
		w=600;
		h=80;
		Coll up_right = new Coll(x,y,w,h,0,true,true);
		
		
		x=1350;
		y=655;
		w=80;
		h=15;
		Coll middle_small1 = new Coll(x,y,w,h,0,true,true);
		x=1504;
		y=655;
		w=550;
		h=15;
		Coll middle_small2 = new Coll(x,y,w,h,0,true,true);
		
		x=1880;
		y=642;
		w=15;
		h=20;
		Coll middle_tiny = new Coll(x,y,w,h,0,true,true);
		
		x=1880;
		y=400;
		w=15;
		h=170;
		Coll middle_up_thin2 = new Coll(x,y,w,h,0,true,true);
		
		x=2048;
		y=400;
		w=18;
		h=352;
		Coll middle_up_thin3 = new Coll(x,y,w,h,0,true,true);
		x=2373;
		y=400;
		w=20;
		h=1100;
		Coll big_right = new Coll(x,y,w,h,0,true,true);
		
		x=2047;
		y=836;
		w=18;
		h=135;
		Coll middle_up_thin4 = new Coll(x,y,w,h,0,true,true);
		
		x=2045;
		y=1053;
		w=22;
		h=450;
		Coll middle_up_thin5 = new Coll(x,y,w,h,0,true,true);
		
		
		x=2050;
		y=900;
		w=400;
		h=57;
		Coll big_middle_right = new Coll(x,y,w,h,0,true,true);
		
		x=1857;
		y=912;
		w=40;
		h=600;
		Coll big_middle = new Coll(x,y,w,h,0,true,true);
		
		x=1696;
		y=912;
		w=200;
		h=49;
		Coll big_middle_hor = new Coll(x,y,w,h,0,true,true);
		
		x=1896;
		y=1070;
		w=79;
		h=22;
		Coll middle_tiny_right = new Coll(x,y,w,h,0,true,true);
		
		
		x=1537;
		y=1475;
		w=1300;
		h=47;
		Coll big_big_middle = new Coll(x,y,w,h,0,true,true);
		
		x=1559;
		y=1330;
		w=300;
		h=20;
		Coll tiny_middle_hor = new Coll(x,y,w,h,0,true,true);
		
		x=1559;
		y=1330;
		w=20;
		h=27;
		Coll tiny__tiny_middle = new Coll(x,y,w,h,0,true,true);
		
		x=1559;
		y=1429;
		w=21;
		h=50;
		Coll tiny__tiny_middle1 = new Coll(x,y,w,h,0,true,true);
		
		x=1150;
		y=1240;
		w=20;
		h=17;
		Coll tiny__tiny_middle2 = new Coll(x,y,w,h,0,true,true);
		
		x=1237;
		y=1240;
		w=75;
		h=17;
		Coll tiny__tiny_middle3 = new Coll(x,y,w,h,0,true,true);
		
		x=1310;
		y=1405;
		w=40;
		h=250;
		Coll big_middle_left = new Coll(x,y,w,h,0,true,true);
		
		x=1350;
		y=1477;
		w=23;
		h=42;
		Coll big_middle_left_tiny = new Coll(x,y,w,h,0,true,true);
		
		x=770;
		y=1627;
		w=580;
		h=28;
		Coll big_middle_left_vert = new Coll(x,y,w,h,0,true,true);
		
		x=335;
		y=1500;
		w=110;
		h=1000;
		Coll big_down_left_vert = new Coll(x,y,w,h,0,true,true);
		
		x=331;
		y=1435;
		w=500;
		h=90;
		Coll big_down_left_hor = new Coll(x,y,w,h,0,true,true);
		
		x=440;
		y=1800;
		w=500;
		h=90;
		super.taqnaya = new Coll(x,y,w,h,0,true,false);
		
		points.clear();
		points.add(new Point(x,y));
		points.add(new Point(x+189,y));
		points.add(new Point(x+189,y-135));
		points.add(new Point(x+189+30,y-135-30));
		points.add(new Point(x+189+30+125,y-135-30));
		points.add(new Point(x+189+30+125,y-135-30+20));
		points.add(new Point(x+189+30+10,y-135-30+20));
		points.add(new Point(x+189+30+10-30+6,y-135-30+20+30-5));
		points.add(new Point(x+189+30+10-30+6,y-30+20+30));
		points.add(new Point(x,y-30+20+30));
		super.taqnaya.setBox(points);
		
		
		x=450;
		y=2240;
		w=500;
		h=90;
		Coll corner_down_left = new Coll(x,y,w,h,0,true,true);
		points.clear();
		points.add(new Point(x+5-10,y-8-10));
		points.add(new Point(x+195,y+200));
		points.add(new Point(x,y+200));
		corner_down_left.setBox(points);
		
		
		x=1241;
		y=2240;
		w=500;
		h=90;
		Coll corner_down_right = new Coll(x,y,w,h,0,true,true);
		points.clear();
		points.add(new Point(x-3+20,y-8-20));
		points.add(new Point(x-195,y+200));
		points.add(new Point(x,y+200));
		corner_down_right.setBox(points);
		
		
		x=546;
		y=2395;
		w=700;
		h=90;
		Coll down_down_left = new Coll(x,y,w,h,0,true,true);
		
		x=1310;
		y=1796;
		w=40;
		h=547;
		Coll down_left_vert = new Coll(x,y,w,h,0,true,true);
		
		x=1235;
		y=2000-6;
		w=40;
		h=547;
		Coll down_left_vert1 = new Coll(x,y,w,h,0,true,true);
		
		x=1274;
		y=1929;
		w=590;
		h=80;
		Coll down_left_hor = new Coll(x,y,w,h,0,true,true);
		
		x=2770;
		y=1500;
		w=90;
		h=1500;
		Coll down_right_big = new Coll(x,y,w,h,0,true,true);
		
		x=2243;
		y=1520;
		w=33;
		h=210;
		Coll down_right_thin = new Coll(x,y,w,h,0,true,true);
		
		x=2325;
		y=1826;
		w=18;
		h=570;
		Coll down_right_thin1 = new Coll(x,y,w,h,0,true,true);
		
		x=2226;
		y=1826;
		w=100;
		h=18;
		Coll down_right_thin2 = new Coll(x,y,w,h,0,true,true);
		
		

		x=2326;
		y=2165;
		w=500;
		h=19;
		Coll down_right_thin3 = new Coll(x,y,w,h,0,true,true);
		
		x=2467;
		y=2180;
		w=15;
		h=250;
		Coll down_right_thin4 = new Coll(x,y,w,h,0,true,true);
		
		x=2320;
		y=2398;
		w=70;
		h=15;
		Coll down_right_thin5 = new Coll(x,y,w,h,0,true,true);
		
		x=2312;
		y=2403;
		w=20;
		h=15;
		Coll down_right_thin6 = new Coll(x,y,w,h,Math.toRadians(-30),true,true);
		
		x=2453;
		y=2399;
		w=18;
		h=15;
		Coll down_right_thin7 = new Coll(x,y,w,h,0,true,true);
		
		x=2153-5;
		y=2389+5-1;
		w=20;
		h=200;
		Coll down_right_thin8 = new Coll(x,y,w,h,Math.toRadians(62),true,true);
		
		x=2153-5+100-20;
		y=2389+5-1+150+20;
		w=20;
		h=180;
		Coll down_right_thin9 = new Coll(x,y,w,h,Math.toRadians(62),true,true);
		
		x=2153-5+100-20+100+100-5;
		y=2389+5-1+150+20-60+5;
		w=16;
		h=100;
		Coll down_right_thin10 = new Coll(x,y,w,h,Math.toRadians(62),true,true);
		
		x=2467;
		y=2508;
		w=13;
		h=20;
		Coll down_right_thin11 = new Coll(x,y,w,h,0,true,true);
		
		
		
		x=2467;
		y=2523;
		w=400;
		h=20;
		Coll down_right_thin12 = new Coll(x,y,w,h,0,true,true);
		x=2467;
		y=2633;
		w=50;
		h=800;
		Coll down_down = new Coll(x,y,w,h,Math.toRadians(63),true,true);
		
		x=1872;
		y=1929;
		w=2000;
		h=20;
		Coll down_down_big = new Coll(x,y,w,h,0,true,true);
		points.clear();
		points.add(new Point(x-3,y));
		points.add(new Point(x+500-20+4-3,y+1130+4+5+5+5));
		points.add(new Point(x+300,y+1000));
		points.add(new Point(x,y+1000));
		
		down_down_big.setBox(points);
		

		
	
		col.add(down_down_big);
		col.add(down_down);
		col.add(down_right_thin12);
		col.add(down_right_thin11);
		col.add(down_right_thin10);
		col.add(down_right_thin9);
		col.add(down_right_thin8);
		col.add(down_right_thin7);
		col.add(down_right_thin6);
		col.add(down_right_thin5);
		col.add(down_right_thin4);
		col.add(down_right_thin3);
		col.add(down_right_thin2);
		col.add(down_right_thin1);
		col.add(down_right_thin);
		col.add(down_right_big);
		col.add(down_left_hor);
		col.add(down_left_vert1);
		col.add(down_left_vert);
		col.add(down_down_left);
		col.add(corner_down_right);
		col.add(corner_down_left);
		col.add(super.taqnaya);
		col.add(big_down_left_hor);
		col.add(big_down_left_vert);
		col.add(big_middle_left_vert);
		col.add(big_middle_left_tiny);
		col.add(big_middle_left);
		col.add(tiny__tiny_middle);
		col.add(tiny__tiny_middle1);
		col.add(tiny__tiny_middle2);
		col.add(tiny__tiny_middle3);
		col.add(tiny_middle_hor);
		col.add(big_big_middle);
		col.add(middle_tiny_right);
		col.add(big_middle_hor);
		col.add(big_middle);
		col.add(big_middle_right);
		col.add(big_right);
		col.add(middle_up_thin5);
		col.add(middle_up_thin4);
		col.add(middle_up_thin3);
		col.add(middle_up_thin2);
		col.add(middle_tiny);
		col.add(middle_small1);
		col.add(middle_small2);
		col.add(up_right);
		col.add(up_right_nakl);
		col.add(up_up);
		col.add(nakl);
		col.add(left_left);
		col.add(left_up);
		col.add(middle_left_thin);
		col.add(middle_up_thin1);
		col.add(middle_up_thin);
		col.add(left_up_tiny);
		col.add(left_up_tiny1);
		col.add(left_up_tiny2);
	}
}
