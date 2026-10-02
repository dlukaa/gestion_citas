package com.example.gestioncitas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gestion_citas.fxml")); //Poner la ruta del archivo .fxml
        Scene scene = new Scene(fxmlLoader.load(), 700, 520);
        stage.setTitle("Gestión de Citas - Clínica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}