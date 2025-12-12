package com.example.rollercoasterjavafxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoasterApplication extends Application {
    static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoasterApplication.class.getResource("tallCoasterView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
    }

    public static void tallScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoasterApplication.class.getResource("tallCoasterView.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setScene(newScene);

    }

    public static void goodScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoasterApplication.class.getResource("GoodCoasterView.fxml"));
        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setScene(newScene);
    }

    public void stop() throws Exception{
        TallCoaster.saveData();
    }
}
