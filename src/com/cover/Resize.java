package com.cover;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Resize {

    public void Image(String in, String out) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(in));
            BufferedImage resizedImage = resizeImage(originalImage, 240, 240);
            ImageIO.write(resizedImage, "bmp", new File(out));
            System.out.println("Image redimensionnée.");
        } catch (IOException e) {
            System.out.println("Erreur lors du redimensionnement de l'image.");
            e.printStackTrace();
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

}