package com.svalero.filterthreads.controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

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
    private ChoiceBox choiceBoxFilters;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     

    }
    
}
