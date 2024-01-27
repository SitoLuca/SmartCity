package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; //Salva lo Stage principale
        LoadFirstScene(stage, "Login", "login.fxml", 500, 500);
    }

    private void LoadFirstScene(Stage stage, String Title, String Surce, int W, int H) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
        Scene scene = new Scene(fxmlLoader.load(), W, H);
        stage.setTitle(Title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}