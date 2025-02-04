package org.smartcity.smartcity.builder;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class LinechartBuilder implements ChartBuilder {

    ArrayList<XYChart.Series<String, Number>> series = new ArrayList<>();

    String title;
    String xAxisTitle;
    String yAxisTitle;


    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setXAxisTitle(String xAxisTitle) {

    }

    @Override
    public void setYAxisTitle(String yAxisTitle) {

    }

    @Override
    public void addSeries(XYChart.Series<String, Number> newSeries, String TitleSerie) {
        series.add(new XYChart.Series<>());
    }

    @Override
    public LineChart<String, Number> buildChart() {
        return null;
    }
}
