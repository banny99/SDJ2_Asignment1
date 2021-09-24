package mediator;

import extern.Listener;
import model.Temperature;
import model.TemperatureList;
import view.chart.TempChartViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TemperatureModelManager implements TemperatureModel
{
  private TemperatureList temperatureList;

  //---
  private PropertyChangeSupport changeSupport;
  //---

  public TemperatureModelManager()
  {
    temperatureList = new TemperatureList();
    changeSupport = new PropertyChangeSupport(this);
  }

  @Override public void addTemperature(String id, double value)
  {
    Temperature temperature = new Temperature(id, value);
    Temperature old = getLastInsertedTemperature();

    temperatureList.addTemperature(temperature);
    temperatureList.addTemperature(id, temperature); //add temp to specific/separated map

    if (old != null && old.getValue() != temperature.getValue())
    {
      changeSupport.firePropertyChange(id, old, temperature);
      System.out.println("-->" + temperature + " (from: " + old + ")");
    }
  }

  @Override public Temperature getLastInsertedTemperature()
  {
    return temperatureList.getLastTemperature(null);
  }

  @Override public Temperature getLastInsertedTemperature(String id)
  {
    return temperatureList.getLastTemperature(id);
  }

  // and maybe other methods...

  @Override public void addListener(String name, PropertyChangeListener listener)
  {
    if(name == null)
      changeSupport.addPropertyChangeListener(listener);
    else
      changeSupport.addPropertyChangeListener(name, listener);
  }

}

