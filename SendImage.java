package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
                    
                    // Modification de l'ordre pour garantir RGB correct
                    Integer red = color.getRed();
                    Integer green = color.getGreen();
                    Integer blue = color.getBlue();
                    
                    // Concaténer dans l'ordre RGB
                    int hexValue = red >>16 + green >>8 + blue;
                    // Convertir la chaîne hex en entier
                    hexArray[y][x] = hexValue;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("pixels.bin"))) {
                for (int y = 0; y < 240; y++) {
                    for (int x = 0; x < 240; x++) {
                        writer.write(hexArray[y][x]);
                    }
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
