package model.heater;

public class State1 implements HeaterState
{
  private Heater heater;
  private static final int POWER = 1;

  public State1(Heater h){
    heater = h;
  }

  @Override
  public void turnUp()
  {
    heater.setPowerState(new State2(heater));
  }

  @Override
  public void turnDown()
  {
    heater.setPowerState(new State0(heater));
  }

  @Override
  public int getPower()
  {
    return POWER;
  }
}
