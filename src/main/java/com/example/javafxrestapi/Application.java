package com.example.javafxrestapi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX Rest API");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/tsp-rounded-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}