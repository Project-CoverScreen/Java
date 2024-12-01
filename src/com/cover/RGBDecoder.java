package com.cover;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;

public class RGBDecoder {
   private static final int IMAGE_SIZE = 240;
   private static final int HEADER_SIZE = 16;
   private static final int FOOTER_SIZE = 16;
   private static final String HEADER = "PetitHeaderLoveU";
   private static final String FOOTER = "PetitFootercFini";

   public void decodeBinFile(String inputPath, String outputPath) throws IOException {
       byte[] fileData = Files.readAllBytes(new File(inputPath).toPath());
       ByteBuffer buffer = ByteBuffer.wrap(fileData);

       // Vérifier l'entête
       byte[] headerBytes = new byte[HEADER_SIZE];
       buffer.get(headerBytes);
       if (!new String(headerBytes).equals(HEADER)) {
           throw new IOException("En-tête invalide");
       }

       // Créer l'image
       BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);

       // Lire les pixels RGB565
       for (int y = 0; y < IMAGE_SIZE; y++) {
           for (int x = 0; x < IMAGE_SIZE; x++) {
               byte msb = buffer.get();
               byte lsb = buffer.get();
               
               int rgb565 = ((msb & 0xFF) << 8) | (lsb & 0xFF);
               
               int red = ((rgb565 >> 11) & 0x1F) * 255 / 31;
               int green = ((rgb565 >> 5) & 0x3F) * 255 / 63;
               int blue = (rgb565 & 0x1F) * 255 / 31;

               int rgb = (red << 16) | (green << 8) | blue;
               image.setRGB(x, y, rgb);
           }
       }

       // Vérifier le pied de page
       byte[] footerBytes = new byte[FOOTER_SIZE];
       buffer.get(footerBytes);
       if (!new String(footerBytes).equals(FOOTER)) {
           throw new IOException("Pied de page invalide");
       }

       // Sauvegarder l'image
       ImageIO.write(image, "PNG", new File(outputPath));
   }

   public static void main(String[] args) {
       try {
           new RGBDecoder().decodeBinFile("test.bin", "output.png");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}