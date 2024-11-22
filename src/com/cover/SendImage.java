package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
//import java.util.zip.CRC32;

public class SendImage {
    public void sendImage(String in, String out) {
        try {
            BufferedImage image = ImageIO.read(new File(in));
            //int[][] hexArray = new int[240][240];
            FileOutputStream fos = new FileOutputStream(out, false);
            //CRC32 crc = new CRC32();

            fos.write("PetitHeaderLoveU".getBytes());
            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);

                    int red = (color.getRed());
                    int green = (color.getGreen());
                    int blue = color.getBlue();
                    if (x == 1 && y == 1) {
                        System.err.println("red: " + red + " green: " + green + " blue: " + blue);
                    }
                    fos.write(red);
                    fos.write(green);
                    fos.write(blue);
                }
            }
            fos.write("PetitFootercFini".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}