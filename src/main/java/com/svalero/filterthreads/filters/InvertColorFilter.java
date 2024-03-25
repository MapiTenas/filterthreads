package com.svalero.filterthreads.filters;
import java.awt.Color;

public class InvertColorFilter {

    public static Color apply(Color sourceColor) {
        int red = sourceColor.getRed();
        int green = sourceColor.getGreen();
        int blue = sourceColor.getBlue();

        int newRed = 255 - red;
        int newGreen = 255 - green;
        int newBlue = 255 - blue;

        Color newColor = new Color(newRed, newGreen, newBlue);
        return newColor;
    }
}