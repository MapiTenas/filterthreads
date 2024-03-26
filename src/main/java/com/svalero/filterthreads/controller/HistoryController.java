package com.svalero.filterthreads.controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.svalero.filterthreads.model.HistoryPhoto;
import com.svalero.filterthreads.utils.HistoryUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistoryController implements Initializable {
    @FXML
    private TableView tableViewHistoric;

    @FXML 
    private TableColumn<String, String> tableColumnPhotoName;
    
    @FXML 
    private TableColumn<String, String> tableColumnFilters;
    
    @FXML 
    private TableColumn<String, String> tableColumnDateTime;

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            tableColumnPhotoName.setCellValueFactory(new PropertyValueFactory<>("photoName"));
            tableColumnFilters.setCellValueFactory(new PropertyValueFactory<>("filterNames"));
            tableColumnDateTime.setCellValueFactory(new PropertyValueFactory<>("datePhoto"));
            ObservableList<HistoryPhoto> historyPhotos = FXCollections.observableArrayList(HistoryUtils.writeHistory());
            tableViewHistoric.setItems(historyPhotos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
