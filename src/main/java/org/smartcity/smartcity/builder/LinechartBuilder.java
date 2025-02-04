package org.smartcity.smartcity.builder;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class LinechartBuilder  implements ChartBuilder{

    ArrayList<XYChart.Series<String, Number>> series = new ArrayList<>();

    String title;
    String xAxisTitle;
    String yAxisTitle;




    @Override
    public void setTitle(String Title) {
        title = Title;
    }

    @Override
    public void setXAxisTitle(String XAxisTitle) {
        xAxisTitle = XAxisTitle;
    }

    @Override
    public void setYAxisTitle(String YAxisTitle) {
        yAxisTitle = YAxisTitle;
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
