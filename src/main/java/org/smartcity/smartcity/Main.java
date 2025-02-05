package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.smartcity.smartcity.controllers.Controller;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() { //Funzione che ritorna il main stage
        return primaryStage;
    }

    /**
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; //Salva lo Stage principale
        //final var icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/Logo.jpg")));
        //primaryStage.getIcons().add(icon);

        Controller FirstScene = new Controller();

        FirstScene.SwapScene("Login", "login.fxml", 500, 500, false);
        
        primaryStage.show();

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }


}