package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    public static Stage getPrimaryStage() { //Funzione che ritorna il main stage
        return primaryStage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; //Salva lo Stage principale

        Controller FirstScene = new Controller();

        FirstScene.SwapScene("Login", "login.fxml", 500, 500, false);
        
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}