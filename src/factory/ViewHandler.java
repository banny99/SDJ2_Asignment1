package factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import view.HeaterManager.HeaterViewController;
import view.TemperatureOverview.TemperatureViewController;
import view.chart.TempChartViewController;

import java.io.IOException;

public class ViewHandler
{

  private Stage primaryStage;
  private Scene currentScene;

  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory vmf){
    this.viewModelFactory = vmf;
  }

  public void start(Stage stage)
  {
    primaryStage = stage;
    try
    {
      openView("temperature");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openView(String id) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader();
    Parent root = null;

    if (id.equals("temperature")){
      fxmlLoader.setLocation(getClass().getResource("../view/TemperatureOverview/temperature.fxml"));
      root = fxmlLoader.load();
      TemperatureViewController viewController = fxmlLoader.getController();
      viewController.init(viewModelFactory.getTemperatureViewModel(), this);
      primaryStage.setTitle("Temperature Overview");
    }
    else if (id.equals("heater")){
      fxmlLoader.setLocation(getClass().getResource("../view/HeaterManager/heater.fxml"));
      root = fxmlLoader.load();
      HeaterViewController viewController = fxmlLoader.getController();
      viewController.init(viewModelFactory.getHeaterViewModel(), this);
      primaryStage.setTitle("Heater Manager");
    }

    else if (id.equals("chart")){
      fxmlLoader.setLocation(getClass().getResource("../view/chart/tempChart.fxml"));
      root = fxmlLoader.load();
      TempChartViewController viewController = fxmlLoader.getController();
      viewController.init(viewModelFactory.getTempChartViewModel(), this);
      primaryStage.setTitle("Temperature chart");

//      Stage stage = new Stage();
//      stage.setTitle("Line Chart Sample");
//      //defining the axes
//      final NumberAxis xAxis = new NumberAxis();
//      final NumberAxis yAxis = new NumberAxis();
//      xAxis.setLabel("Number of Month");
//      //creating the chart
//      final LineChart<Number,Number> lineChart =
//          new LineChart<Number,Number>(xAxis,yAxis);
//
//      lineChart.setTitle("Stock Monitoring, 2010");
//      //defining a series
//      XYChart.Series series = new XYChart.Series();
//      series.setName("My portfolio");
//      //populating the series with data
//      series.getData().add(new XYChart.Data(1, 23));
//      series.getData().add(new XYChart.Data(2, 14));
//      series.getData().add(new XYChart.Data(3, 15));
//      series.getData().add(new XYChart.Data(4, 24));
//      series.getData().add(new XYChart.Data(5, 34));
//      series.getData().add(new XYChart.Data(6, 36));
//      series.getData().add(new XYChart.Data(7, 22));
//      series.getData().add(new XYChart.Data(8, 45));
//      series.getData().add(new XYChart.Data(9, 43));
//      series.getData().add(new XYChart.Data(10, 17));
//      series.getData().add(new XYChart.Data(11, 29));
//      series.getData().add(new XYChart.Data(12, 25));
//
//      Scene scene  = new Scene(lineChart,800,600);
//      lineChart.getData().add(series);
//
//      stage.setScene(scene);
//      stage.show();
    }

    currentScene = new Scene(root);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }

}
