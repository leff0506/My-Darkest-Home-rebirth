package engine.imagework;

import java.awt.Image;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;


import javax.imageio.ImageIO;

public class Textures {
    public static HashMap<String, Image> texS = new HashMap<>();//s - static
    public static HashMap<String, ArrayList<Image>> texD = new HashMap<>();//d -dynamic

    private Textures() {
    }

    public static void uploadPlayer(File dir) {
        uploadD("player", dir);
    }

    public static void uploadD(String name, File dir) {//Dynamic Textures
        ArrayList<Image> temp = new ArrayList<>();
        File[] files = dir.listFiles();
        temp.clear();
        for (File f : files) {
            try {
                temp.add(ImageIO.read(f));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (texD.containsKey(name)) {
            texD.replace(name, temp);
        } else {
            texD.put(name, temp);
        }
    }

    public static void uploadS(String name, File file) {//Static textures
        Image im;
        try {
            im = ImageIO.read(file);
            if (texD.containsKey(name)) {
                texS.replace(name, im);
            } else {
                texS.put(name, im);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}