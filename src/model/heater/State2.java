package model.heater;

public class State2 implements HeaterState
{
  private Heater heater;
  private static final int POWER = 2;

  public State2(Heater h)
  {
    heater = h;
  }

  @Override public void turnUp()
  {
    heater.setPowerState(new State3(heater));
  }

  @Override public void turnDown()
  {
    heater.setPowerState(new State1(heater));
  }

  @Override public int getPower()
  {
    return POWER;
  }
}
