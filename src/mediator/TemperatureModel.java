package mediator;

import model.Temperature;
import java.beans.PropertyChangeListener;

public interface TemperatureModel
{
  void addTemperature(String id, double temperature);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);

  void addListener(String name, PropertyChangeListener listener);
}

