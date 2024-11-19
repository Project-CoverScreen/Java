package com.cover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AsciiArt {
    public void asciiArt() {
        try {
            BufferedImage image = ImageIO.read(new File("eightbit.bmp"));

            int[][] hexArray = new int[240][240];

            for (int y = 0; y < 240; y++) {
                for (int x = 0; x < 240; x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);

                    // Modification de l'ordre pour garantir RGB correct
                    int redHex = Integer.parseInt(String.format("%02x", color.getRed()), 16);
                    int greenHex = Integer.parseInt(String.format("%02x", color.getGreen()), 16);
                    int blueHex = Integer.parseInt(String.format("%02x", color.getBlue()), 16);

                    // Concaténer dans l'ordre RGB
                    int hexValue = redHex + greenHex + blueHex;
                    // Convertir la chaîne hex en entier
                    hexArray[y][x] = hexValue;
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("AsciiArt.bin"))) {
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
