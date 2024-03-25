package com.svalero.filterthreads.controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AppController implements Initializable{

    // We match the components between the user interface and the model.

    @FXML
    private Button buttonSelectImage;

    @FXML
    private Button buttonSelectFilter;

    @FXML 
    private Label labelWelcome;

    @FXML
    private TabPane tabPaneFilter;

    @FXML
    private ListView filterListView;

    @FXML 
    private Label imagePathLabel;

    @FXML
    private Label orLabel;

    @FXML 
    private Button buttonSelectFolder;

    @FXML
    private Label folderPathLabel;

    ObservableList<String> selectedItems;

    private List<File> files;

    public AppController(){

    }

    /*Method in which the filter selection is added to the list view. */
    @Override
    public void initialize (URL location, ResourceBundle resources){
        tabPaneFilter.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        this.filterListView.getItems().addAll("Grayscale", "Brighter","InvertColor", "Sepia");
        this.filterListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //This allows to choose multiple filters. 
    } 

    /*Method that is executed when we click on the Select image button*/
    @FXML
    public void selectImage(ActionEvent event){
        Stage stage = (Stage) this.buttonSelectImage.getScene().getWindow();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(stage);
        this.files = new ArrayList<File>();
        this.files.add(file);
        this.imagePathLabel.setText(file.getName());
    }

    /*Method that is executed when we clic on the Select folder button */
    @FXML
    public void selectFolder(ActionEvent event){
        Stage stage = (Stage) this.buttonSelectFolder.getScene().getWindow();
        DirectoryChooser dc = new DirectoryChooser();
        File selectedFolder = dc.showDialog(stage);
        this.files = new ArrayList<File>(Arrays.asList(selectedFolder.listFiles()));
        this.folderPathLabel.setText(selectedFolder.getName());
    }


    /*Method that is executed when clicking on the Select Filter button */
    @FXML
    private void selectFilter(ActionEvent event) {
        try {
            System.out.println(this.filterListView.getSelectionModel().getSelectedItems());
            List<String> selectedFilters = new ArrayList<String>(this.filterListView.getSelectionModel().getSelectedItems());
            for (File file : files){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/svalero/filterthreads/filterPaneWindow.fxml"));
                FilterController filterController = new FilterController(file, selectedFilters);
                loader.setController(filterController);
                AnchorPane filterPane = loader.load();
    
                String fileName = file.getName();
                System.out.println(fileName);
                tabPaneFilter.getTabs().add(new Tab(fileName, filterPane));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
