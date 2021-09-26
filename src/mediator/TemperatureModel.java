package mediator;

import javafx.collections.ObservableList;
import model.Temperature;
import java.beans.PropertyChangeListener;
import java.util.Collection;

public interface TemperatureModel
{
  void addTemperature(String id, double temperature);

  Temperature getLastInsertedTemperature();

  Temperature getLastInsertedTemperature(String id);

  void addListener(String name, PropertyChangeListener listener);


//  Collection<Temperature> getTemperatureCollection(String key);

}

