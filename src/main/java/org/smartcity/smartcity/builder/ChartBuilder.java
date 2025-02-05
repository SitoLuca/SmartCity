package org.smartcity.smartcity.builder;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Interfaccia che definisce i metodi per costruire un grafico (es. un grafico a linee).
 * Questa interfaccia permette di configurare il titolo del grafico, i titoli degli assi,
 * e di aggiungere serie di dati al grafico prima di costruirlo.
 */
public interface ChartBuilder {

    /**
     * Imposta il titolo del grafico.
     *
     * @param title Il titolo del grafico.
     */
    void setTitle(String title);

    /**
     * Imposta il titolo dell'asse X del grafico.
     *
     * @param xAxisTitle Il titolo dell'asse X.
     */
    void setXAxisTitle(String xAxisTitle);

    /**
     * Imposta il titolo dell'asse Y del grafico.
     *
     * @param yAxisTitle Il titolo dell'asse Y.
     */
    void setYAxisTitle(String yAxisTitle);

    /**
     * Aggiunge una nuova serie di dati al grafico.
     *
     * @param newSeries La serie di dati da aggiungere.
     * @param TitleSerie Il titolo della serie da visualizzare nella legenda.
     */
    void addSeries(XYChart.Series<String, Number> newSeries, String TitleSerie);

    /**
     * Costruisce e restituisce un grafico a linee con le impostazioni e le serie di dati configurate.
     *
     * @return Il grafico a linee costruito.
     */
    LineChart<String, Number> buildChart();
}
