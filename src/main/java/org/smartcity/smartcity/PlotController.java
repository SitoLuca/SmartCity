package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class PlotController extends Application {


    @FXML
    private Button build;
    @FXML
    private LineChart<Number, Number> LineChart;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker DateTo;


    public PlotController() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Grafico");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linechart.fxml"));
        Scene plotScene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(plotScene);
        stage.setResizable(false);
        stage.show();


    }

    @FXML
    public void initialize() {
        build.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                DrawLineChart();
            }
        });
    }

    private void DrawLineChart() {

    }

}


