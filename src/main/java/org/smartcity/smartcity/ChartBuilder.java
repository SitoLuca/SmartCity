package org.smartcity.smartcity;

public interface ChartBuilder {
    void setTitle(String title);
    void setXAxisLable(String xAxisTitle);
    void setYAxisLable(String yAxisTitle);
    void addDataPoint(String x, String y);
    void build();

}
