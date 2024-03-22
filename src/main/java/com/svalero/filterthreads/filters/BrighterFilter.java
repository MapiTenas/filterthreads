package com.svalero.filterthreads.filters;

import java.awt.Color;

public class BrighterFilter {

    public static Color apply(Color sourceColor) {
        int brightnessFactor = 50;
        int red = sourceColor.getRed();
        int green = sourceColor.getGreen();
        int blue = sourceColor.getBlue();

        red = Math.min(255, red + brightnessFactor);
        green = Math.min(255, green + brightnessFactor);
        blue = Math.min(255, blue + brightnessFactor);

        Color newColor = new Color (red, green, blue);
        return newColor;
    }
}