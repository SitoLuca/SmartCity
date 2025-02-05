package org.smartcity.smartcity.builder;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Classe che implementa l'interfaccia {@link ChartBuilder} per la costruzione di un grafico a linee.
 * Questa classe permette di configurare un grafico a linee impostando il titolo, i titoli degli assi
 * e aggiungendo serie di dati.
 */
public class LinechartBuilder implements ChartBuilder {

    // Lista delle serie di dati da aggiungere al grafico
    ArrayList<XYChart.Series<String, Number>> series = new ArrayList<>();

    // Titolo del grafico
    String title;

    // Titoli degli assi
    String xAxisTitle;
    String yAxisTitle;

    /**
     * Imposta il titolo del grafico.
     *
     * @param Title Il titolo del grafico.
     */
    @Override
    public void setTitle(String Title) {
        title = Title;
    }

    /**
     * Imposta il titolo dell'asse X del grafico.
     *
     * @param XAxisTitle Il titolo dell'asse X.
     */
    @Override
    public void setXAxisTitle(String XAxisTitle) {
        xAxisTitle = XAxisTitle;
    }

    /**
     * Imposta il titolo dell'asse Y del grafico.
     *
     * @param YAxisTitle Il titolo dell'asse Y.
     */
    @Override
    public void setYAxisTitle(String YAxisTitle) {
        yAxisTitle = YAxisTitle;
    }

    /**
     * Aggiunge una nuova serie di dati al grafico.
     *
     * @param newSeries La serie di dati da aggiungere.
     * @param TitleSerie Il titolo della serie da visualizzare nella legenda.
     */
    @Override
    public void addSeries(XYChart.Series<String, Number> newSeries, String TitleSerie) {
        series.add(new XYChart.Series<>());
    }

    /**
     * Costruisce il grafico a linee con le impostazioni e le serie di dati configurate.
     *
     * @return Il grafico a linee (attualmente non implementato).
     */
    @Override
    public LineChart<String, Number> buildChart() {
        return null;
    }
}
