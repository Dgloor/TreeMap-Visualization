/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miscellaneous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author Ac 001
 */
public class ColorAssigner {

    public static ArrayList<String> commonExtensions = createExtensions();
    public static HashMap<String, Color> colorMap = fullColorMap();

    public ColorAssigner() {
        fullColorMap();
    }

    public static Color getColor(String filename) {
        if (filename.contains(".")) {
            String extension = filename.split("\\.")[1];
            extension = extension.toLowerCase();
            if (!colorMap.keySet().contains(extension)) {
                setColor(extension, colorMap);
            }
            return colorMap.get(extension);
        }
        return null;
    }

    private static ArrayList<String> createExtensions() {
        if (commonExtensions == null) {
            ArrayList<String> extensionsArray = new ArrayList<>();
            addCommonExtensions(extensionsArray);
            return extensionsArray;
        } else {
            return commonExtensions;
        }
    }

    private static HashMap<String, Color> fullColorMap() {
        HashMap<String, Color> map = new HashMap<>();
        commonExtensions.forEach((extension) -> {
            setColor(extension, map);
        });
        return map;
    }

    private static void setColor(String extension, HashMap<String, Color> map) {
        extension = extension.toLowerCase();
        Random rand = new Random();
        map.put(extension, Color.color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
    }

    private static void addCommonExtensions(ArrayList<String> array) {
        //Imagenes
        array.add(".jpg");
        array.add(".png");
        array.add(".gif");
        array.add(".bmp");
        //texto
        array.add(".txt");
        array.add(".doc");
        array.add(".docx");
        //video
        array.add(".avi");
        array.add(".mp4");
        array.add(".mpeg");
        array.add(".mwv");
        //audio
        array.add(".mp3");
        array.add(".wav");
        array.add(".wma");
        //archivo comprimido
        array.add(".zip");
        array.add(".rar");
        array.add(".tar");
        //lectura
        array.add(".pdf");
        array.add(".epub");
        array.add(".azw");
        array.add(".ibook");
        //imagen de disco
        array.add(".iso");
        array.add(".mds");
        array.add(".img");
    }
}
