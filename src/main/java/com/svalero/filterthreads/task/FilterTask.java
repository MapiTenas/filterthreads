package com.svalero.filterthreads.task;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import com.svalero.filterthreads.filters.BrighterFilter;
import com.svalero.filterthreads.filters.GrayscaleFilter;

import javafx.concurrent.Task;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class FilterTask extends Task<BufferedImage> {

    private File sourceImage;
    private List<String> selectedFilters;

    public FilterTask(File sourceImage, List<String> selectedFilters) throws MalformedURLException {
        this.sourceImage = sourceImage;
        this.selectedFilters = selectedFilters;
    } 

    @Override
    protected BufferedImage call() throws Exception {
        int totalProcessedPixels = 0;
        updateMessage("Initializing filter");
        BufferedImage image = ImageIO.read(this.sourceImage);
        int imageSize = image.getHeight() * image.getWidth(); //This allows us to calculate the total size of the image 
        float totalProcessed = 0f;

        for (int y= 0; y < image.getHeight(); y++){
            Thread.sleep(20);
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x,y)); //Original color

                for (String selectedFilter : this.selectedFilters) {
                    if (selectedFilter.equals("Grayscale"))
                        color = GrayscaleFilter.apply(color);
                    if (selectedFilter.equals("Brighter"))
                        color = BrighterFilter.apply(color);   
                }
                if (color != null)
                    image.setRGB(x, y, color.getRGB());

                    totalProcessedPixels++;
                updateProgress(totalProcessedPixels, imageSize);
                totalProcessed = totalProcessedPixels / (float) imageSize;
                String totalProcessedFormatted = String.format("%.2f", 100 * totalProcessed);
                updateMessage(totalProcessedFormatted + " %");
            } 
        }
        

        updateProgress(totalProcessedPixels, imageSize);
        updateMessage("100%");
        return image;   
    }
    
}
