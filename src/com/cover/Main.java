package com.cover;

public class Main {

    public static void main(String[] args) {

        Resize resize = new Resize();
        resize.Image("test.jpeg", "eightbit.bmp");
        SendImage sendImage = new SendImage();
        sendImage.sendImage("eightbit.bmp", "pixels.bin");
    }

}