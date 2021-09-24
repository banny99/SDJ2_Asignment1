package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TemperatureList
{

  private ArrayList<Temperature> list;
  private Map<String, ArrayList<Temperature>> tempMap;

  public TemperatureList()
  {
    this.list = new ArrayList<>();
    tempMap = new HashMap<>();
  }

  public void addTemperature(Temperature temperature)
  {
    list.add(temperature);
  }
  public void addTemperature(String key, Temperature temperature)
  {
    if (!tempMap.containsKey(key)){
      tempMap.put(key, new ArrayList<Temperature>(25));
    }

    if (tempMap.get(key) != null && tempMap.get(key).size() >= 20){
      tempMap.get(key).remove(0);
//      System.out.println(tempMap.size());
    }

    tempMap.get(key).add(temperature);
  }

  public Temperature getTemperature(int index)
  {
    if (index >= 0 && index < list.size())
    {
      return list.get(index);
    }
    return null;
  }

  public Temperature getLastTemperature(String id)
  {
    if (list.size() < 1)
    {
      return null;
    }
    if (id == null)
    {
      return list.get(list.size()-1);
    }
    for (int i=list.size()-1; i>=0; i--)
    {
      if (id.equals(list.get(i).getId()))
      {
        return list.get(i);
      }
    }
    return null;
  }

  public int getSize()
  {
    return list.size();
  }

  public String toString()
  {
    String s = "{";
    for (int i = 0; i < list.size(); i++)
    {
      s += list.get(i);
      if (i < list.size() - 1)
      {
        s += ", ";
      }
    }
    return s;
  }
}

