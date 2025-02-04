package org.smartcity.smartcity.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.smartcity.smartcity.Main;

import java.io.IOException;


public class Controller {

    public void SwapScene(String title, String Surce, int W, int H, boolean resizable) {
        try {

            Stage primaryStage = Main.getPrimaryStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
            Scene next = new Scene(fxmlLoader.load(), W, H);
            primaryStage.setResizable(resizable);
            primaryStage.setTitle(title);
            primaryStage.setScene(next);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDialog(String title, String Surce, int W, int H) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
        Scene dialog = new Scene(fxmlLoader.load(), W, H);

        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.setScene(dialog);

        dialogStage.showAndWait();

    }

}
