package org.smartcity.smartcity;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PlotController extends Application {


    @FXML
    private Button build;
    @FXML
    private LineChart<String, Number> linechart;

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
                try {
                    DrawLineChart();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        dateFrom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(dateFrom.getValue().toString());
            }
        });

        DateTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(DateTo.getValue().toString());
            }
        });

    }

    private void DrawLineChart() throws SQLException {
        //PlotBuilder builder = new PlotBuilder();

        DbManager manager = DbManager.getInstance();

        linechart.getData().clear();

        linechart.setTitle("Grafico");
        linechart.setAnimated(true);
        linechart.getXAxis().setLabel("Data");
        linechart.getYAxis().setLabel("Valore");

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Temperatura");

        XYChart.Series<String, Number> veicoliSeries = new XYChart.Series<>();
        veicoliSeries.setName("Veicoli");

        XYChart.Series<String, Number> inquinamentoSeries = new XYChart.Series<>();
        inquinamentoSeries.setName("Inquinamento");


        String sql = "select round(avg(temperatura), 2) as temperatura, round(avg(n_veicoli), 2) as veicoli, round(avg(inquinamento),2) as inquinamento, data_ora\n" +
                "from log_sensore where data_ora between '" + dateFrom.getValue().toString() + "' and '" + DateTo.getValue().toString() + "' \n" +
                "group by data_ora";

        List<Map<String, Object>> res = manager.queryExec(sql);

        for (Map<String, Object> log : res) {
            tempSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("temperatura").toString())));
            veicoliSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("veicoli").toString())));
            inquinamentoSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("inquinamento").toString())));

        }

        linechart.getData().addAll(List.of(tempSeries,veicoliSeries,inquinamentoSeries));
    }

}


