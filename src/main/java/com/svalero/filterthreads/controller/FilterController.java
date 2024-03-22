package com.svalero.filterthreads.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


import com.svalero.filterthreads.task.FilterTask;

import javafx.concurrent.Worker;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilterController implements Initializable {

    @FXML
    private ImageView initialImage;
    @FXML
    private ImageView finalImage;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelProgressStatus;


    private File sourceImage;
    private String selectedFilter;
    private FilterTask filterTask;

    //Class constructor
    public FilterController (File sourceImage, String selectedFilter) {
        this.sourceImage = sourceImage;
        this.selectedFilter = selectedFilter;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InputStream stream;
        try {
            stream = new FileInputStream(sourceImage.getAbsolutePath());
            Image image = new Image(stream);
            this.initialImage.setImage(image); //Here we load the original image. 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            filterTask = new FilterTask(this.sourceImage, this.selectedFilter);
        
            /*We bind the progress bar to the progress of the task*/
            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(filterTask.progressProperty());
            
            /*States of the task. This also launch a pop up when the task has succedded successfully.*/
            filterTask.stateProperty().addListener((observableValue, oldState, newState)-> {
               if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("The filter has finished successfully.");
                    alert.show();
                }
            });
        

            filterTask.setOnSucceeded(event -> {
                BufferedImage outputImage = filterTask.getValue();
                Image image = SwingFXUtils.toFXImage(outputImage, null);

                this.finalImage.setImage(image); //If the task succedded, this will charge the new image in the view. 
            });

            /*Actualizes the progress of the task.*/
            filterTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
                labelProgressStatus.setText(newValue);
            });


            /*Creation of the thread.*/
            new Thread (filterTask).start();
        

        } catch (MalformedURLException murle) {
            murle.printStackTrace();
        }
    }
 
}
