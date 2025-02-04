package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.smartcity.smartcity.controllers.Controller;


/**
 * The Main class is the entry point of the JavaFX application for the SmartCity project.
 * It extends {@link Application} and overrides the start method to set up the initial scene
 * and primary stage for the application.
 *
 * This class is responsible for:
 * 1. Launching the JavaFX application.
 * 2. Setting up the primary stage (main window).
 * 3. Loading the initial scene using an FXML file for the login screen.
 * 4. Optionally setting an application icon.
 *
 * The primaryStage is shared across different parts of the application for scene switching.
 */
public class Main extends Application {

    /**
     * The primary stage of the application shared globally
     */
    private static Stage primaryStage;


    /**
     * Getter of primaryStage
     * @return Stage primaryStage
     */
    public static Stage getPrimaryStage() { //Funzione che ritorna il main stage
        return primaryStage;
    }


    /**
     * Initializes the main stage,
     * sets the initial scene to be shown
     *
     * @param stage the primary stage for this application, which will be set up.
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        //final var icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/Logo.jpg")));
        //primaryStage.getIcons().add(icon);

        Controller FirstScene = new Controller();

        FirstScene.SwapScene("Login", "login.fxml", 500, 500, false);
        
        primaryStage.show();

    }

    /**
     * The main method that launches the JavaFX application.
     * This is the entry point to the application.
     *
     * @param args command-line arguments passed when starting the application (not used here).
     */
    public static void main(String[] args) {
        launch();
    }


}