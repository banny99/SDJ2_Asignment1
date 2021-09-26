package view.chart;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import mediator.TemperatureModel;
import model.Temperature;
import java.beans.PropertyChangeEvent;

public class TempChartViewModel
{
  private TemperatureModel temperatureModel;

  private ObservableList<XYChart.Data> tempValues1;
  private ObservableList<XYChart.Data> tempValues2;
  private ObservableList<XYChart.Data> tempValues3;

  public TempChartViewModel(TemperatureModel tm){
    temperatureModel = tm;
    temperatureModel.addListener(null, (evt) -> updateChart(evt));

    tempValues1 = FXCollections.observableArrayList();
    tempValues2 = FXCollections.observableArrayList();
    tempValues3 = FXCollections.observableArrayList();
  }

  private void updateChart(PropertyChangeEvent evt){

    Temperature temperature = (Temperature) evt.getNewValue();
    Platform.runLater(() -> {
      if (temperature.getId().equals("t1"))
      {
        if (tempValues1.size() >= 20)
          tempValues1.remove(0);
        tempValues1.add(new XYChart.Data(temperature.getTime().getTime(), temperature.getValue()));
      }
      else if (temperature.getId().equals("t2"))
      {
        if (tempValues2.size() >= 20)
          tempValues2.remove(0);
        tempValues2.add(new XYChart.Data(temperature.getTime().getTime(), temperature.getValue()));
      }
      else if (temperature.getId().equals("t3"))
      {
        if (tempValues3.size() >= 20)
          tempValues3.remove(0);
        tempValues3.add(new XYChart.Data(temperature.getTime().getTime(), temperature.getValue()));
      }
    });

  }

  public ObservableList<XYChart.Data> getList1()
  {
    return tempValues1;
  }
  public ObservableList<XYChart.Data> getList2()
  {
    return tempValues2;
  }
  public ObservableList<XYChart.Data> getList3()
  {
    return tempValues3;
  }
}
