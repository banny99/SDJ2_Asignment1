package factory;

import mediator.HeaterModelManager;
import mediator.TemperatureModel;
import mediator.TemperatureModelManager;
import model.heater.Heater;
import mediator.HeaterModel;

public class ModelFactory
{

  private HeaterModel heaterModel;
  public HeaterModel getHeaterModel()
  {
    if (heaterModel == null){
      heaterModel = new HeaterModelManager(new Heater());
    }
    return heaterModel;
  }

  private TemperatureModel temperatureModel;
  public TemperatureModel getTemperatureModel()
  {
    if (temperatureModel == null){
      temperatureModel = new TemperatureModelManager();
    }
    return temperatureModel;
  }


}
