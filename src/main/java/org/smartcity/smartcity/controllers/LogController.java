package org.smartcity.smartcity.controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.smartcity.smartcity.strategy.Subscriber;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Controller per la visualizzazione dei log in tempo reale.
 * Questa classe visualizza i messaggi di log con diversi livelli di gravità
 * in una finestra grafica.
 */
public class LogController extends Application implements Subscriber {
    private final TextFlow logFlow = new TextFlow();
    private static LogController instance = null;

    /**
     * Costruttore privato per garantire l'uso del pattern Singleton.
     */
    private LogController() {
    }

    /**
     * Metodo per aggiornare il log con un nuovo messaggio.
     * Aggiunge un messaggio di log con un livello di gravità specificato.
     *
     * @param level   Il livello di gravità del messaggio (INFO, WARNING, CRITICAL).
     * @param message Il messaggio da visualizzare.
     */
    @Override
    public void Update(String level, String message) {
        addLog(level, message);
    }

    /**
     * Restituisce l'istanza unica della classe (Singleton).
     *
     * @return L'istanza unica di LogController.
     */
    public static LogController GetInstance() {
        return Objects.requireNonNullElseGet(instance, LogController::new);
    }

    /**
     * Avvia la finestra della GUI con la visualizzazione dei log.
     *
     * @param stage Il palcoscenico principale dell'applicazione.
     */
    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane(logFlow);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(scrollPane);
        Scene scene = new Scene(root, 500, 300);

        stage.setTitle("Visualizzatore Log");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Aggiunge un messaggio di log al flusso di testo.
     * Il colore del messaggio dipende dal livello di gravità.
     *
     * @param level   Il livello di gravità del messaggio (INFO, WARNING, CRITICAL).
     * @param message Il messaggio da aggiungere al flusso.
     */
    private void addLog(String level, String message) {
        Text logText = new Text("[" + LocalTime.now() + "] " + level + ": " + message + "\n");

        // Imposta il colore del messaggio in base al livello di gravità
        switch (level) {
            case "INFO":
                logText.setFill(Color.BLUE);
                break;
            case "WARNING":
                logText.setFill(Color.ORANGE);
                break;
            case "CRITICAL":
                logText.setFill(Color.RED);
                break;
            default:
                logText.setFill(Color.BLACK);
        }

        logFlow.getChildren().add(logText);
    }
}
