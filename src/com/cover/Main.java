package com.cover;

public class Main {

    public static String Photo = "photo/";
    public static String Resize = "Resize/";
    public static String Bin = "Bin/";
    public static String View = "View/";

    public static String jpeg = ".jpg";
    public static String bmp = ".bmp";
    public static String bin = ".bin";

    public static String in = "1";
    public static String mid = "resize";

    public static void main(String[] args) {

        for (int i = 1; i < 2; i++) {
            in = Integer.toString(i);
            resizeImage();
        }
    }

    public static void resizeImage() {

        Resize resize = new Resize();
        resize.Image((Photo + in + jpeg), (Resize + mid + bmp));
        SendImage sendImage = new SendImage();
        sendImage.sendImage((Resize + mid + bmp), (Bin + in + bin));
    }
}