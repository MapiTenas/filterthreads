package com.svalero.filterthreads.filters;

import java.awt.Color;

public class GrayscaleFilter {

    public static Color apply(Color sourceColor){
        int red = sourceColor.getRed();
        int green = sourceColor.getGreen();
        int blue = sourceColor.getBlue();

        int gray = (red + green + blue) / 3;

        Color newColor = new Color(gray, gray, gray);
        return newColor;
    }
    
}
