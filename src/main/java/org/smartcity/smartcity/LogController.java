package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.Objects;

public class LogController extends Application implements Subscriber{
    private final TextFlow logFlow = new TextFlow();
    private static LogController instance = null;

    private LogController() {

    }

    @Override
    public void Update(String level, String message){
        addLog(level, message);
    }

    public static LogController GetInstance() {
        return Objects.requireNonNullElseGet(instance, LogController::new);
    }

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane(logFlow);
        scrollPane.setFitToWidth(true);

        VBox root = new VBox(scrollPane);
        Scene scene = new Scene(root, 500, 300);

        stage.setTitle("Log Viewer");
        stage.setScene(scene);
        stage.show();
    }

    private void addLog(String level, String message) {

        Text logText = new Text("[" + LocalTime.now() + "] " + level + ": " + message + "\n");

        switch (level) {
            case "INFO":
                logText.setFill(Color.BLUE);
                break;
            case "WARNING":
                logText.setFill(Color.ORANGE);
                break;
            case "ERROR":
                logText.setFill(Color.RED);
                logText.setFont(Font.font("Verdana", 14));
                break;
            default:
                logText.setFill(Color.BLACK);
        }

        logFlow.getChildren().add(logText);

    }


}

