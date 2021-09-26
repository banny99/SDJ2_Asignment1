package view.TemperatureOverview;

import Util.Listener;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import mediator.HeaterModel;
import mediator.TemperatureModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.Collections;

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

  private ObservableList<Temperature> table1TempList;
  private ObservableList<Temperature> table2TempList;
  private ObservableList<Temperature> table3TempList;

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
    temperatureModel.addListener("t1", (evt) -> updateTemperatures(evt));
    temperatureModel.addListener("t2", (evt) -> updateTemperatures(evt));
    temperatureModel.addListener("t3", (evt) -> updateTemperatures(evt));

    table1TempList = FXCollections.observableArrayList();
    table2TempList = FXCollections.observableArrayList();
    table3TempList = FXCollections.observableArrayList();
  }

  private void updateTemperatures(PropertyChangeEvent evt){
    Temperature temperature = ((Temperature) evt.getNewValue());

    if (temperature.getId().equals("t1"))
      updateTemperature1(temperature);

    else if (temperature.getId().equals("t2"))
      updateTemperature2(temperature);

    else if (temperature.getId().equals("t3"))
      updateTemperature3(temperature);
  }

  private void updateTemperature1(Temperature temperature)
  {
    if (table1TempList.size() >= 4){
      table1TempList.remove(0);
    }
    table1TempList.add(temperature);
    Platform.runLater(()-> temperatureLabel1.setValue(temperature.toString()));

    checkTemperatureLimits(temperature.getValue());
  }

  private void updateTemperature2(Temperature temperature)
  {
    if (table2TempList.size() >= 4){
      table2TempList.remove(0);
    }
    table2TempList.add(temperature);
    Platform.runLater(()-> temperatureLabel2.setValue(temperature.toString()));
  }

  private void updateTemperature3(Temperature temperature)
  {
    if (table3TempList.size() >= 4){
      table3TempList.remove(0);
    }
    table3TempList.add(temperature);
    Platform.runLater(()-> temperatureLabel3.setValue(temperature.toString()));
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


  public ObservableList<Temperature> getTemperatureList1()
  {
    return table1TempList;
  }

  public ObservableList<Temperature> getTemperatureList2()
  {
    return table2TempList;
  }

  public ObservableList<Temperature> getTemperatureList3()
  {
    return table3TempList;
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
}
