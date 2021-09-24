package view.chart;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import mediator.TemperatureModel;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class TempChartViewModel
{
  private TemperatureModel temperatureModel;

  private ObservableList<XYChart.Data> tempValues1;

  public TempChartViewModel(TemperatureModel tm){
    temperatureModel = tm;
    temperatureModel.addListener(null, (evt) -> updateChart(evt));

  }

  private void updateChart(PropertyChangeEvent evt){
    System.out.println("updating chart from view-model");

//    double temperature = ((Temperature) evt.getNewValue()).getValue();
//    XYChart.Data<String, Double> temp = new XYChart.Data<String, Double>("Y", temperature);
//    tempValues1.add(temp);
  }

  public ObservableList<XYChart.Data> getList()
  {
    return tempValues1;
  }
}
