package com.svalero.filterthreads.utils;

import javafx.scene.control.Alert;

public class AlertsUtils {

    public static void showAlert(String message, Boolean isError){
        Alert alert;
        if (isError) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setContentText(message);
        alert.show();
    }

}
