package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SendImage {
    public void sendImage(String in, String out) {
        try {
            BufferedImage image = ImageIO.read(new File(in));
            int[][] hexArray = new int[240][240];
            FileOutputStream fos = new FileOutputStream(out, false);
            
            fos.write("PetitHeaderLoveU".getBytes());
            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);
                    //System.err.println(rgb);
                    Color color = new Color(rgb);
                    System.err.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
                    
                    hexArray[y][x] = (color.getRed() & 0xFF) << 16 |
                    (color.getGreen() & 0xFF) << 8 |
                    (color.getBlue() & 0xFF);

                    fos.write((rgb >> 16) & 0xFF); // R
                    fos.write((rgb >> 8) & 0xFF); // G
                    fos.write(rgb & 0xFF); // B
                }
            }
            fos.write("PetitFootercFini".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}