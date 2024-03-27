package com.svalero.filterthreads.utils;

public class ImageChecker {
    public static boolean imageChecker(String fileName){
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
    }
    
}
