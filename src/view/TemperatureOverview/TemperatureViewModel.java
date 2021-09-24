package view.TemperatureOverview;

import extern.Listener;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import mediator.HeaterModel;
import mediator.TemperatureModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;

public class TemperatureViewModel implements Listener
{
  private double minTemp = 15.0;
  private double maxTemp = 25.0;
  private boolean isAnnouncedMin = false;
  private boolean isAnnouncedMax = false;

  private StringProperty temperatureLabel1;
  private StringProperty temperatureLabel2;
  private StringProperty temperatureLabel3;
  private StringProperty powerStateLabel;

  private HeaterModel heaterModel;
  private TemperatureModel temperatureModel;

  public TemperatureViewModel(TemperatureModel tm, HeaterModel hm)
  {
    temperatureLabel1 = new SimpleStringProperty();
    temperatureLabel2 = new SimpleStringProperty();
    temperatureLabel3 = new SimpleStringProperty();
    powerStateLabel = new SimpleStringProperty();

    this.heaterModel = hm;
    heaterModel.addListener(this); //subscribing to the observing subject(heater)

    this.temperatureModel = tm;
    temperatureModel.addListener("t1", (evt) -> updateTemperature1(evt));
    temperatureModel.addListener("t2", (evt) -> updateTemperature2(evt));
    temperatureModel.addListener("t3", (evt) -> updateTemperature3(evt));
  }


  private void updateTemperature1(PropertyChangeEvent evt)
  {
    double temperature = ((Temperature) evt.getNewValue()).getValue();
    Platform.runLater(()-> temperatureLabel1.setValue(evt.getNewValue().toString()));
    checkTemperatureLimits(temperature);
  }
  private void checkTemperatureLimits(double temperature){
    if (!isAnnouncedMin && temperature < minTemp){
      isAnnouncedMin = true;
      System.out.println("indoor temperature too low!");
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Temperature-limit crossed!");
        alert.setHeaderText("Getting too cold, Brr");
        alert.setContentText("The indoor temperature fell below desired temperature level.");
        alert.showAndWait();
      });
    }
    else if (!isAnnouncedMax && temperature > maxTemp){
      isAnnouncedMax = true;
      System.out.println("indoor temperature too high!");
      Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Temperature-limit crossed!");
        alert.setHeaderText("Getting too hot, Uf");
        alert.setContentText("The indoor temperature reached above desired temperature level.");
        alert.showAndWait();
      });
    }

    if (temperature > minTemp){
      isAnnouncedMin = false;
    }
    if (temperature < maxTemp){
      isAnnouncedMax = false;
    }
  }

  private void updateTemperature2(PropertyChangeEvent evt)
  {
    double temperature = ((Temperature) evt.getNewValue()).getValue();
    Platform.runLater(()-> temperatureLabel2.setValue(evt.getNewValue().toString()));
  }

  private void updateTemperature3(PropertyChangeEvent evt)
  {
    Platform.runLater(()-> temperatureLabel3.setValue(evt.getNewValue().toString()));
  }


  public StringProperty requestTemperatureLabelProperty1()
  {
    return temperatureLabel1;
  }
  public StringProperty requestTemperatureLabelProperty2()
  {
    return temperatureLabel2;
  }
  public StringProperty requestTemperatureLabelProperty3()
  {
    return temperatureLabel3;
  }
  public StringProperty requestPowerStateLabelProperty()
  {
    return powerStateLabel;
  }

  //listening to the power state changes ->update heater state when changed
  @Override public void update()
  {
    Platform.runLater(() -> powerStateLabel.setValue("" + heaterModel.getPower()));
  }

  public void onSubmitButtonPressed(double min, double max)
  {
    minTemp = min;
    maxTemp = max;
  }

  public double getMaxTemp()
  {
    return maxTemp;
  }
  public double getMinTemp()
  {
    return minTemp;
  }
}
