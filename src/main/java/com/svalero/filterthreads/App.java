package com.svalero.filterthreads;
import java.io.IOException;
import com.svalero.filterthreads.controller.AppController;
import com.svalero.filterthreads.utils.AlertsUtils;
import com.svalero.filterthreads.utils.SplashScreen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void init() throws Exception {
        System.out.println("Initializing the application.");
        System.out.println(Thread.currentThread().getName());
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.showSplashScreen();
        new Thread(() -> {
            try {
                Thread.sleep(3000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                try {
                    // Cerrar el Splash Screen
                    splashScreen.closeSplashScreen();
                    
                    // Cargar y mostrar la ventana principal
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/svalero/filterthreads/mainWindow.fxml"));
                    loader.setController(new AppController());
                    AnchorPane anchorPane = loader.load();
        
                    Scene scene = new Scene(anchorPane);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Photo Filter Application");
                    primaryStage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                    AlertsUtils.showAlert(e.getMessage(), true);
                }
            });
        }).start();
    }

    @Override
    public void stop() throws Exception{
        System.out.println("Exiting the application.");
        System.out.println(Thread.currentThread().getName());
        super.stop();
    }


    public static void main(String[] args){
        launch();
    }
}