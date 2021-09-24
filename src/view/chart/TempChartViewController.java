package view.chart;

import factory.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.io.IOException;

public class TempChartViewController
{
  @FXML private LineChart tempChart;

  private XYChart.Series dataSeries1;

  private TempChartViewModel tempChartViewModel;
  private ViewHandler viewHandler;

  public void init(TempChartViewModel tvm, ViewHandler vh){
    tempChartViewModel = tvm;
    viewHandler = vh;

//    dataSeries1 = new XYChart.Series();
//    dataSeries1.getData().add(tempChartViewModel.getList());
//    dataSeries1.setData(tempChartViewModel.getList()); //binding
//    tempChart.getData().add(dataSeries1);
  }

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    try
    {
      viewHandler.openView("temperature");
//      tempChartViewModel.unsubscribe();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


}
