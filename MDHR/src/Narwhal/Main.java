package Narwhal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import engine.Display;
import engine.level.Level;
import engine.level.MainLevel;

public class Main {

	public static void main(String[] args) {
		File error = new File("error.log");
		try {
			error.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter w=null;
		try {
			w= new PrintWriter(error);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MainLevel lvl = new MainLevel();
			Display display= new Display(lvl);
		}catch(Exception e) {
			w.append(e.getMessage());
			w.flush();
			w.close();
		}
		

	}

}
