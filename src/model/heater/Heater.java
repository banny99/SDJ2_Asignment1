package model.heater;

import extern.Listener;
import extern.Subject;

import java.util.ArrayList;

public class Heater implements Subject
{
  private HeaterState currState;
  private ArrayList<Listener> listeners;

  public Heater(){
    currState = new State0(this);
    listeners = new ArrayList<>();
  }

  public void turnUp(){
    currState.turnUp();
    System.out.println(" /turning UP/ ->state=" + getPower());
  }
  public void turnDown(){
    currState.turnDown();
    System.out.println(" /turning DOWN/ ->state=" + getPower());
  }

  public int getPower(){
    return currState.getPower();
  }

  void setPowerState(HeaterState newState){
    currState = newState;
    notifyListeners();
  }


  //subject/observer methods
  @Override public void addListener(Listener l)
  {
    listeners.add(l);
    l.update();
  }

  @Override public void removeListener(Listener l)
  {
    listeners.remove(l);
  }

  @Override public void notifyListeners()
  {
    for (Listener l : listeners){
      l.update();
    }
  }
}
