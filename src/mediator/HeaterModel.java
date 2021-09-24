package mediator;

import extern.Listener;

public interface HeaterModel
{
  void turnUp();
  void turnDown();
  int getPower();

  void addListener(Listener l);
}
