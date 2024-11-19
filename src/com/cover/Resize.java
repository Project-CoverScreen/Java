package com.cover;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Resize {

    public void Image() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("test.jpeg"));
            BufferedImage resizedImage = resizeImage(originalImage, 240, 240);
            BufferedImage rgbImage = convertToRGB(resizedImage);
            BufferedImage eightBitImage = convertTo8Bit(rgbImage);
            ImageIO.write(eightBitImage, "bmp", new File("eightbit2.bmp"));
            //compressImage(eightBitImage, "resize.png", 0.75f);
            System.out.println("Image redimensionnée, convertie en 8 bits et compressée avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors du redimensionnement, de la conversion ou de la compression de l'image.");
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

    private static BufferedImage convertToRGB(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    private static BufferedImage convertTo8Bit(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    @SuppressWarnings("unused")
    private static void compressImage(BufferedImage image, String outputPath, float quality) throws IOException {
        File outputFile = new File(outputPath);
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("png").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();

        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(quality);

        try (ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile)) {
            jpgWriter.setOutput(outputStream);
            IIOImage outputImage = new IIOImage(image, null, null);
            jpgWriter.write(null, outputImage, jpgWriteParam);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            jpgWriter.dispose();
        }
    }

}