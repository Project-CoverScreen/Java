package com.cover;

import java.io.*;
import java.util.zip.CRC32;

public class CRC {
    private static final String DEFAULT_FILENAME = "pixels.bin";
    private static final int BUFFER_SIZE = 204800;

    public long crc() {
        return crc(DEFAULT_FILENAME);
    }

    public long crc(String fileName) {
        try {
            return calculateCRC(fileName);
        } catch (IOException e) {
            System.err.printf("Erreur lors du calcul du CRC pour %s : %s%n",
                    fileName, e.getMessage());
            return -1;
        }
    }

    private static long calculateCRC(String filePath) throws IOException {
        CRC32 crc = new CRC32();

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(filePath), BUFFER_SIZE)) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                crc.update(buffer, 0, bytesRead);
            }

            return crc.getValue();
        }
    }

    public String getCRCAsHex() {
        return String.format("%08x", crc(DEFAULT_FILENAME));
    }
}