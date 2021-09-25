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
  public void addTemperature2(String key, Temperature temperature)
  {
    if (!tempMap.containsKey(key)){
      tempMap.put(key, new ArrayList<>(25));
    }

    if (tempMap.get(key).size() >= 20){
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
    StringBuilder s = new StringBuilder("{");
    for (int i = 0; i < list.size(); i++)
    {
      s.append(list.get(i));
      if (i < list.size() - 1)
      {
        s.append(", ");
      }
    }
    return s.toString();
  }

  public Map<String, ArrayList<Temperature>> getTempMap()
  {
    return tempMap;
  }

  public ArrayList<Temperature> getList()
  {
    return list;
  }
}

