package org.smartcity.smartcity.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.smartcity.smartcity.Main;

import java.io.IOException;

/**
 * Classe che gestisce il controllo delle scene all'interno dell'applicazione.
 * Questa classe fornisce metodi per cambiare scena e aprire finestre di dialogo.
 */
public class Controller {

    /**
     * Cambia la scena principale dell'applicazione con una nuova scena.
     *
     * @param title Il titolo della nuova finestra.
     * @param Surce Il percorso del file FXML della scena da caricare.
     * @param W Larghezza della nuova finestra.
     * @param H Altezza della nuova finestra.
     * @param resizable Se la finestra deve essere ridimensionabile.
     */
    public void SwapScene(String title, String Surce, int W, int H, boolean resizable) {
        try {

            // Recupera la finestra principale (stage) dell'applicazione.
            Stage primaryStage = Main.getPrimaryStage();

            // Carica il file FXML e crea una nuova scena.
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
            Scene next = new Scene(fxmlLoader.load(), W, H);

            // Imposta le proprietà della finestra.
            primaryStage.setResizable(resizable);
            primaryStage.setTitle(title);
            primaryStage.setScene(next);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Apre una finestra di dialogo (popup) con una scena separata.
     *
     * @param title Il titolo della finestra di dialogo.
     * @param Surce Il percorso del file FXML della finestra di dialogo da caricare.
     * @param W Larghezza della finestra di dialogo.
     * @param H Altezza della finestra di dialogo.
     * @throws IOException Se il file FXML non può essere caricato.
     */
    public void openDialog(String title, String Surce, int W, int H) throws IOException {
        // Carica il file FXML per la finestra di dialogo.
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Surce));
        Scene dialog = new Scene(fxmlLoader.load(), W, H);

        // Crea una nuova finestra di dialogo.
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.setScene(dialog);

        // Mostra la finestra di dialogo e attendi che venga chiusa.
        dialogStage.showAndWait();
    }
}
