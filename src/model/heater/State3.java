package model.heater;

public class State3 implements HeaterState
{
  private Heater heater;
  private Thread timer;
  private static final int POWER = 3;

  public State3(Heater h){
    heater = h;

    timer = createTimerThread();
    timer.setDaemon(true);
    timer.start();
  }

  @Override public void turnUp()
  {
    //nothing
  }

  @Override public void turnDown()
  {
    timer.interrupt();
    heater.setPowerState(new State2(heater));
  }

  @Override public int getPower()
  {
    return POWER;
  }


  private Thread createTimerThread(){
    return new Thread (() ->{
      try
      {
        Thread.sleep(10000);
        heater.setPowerState(new State2(heater));
        System.out.println(" /auto-turn DOWN/ ->state=" + heater.getPower());
      }
      catch (InterruptedException e)
      {
//        e.printStackTrace();
        System.out.println("'Heater-timer interrupted successfully'");
      }

    });
  }
}
