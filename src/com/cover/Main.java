package com.cover;

public class Main {

    public static void main(String[] args) {

        Resize resize = new Resize();
        resize.Image();
        SendImage sendImage = new SendImage();
        sendImage.sensImage();
        // AsciiArt asciiArt = new AsciiArt();
        // asciiArt.asciiArt();
        CRC crc = new CRC();
        System.err.println("CRC : " + crc.getCRCAsHex());

    }

}