package factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    }

    currentScene = new Scene(root);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }

}
