package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.zip.CRC32;

public class SendImage {
    public void sendImage(String in, String out) {
        try {
            BufferedImage image = ImageIO.read(new File(in));
            //int[][] hexArray = new int[240][240];
            FileOutputStream fos = new FileOutputStream(out, false);
            CRC32 crc = new CRC32();

            fos.write("PetitHeaderLoveU".getBytes());
            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);
                    
                    fos.write(color.getRed());
                    fos.write(color.getGreen());
                    fos.write(color.getBlue());
                    crc.update(color.getRed() + color.getGreen() + color.getBlue());
             }
            }
            //  write crc to file as int in 4 bytes please
            int crcValue = (int) crc.getValue();
            fos.write((crcValue >> 24) & 0xFF);
            fos.write((crcValue >> 16) & 0xFF);
            fos.write((crcValue >> 8) & 0xFF);
            fos.write(crcValue & 0xFF);

            // print crc
            System.out.println("CRC: " + crcValue);

            fos.write("PetitFootercFini".getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}