package model.heater;

public interface HeaterState
{
  int getPower();
  void turnUp();
  void turnDown();
}
