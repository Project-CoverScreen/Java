package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SendImage {
    public void sensImage() {
        try {
            BufferedImage image = ImageIO.read(new File("eightbit.bmp"));
            int[][] hexArray = new int[240][240];

            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);

                    // Création directe de la valeur RGB en hexa
                    hexArray[y][x] = (color.getRed() << 16) |
                            (color.getGreen() << 8) |
                            (color.getBlue());
                }
            }

            try (FileOutputStream fos = new FileOutputStream("pixels.bin")) {
                byte[] header = "PetitHeaderloveU".getBytes();
                fos.write(header);
                for (int y = 0; y < 240; y++) {
                    for (int x = 0; x < 240; x++) {
                        // Écriture des 3 octets RGB
                        int rgb = hexArray[y][x];
                        fos.write((rgb >> 16) & 0xFF); // R
                        fos.write((rgb >> 8) & 0xFF); // G
                        fos.write(rgb & 0xFF); // B
                    }
                }
                byte[] footer = "clafinfooteryes!".getBytes();
                fos.write(footer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}