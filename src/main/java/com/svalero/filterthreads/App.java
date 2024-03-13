package com.svalero.filterthreads;
import java.io.IOException;
import com.svalero.filterthreads.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        loader.setController(new AppController());
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Photo Filter Application.");
        primaryStage.show();
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