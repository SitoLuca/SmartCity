package org.smartcity.smartcity.builder;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public interface ChartBuilder {

    void setTitle(String title);
    void setXAxisTitle(String xAxisTitle);
    void setYAxisTitle(String yAxisTitle);
    void addSeries(XYChart.Series<String, Number> newSeries, String TitleSerie);

    LineChart<String,Number> buildChart();

}
