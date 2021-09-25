package mediator;

import Util.Listener;
import model.heater.Heater;

public class HeaterModelManager implements HeaterModel
{
  private Heater heater;

  public HeaterModelManager(Heater heater){
    this.heater = heater;
  }

  @Override public void turnUp()
  {
    heater.turnUp();
  }

  @Override public void turnDown()
  {
    heater.turnDown();
  }

  @Override public int getPower()
  {
    return heater.getPower();
  }



  @Override public void addListener(Listener l)
  {
    heater.addListener(l);
  }
  @Override public void removeListener(Listener l)
  {
    heater.removeListener(l);
  }
  @Override public void notifyListeners()
  {
    heater.notifyListeners();
  }

}
