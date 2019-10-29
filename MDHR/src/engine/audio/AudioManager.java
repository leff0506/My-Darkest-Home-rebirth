package engine.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes.Name;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;



public class AudioManager {
//	public static Player p;

	private static Map<String,AudioInputStream> audios = new HashMap<>();


	private static AudioListener list;
	public static void clip() {

		list= new AudioListener();
			 
			  

	}

/**
 * 
 * @param str
 * @param vol 1..100
 */
	public static synchronized void play(String str,double vol) {
        try {
	        if(audios.containsKey(str)) {
				System.out.println("try to play");
				AudioFormat format = audios.get(str).getFormat();
		        DataLine.Info info = new DataLine.Info(Clip.class, format);
		        Clip clip = (Clip)AudioSystem.getLine(info);
		        clip.open(audios.get(str));
		        if(str.equals("shopot"))clip.loop(clip.LOOP_CONTINUOUSLY);
		        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				double gain = vol/100.0;
				float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
				gainControl.setValue(dB);
		    	clip.addLineListener(list);
				clip.loop(0);
		        clip.start();
			}	 
        	
		} catch (LineUnavailableException | IOException   e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void upload(String name,AudioInputStream a) {
		audios.put(name, a);
	}
}
