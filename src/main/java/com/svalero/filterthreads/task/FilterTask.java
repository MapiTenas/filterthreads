package com.svalero.filterthreads.task;

import java.io.File;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import com.svalero.filterthreads.filters.BrighterFilter;
import com.svalero.filterthreads.filters.GrayscaleFilter;

import javafx.concurrent.Task;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class FilterTask extends Task<BufferedImage> {

    private File sourceImage;
    private String selectedFilter;

    public FilterTask(File sourceImage, String selectedFilter) throws MalformedURLException {
        this.sourceImage = sourceImage;
        this.selectedFilter = selectedFilter;
    } 

    @Override
    protected BufferedImage call() throws Exception {
        int totalProcessedPixels = 0;
        updateMessage("Initializing filter");
        BufferedImage image = ImageIO.read(this.sourceImage);
        int imageSize = image.getHeight() * image.getWidth(); //This allows us to calculate the total size of the image 
        float totalProcessed = 0f;
        for(int y = 0; y < image.getHeight(); y++) {
            Thread.sleep(10);
            for(int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                if (selectedFilter.equals("Grayscale"))
                    image.setRGB(x, y, GrayscaleFilter.apply(color).getRGB());
                if (selectedFilter.equals("Brighter"))
                    image.setRGB(x, y, BrighterFilter.apply(color).getRGB());  
                
                totalProcessedPixels++;
                updateProgress(totalProcessedPixels, imageSize);
                totalProcessed = totalProcessedPixels / (float)imageSize;
                String totalProcessedFormatted = String.format("%.2f", 100 * totalProcessed);
                updateMessage(totalProcessedFormatted + " %");
            }

        } 
        //To do: improve this
        String outputName = this.sourceImage.getName().substring(0, this.sourceImage.getName().length() - 4) + "_" + selectedFilter + ".png"; 
        File output = new File(outputName);
        ImageIO.write(image, "png", output); 
        updateProgress(totalProcessedPixels, imageSize);
        updateMessage("100%");
        return image;   
    }
    
}
