package view.TemperatureOverview;

import factory.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class TemperatureViewController
{

  //--
  @FXML private TextField tf_min;
  @FXML private TextField tf_max;
  @FXML private Button btn_submit;

  @FXML private Label lb_powerState;

  @FXML private Label lb_temperature1;
  @FXML private Label lb_temperature2;
  @FXML private Label lb_temperature3;
  //--

  private TemperatureViewModel temperatureViewModel;
  private ViewHandler viewHandler;

  public void init(TemperatureViewModel vm, ViewHandler vh)
  {
    this.temperatureViewModel = vm;    //ViewModel reference--> MVVM
    this.viewHandler = vh;             //ViewHandler reference--> change views

    tf_min.setText(temperatureViewModel.getMinTemp()+"");
    tf_max.setText(temperatureViewModel.getMaxTemp()+"");

    //bindings:
    lb_temperature1.textProperty().bind(temperatureViewModel.requestTemperatureLabelProperty1());
    lb_temperature2.textProperty().bind(temperatureViewModel.requestTemperatureLabelProperty2());
    lb_temperature3.textProperty().bind(temperatureViewModel.requestTemperatureLabelProperty3());

    lb_powerState.textProperty().bind(temperatureViewModel.requestPowerStateLabelProperty());
  }


  //button actions handling:
  public void manageHeaterButtonPressed(ActionEvent actionEvent)
  {
    try
    {
      viewHandler.openView("heater");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void submitButtonPressed(ActionEvent actionEvent)
  {
    try
    {
      double min = Double.parseDouble(tf_min.getText());
      double max = Double.parseDouble(tf_max.getText());
      if (min < max && (max-min) >= 5){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Temperature-limit change confirmation");
        alert.setHeaderText("Change Temperature limits ?");
        alert.setContentText("Do you want to set MIN-temperature to "+min+",\n and MAX-temperature to "+max+" Celsia?");
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES){
          temperatureViewModel.onSubmitButtonPressed(min, max);
        }
        else {
          tf_min.clear();
          tf_max.clear();
        }
      }
      else {
        triggerInvalidInputAlert("difference between max and min must be at least 5 degrees.");
      }
    }
    catch (NumberFormatException nfe){
      triggerInvalidInputAlert("temperature values must be decimal values");
    }
  }

  private void triggerInvalidInputAlert(String additionalMsg){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Invalid input alert");
    alert.setHeaderText("Invalid or none values inserted as temperature limits!");
    alert.setContentText("Insert valid temperature data and try again please :).\n(*i - " + additionalMsg + ".)");
    alert.showAndWait();

    tf_max.clear();
    tf_min.clear();
  }


  public void showChartButtonPressed(ActionEvent actionEvent)
  {
    try
    {
      viewHandler.openView("chart");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
