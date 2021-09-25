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
  private XYChart.Series series1;
  private XYChart.Series series2;
  private XYChart.Series series3;

  private TempChartViewModel tempChartViewModel;
  private ViewHandler viewHandler;

  public void init(TempChartViewModel vm, ViewHandler vh){
    tempChartViewModel = vm;
    viewHandler = vh;

    series1 = new XYChart.Series();
    series1.setData(tempChartViewModel.getList1()); //binding
    series2 = new XYChart.Series();
    series2.setData(tempChartViewModel.getList2()); //binding
    series3 = new XYChart.Series();
    series3.setData(tempChartViewModel.getList3()); //binding

    tempChart.getData().addAll(series1, series2, series3);
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
