package view.HeaterManager;

import factory.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HeaterViewController
{
  @FXML private Label lb_powerState;

  private ViewHandler viewHandler;
  private HeaterViewModel heaterViewModel;

  public void init(HeaterViewModel vm, ViewHandler vh)
  {
    this.heaterViewModel = vm;
    this.viewHandler = vh;

    lb_powerState.textProperty().bind(heaterViewModel.requestPowerStateProperty());
  }

  public void buttonUpPressed(ActionEvent actionEvent)
  {
    heaterViewModel.onButtonUpPressed();
  }

  public void buttonDownPressed(ActionEvent actionEvent)
  {
    heaterViewModel.onButtonDownPressed();
  }

  public void checkTemperatureButtonPressed(ActionEvent actionEvent)
  {
    try
    {
      viewHandler.openView("temperature");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
