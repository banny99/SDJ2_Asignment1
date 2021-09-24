package extern;

import mediator.HeaterModel;
import mediator.TemperatureModel;

public class Thermometer implements Runnable
{
  private static int min;
  private static int max;

  private String id;
  private double temperature;
  private int distance;
  private int sleepTime;

  private TemperatureModel temperatureModel;
  private HeaterModel heaterModel;

  // indoor/inside thermometer
  public Thermometer(String id, double t, int distance, int sleepTime, int min, int max,TemperatureModel tm, HeaterModel hm){
    this.id = id;
    this.temperature = t;
    this.distance = distance;
    this.sleepTime = sleepTime;
    this.temperatureModel = tm;
    this.heaterModel = hm;

    Thermometer.min = min;
    Thermometer.max = max;
  }

  // outdoor/external thermometer (without 'distance')
  public Thermometer(String id, double t, int sleepTime,int min, int max, TemperatureModel m){
    this.id = id;
    this.temperature = t;
    distance = -1;
    this.sleepTime = sleepTime;
    this.temperatureModel = m;

    Thermometer.min = min;
    Thermometer.max = max;
  }

  /**
   * Calculating the internal temperature in one of two locations.
   * This includes a term from a heater (depending on location and
   * heaters power), and a term from an outdoor heat loss.
   * Values are only valid in the outdoor temperature range [-20; 20]
   * and when s, the number of seconds between each measurements are
   * between 4 and 8 seconds.
   *
   * @param t the last measured temperature
   * @param power the heaters power {0, 1, 2 or 3} where 0 is turned off,
   * 1 is low, 2 is medium and 3 is high
   * @param d the distance between heater and measurements {1 or 7}
   * where 1 is close to the heater and 7 is in the opposite corner
   * @param t0 the outdoor temperature (valid in the range [-20; 20])
   * @param s the number of seconds since last measurement [4; 8]
   * @return the temperature
   */
  public double getIndoorTemperature(double t, int power, int d, double t0, int s)
  {
    double tMax = Math.min(11 * power + 10, 11 * power + 10 + t0);
    tMax = Math.max(Math.max(t, tMax), t0);
    double heaterTerm = 0;
    if (power > 0)
    {
      double den = Math.max((tMax * (20 - 5 * power) * (d + 5)), 0.1);
      heaterTerm = 30 * s * Math.abs(tMax - t) / den;
    }
    double outdoorTerm = (t - t0) * s / 250.0;
    t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
    return t;
  }

  /**
   * Calculating the external temperature.
   * Values are only valid if the temperature is being measured
   * approximately every 10th second.
   *
   * @param t0 the last measured external temperature
   * @param min a lower limit (may temporally be deceeded)
   * @param max an upper limit (may temporally be exceeded)
   * @return an updated external temperature
   */
  public double getExternalTemperature(double t0, double min, double max)
  {
    double left = t0 - min;
    double right = max - t0;
    int sign = Math.random() * (left + right) > left ? 1 : -1;
    t0 += sign * Math.random();
    return t0;
  }

  @Override public void run()
  {
    while (true){

      //(if external thermometer -> distance set to '-1')
      if (this.distance < 0){
        temperature = getExternalTemperature(temperature, min, max);
      }
      else {
        temperature = getIndoorTemperature(temperature, heaterModel.getPower(), distance, 0, 6);
      }
      temperatureModel.addTemperature(id, temperature);

      try
      {
        Thread.sleep(sleepTime);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}

