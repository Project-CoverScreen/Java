package com.cover;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import javax.imageio.ImageIO;

public class SendImage {
    public void sendImage(String in, String out) {
        try {
            BufferedImage image = ImageIO.read(new File(in));
            FileOutputStream fos = new FileOutputStream(out, false);
            CRC32 crc = new CRC32();

            fos.write("PetitHeaderLoveU".getBytes());
            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);


                    int red = (rgb >> 16) & 0xFF; 
                    int green = (rgb >> 8) & 0xFF; 
                    int blue = rgb & 0xFF;        
                    
                    int red5 = (red >> 3) & 0x1F;    // 5 bits
                    int green6 = (green >> 2) & 0x3F; // 6 bits
                    int blue5 = (blue >> 3) & 0x1F;   // 5 bits
                    
                    int rgb565 = (red5 << 11) | (green6 << 5) | blue5;
                    
                    // little endian important
                    fos.write(rgb565 & 0xFF);       
                    fos.write((rgb565 >> 8) & 0xFF);

                    // Update CRC
                    crc.update((rgb565 >> 8) & 0xFF);
                    crc.update(rgb565 & 0xFF);
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
