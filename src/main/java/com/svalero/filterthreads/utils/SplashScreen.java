package com.svalero.filterthreads.utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashScreen extends Stage {

    public SplashScreen() {
        StackPane root = new StackPane();
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/svalero/filterthreads/splashscreen.jpg")));
        root.getChildren().add(imageView);
        Scene scene = new Scene(root);
        setScene(scene);
    }

    public void showSplashScreen() {
        show();
        centerOnScreen();
    }

    public void closeSplashScreen() {
        close();
    }

}
