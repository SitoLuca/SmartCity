package org.smartcity.smartcity.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.smartcity.smartcity.Main;
import org.smartcity.smartcity.dbProxy.DbManagerProxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Controller per la gestione della visualizzazione del grafico.
 * Estende la classe {@link Application}.
 *
 * Questa classe gestisce la logica di creazione di un grafico a linee basato su
 * i dati ottenuti dal database, con possibilità di selezionare il periodo di tempo
 * e i dati da visualizzare tramite delle checkbox.
 */
public class PlotController extends Application {

    @FXML
    private Button build; // Bottone per generare il grafico
    @FXML
    private DatePicker dateFrom; // Data di inizio per il periodo da visualizzare
    @FXML
    private DatePicker DateTo; // Data di fine per il periodo da visualizzare
    @FXML
    private Pane ChartPane; // Pannello dove viene visualizzato il grafico
    @FXML
    private CheckBox CheckInquinamento; // Checkbox per visualizzare i dati sull'inquinamento
    @FXML
    private CheckBox CheckTemperatura; // Checkbox per visualizzare i dati sulla temperatura
    @FXML
    private CheckBox CheckVeicoli; // Checkbox per visualizzare i dati sul numero di veicoli

    private final CategoryAxis xAxis = new CategoryAxis(); // Asse delle ascisse (per le date)
    private final NumberAxis yAxis = new NumberAxis(); // Asse delle ordinate (per i valori)

    LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

    /**
     * Costruttore della classe {@link PlotController}.
     *
     * Inizializza il controller.
     */
    public PlotController() {
    }

    /**
     * Metodo principale per avviare la finestra con il grafico.
     *
     * @param stage Finestra principale dell'applicazione.
     * @throws Exception Se si verifica un errore nel caricamento del file FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Grafico");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("linechart.fxml"));
        Scene plotScene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(plotScene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodo di inizializzazione per configurare gli eventi dei vari componenti
     * dell'interfaccia utente.
     */
    @FXML
    public void initialize() {
        build.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DrawLineChart(); // Disegna il grafico quando viene premuto il pulsante
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Gestione dell'evento per la selezione della data di inizio
        dateFrom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(dateFrom.getValue().toString());
            }
        });

        // Gestione dell'evento per la selezione della data di fine
        DateTo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(DateTo.getValue());
                build.setDisable(false); // Abilita il pulsante "Build" quando viene selezionata la data di fine
            }
        });
    }

    /**
     * Metodo per disegnare il grafico a linee basato sui dati selezionati nel periodo
     * specificato.
     *
     * @throws SQLException Se si verifica un errore durante l'esecuzione della query SQL.
     */
    private void DrawLineChart() throws SQLException {

        DbManagerProxy db = new DbManagerProxy(); // Oggetto per interagire con il database

        lineChart.getData().clear(); // Pulisce il grafico precedente

        lineChart.setTitle("Grafico");
        lineChart.setAnimated(true);
        lineChart.getXAxis().setLabel("Data");
        lineChart.getYAxis().setLabel("Valore");

        // Serie di dati per la temperatura
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Temperatura");

        // Serie di dati per il numero di veicoli
        XYChart.Series<String, Number> veicoliSeries = new XYChart.Series<>();
        veicoliSeries.setName("Veicoli");

        // Serie di dati per l'inquinamento
        XYChart.Series<String, Number> inquinamentoSeries = new XYChart.Series<>();
        inquinamentoSeries.setName("Inquinamento");

        // Query SQL per ottenere i dati dal database
        String sql = "select round(avg(temperatura), 2) as temperatura, round(avg(n_veicoli), 2) as veicoli, " +
                "round(avg(inquinamento),2) as inquinamento, data_ora from log_sensore where data_ora between '" +
                dateFrom.getValue().toString() + "' and '" + DateTo.getValue().toString() + "' group by data_ora";

        List<Map<String, Object>> res = db.queryExec(sql);

        // Aggiunge i dati al grafico per ogni riga della risposta
        for (Map<String, Object> log : res) {
            tempSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("temperatura").toString())));
            veicoliSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("veicoli").toString())));
            inquinamentoSeries.getData().add(new XYChart.Data<>(log.get("data_ora").toString(), Float.parseFloat(log.get("inquinamento").toString())));
        }

        // Lista delle serie da aggiungere al grafico in base alle checkbox selezionate
        List<XYChart.Series<String, Number>> a = new ArrayList<>();

        if (CheckTemperatura.isSelected()) {
            a.add(tempSeries);
        }
        if (CheckVeicoli.isSelected()) {
            a.add(veicoliSeries);
        }
        if (CheckInquinamento.isSelected()) {
            a.add(inquinamentoSeries);
        }

        // Aggiunge le serie selezionate al grafico
        lineChart.getData().addAll(a);

        // Imposta la dimensione del grafico
        lineChart.setMinHeight(ChartPane.getHeight());
        lineChart.setMinWidth(ChartPane.getWidth());

        // Aggiunge il grafico al pannello
        ChartPane.getChildren().add(lineChart);
    }
}
