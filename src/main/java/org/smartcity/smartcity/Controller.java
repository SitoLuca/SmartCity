package org.smartcity.smartcity;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class Controller {

    public void SwapScene(String title, String Surce, int W, int H) {
        try {

            Stage primaryStage = Main.getPrimaryStage(); //Recupera lo Stage
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
            Scene next = new Scene(fxmlLoader.load(), W, H);
            primaryStage.setTitle(title);
            primaryStage.setScene(next);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
