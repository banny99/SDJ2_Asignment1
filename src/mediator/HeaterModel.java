package mediator;

import Util.Subject;

public interface HeaterModel extends Subject
{
  void turnUp();
  void turnDown();
  int getPower();

}
