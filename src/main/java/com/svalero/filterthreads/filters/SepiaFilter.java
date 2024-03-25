package com.svalero.filterthreads.filters;

import java.awt.Color;

public class SepiaFilter {

    public static Color apply(Color sourceColor) {
        int red = sourceColor.getRed();
        int green = sourceColor.getGreen();
        int blue = sourceColor.getBlue();

        int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
        int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
        int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

        // Ensure values are within the valid range [0, 255]
        newRed = Math.min(255, Math.max(0, newRed));
        newGreen = Math.min(255, Math.max(0, newGreen));
        newBlue = Math.min(255, Math.max(0, newBlue));

        return new Color(newRed, newGreen, newBlue);
    }
}
