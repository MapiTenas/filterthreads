package com.svalero.filterthreads.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.svalero.filterthreads.task.FilterTask;
import com.svalero.filterthreads.utils.AlertsUtils;
import com.svalero.filterthreads.utils.HistoryUtils;

import javafx.concurrent.Worker;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FilterController implements Initializable {

    @FXML
    private ImageView initialImage;
    @FXML
    private ImageView finalImage;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label labelProgressStatus;
    @FXML
    private Button saveButton;


    private File sourceImage;
    private List<String> selectedFilters;
    private FilterTask filterTask;
    private BufferedImage outputImage;

    //Class constructor
    public FilterController (File sourceImage, List<String> selectedFilters) {
        this.sourceImage = sourceImage;
        this.selectedFilters = selectedFilters;
        this.outputImage = null;
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
            AlertsUtils.showAlert(e.getMessage(), true);
        }
        
        try {
            filterTask = new FilterTask(this.sourceImage, this.selectedFilters);
        
            /*We bind the progress bar to the progress of the task*/
            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(filterTask.progressProperty());
            
            /*States of the task. This also launch a pop up when the task has succedded successfully.*/
            filterTask.stateProperty().addListener((observableValue, oldState, newState)-> {
               if (newState == Worker.State.SUCCEEDED) {
                    AlertsUtils.showAlert("The filter has finished successfully.", false);
                }
            });
        

            filterTask.setOnSucceeded(event -> {
                //If the task succedded, this will charge the new image in the view. 
                this.outputImage = filterTask.getValue();
                Image image = SwingFXUtils.toFXImage(outputImage, null);
                this.finalImage.setImage(image); 

                //Next, store a log in the app's history 
                HistoryUtils.saveHistory(sourceImage.getName(), selectedFilters.toString());
            });

            /*Actualizes the progress of the task.*/
            filterTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
                labelProgressStatus.setText(newValue);
            });


            /*Creation of the thread.*/
            new Thread (filterTask).start();
        

        } catch (MalformedURLException murle) {
            AlertsUtils.showAlert(murle.getMessage(), true);
            murle.printStackTrace();
        }
    }

    //Allows you to save the filtered images by choosing the path. There is one path by default. Photos have a new name by default.
    @FXML
    private void saveFilteredImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save filtered image");
        fileChooser.setInitialDirectory(new File("C:\\Users\\mapit\\Desktop\\FilteredPhotos"));
        Stage stage = (Stage) this.saveButton.getScene().getWindow();
        String fileName = this.sourceImage.getName().replaceFirst("[.][^.]+$", "");
        String extension = this.sourceImage.getName().substring(this.sourceImage.getName().lastIndexOf(".") + 1);
        String defaultFileName = fileName + "_filtered." + extension;
        fileChooser.setInitialFileName(defaultFileName);
        File file = fileChooser.showSaveDialog(stage);
        try {
            ImageIO.write(this.outputImage, "png", file);
        } catch (IOException e) {
            AlertsUtils.showAlert(e.getMessage(), true);
            e.printStackTrace();
        }
        
    }
 
}
