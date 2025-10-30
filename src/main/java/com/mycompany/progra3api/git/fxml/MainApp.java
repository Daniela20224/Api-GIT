package com.mycompany.progra3api.git.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var fxmlPath = "/fxml/FXML.fxml";
        System.out.println("Cargando interfaz desde: " + getClass().getResource(fxmlPath));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene mainScene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("GitHub Client - Proyecto Final");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
