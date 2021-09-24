package model.heater;

public class State0 implements HeaterState
{
  private Heater heater;
  private static final int POWER = 0;

  public State0(Heater h){
    heater = h;
  }

  @Override public void turnUp()
  {
    heater.setPowerState(new State1(heater));
  }

  @Override public void turnDown()
  {
    //nothing
  }

  @Override public int getPower()
  {
    return POWER;
  }
}
