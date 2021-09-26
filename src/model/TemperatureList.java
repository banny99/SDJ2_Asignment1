package model;

import java.util.ArrayList;

public class TemperatureList
{

  private ArrayList<Temperature> list1;
  private ArrayList<Temperature> list2;
  private ArrayList<Temperature> list3;

  public TemperatureList()
  {
    this.list1 = new ArrayList<>();
    this.list2 = new ArrayList<>();
    this.list3 = new ArrayList<>();
  }

  public void addTemperature(Temperature temperature)
  {
    if (temperature.getId().equals("t1"))
      list1.add(temperature);
    else if (temperature.getId().equals("t2"))
      list2.add(temperature);
    else if (temperature.getId().equals("t3"))
      list3.add(temperature);
  }


  public Temperature getLastTemperature(String id)
  {
    if (id == null){
      return null;
    }

    if (id.equals("t1")){
      if (list1.size() < 1)
        return null;
      return list1.get(list1.size()-1);
    }

    else if (id.equals("t2")){
      if (list2.size() < 1)
        return null;
      return list2.get(list2.size()-1);
    }

    else if (id.equals("t3")){
      if (list3.size() < 1)
        return null;
      return list3.get(list3.size()-1);
    }


//    if (list.size() < 1)
//    {
//      return null;
//    }
//    if (id == null)
//    {
//      return list.get(list.size()-1);
//    }
//    for (int i=list.size()-1; i>=0; i--)
//    {
//      if (id.equals(list.get(i).getId()))
//      {
//        return list.get(i);
//      }
//    }

    return null;
  }

  public int getSize(String id)
  {
    if (id.equals("t1")){
      return list1.size();
    }

    else if (id.equals("t2")){
      return list2.size();
    }

    else if (id.equals("t3")){
      return list3.size();
    }

    return 0;
  }

  public String toString(String id)
  {
    ArrayList<Temperature> list = new ArrayList<>();

    if (id.equals("t1")){
      list = list1;
    }

    else if (id.equals("t2")){
      list = list2;
    }

    else if (id.equals("t3")){
      list = list3;
    }

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


  public ArrayList<Temperature> getList(String id)
  {
    if (id.equals("t1")){
      return list1;
    }

    else if (id.equals("t2")){
      return list2;
    }

    else if (id.equals("t3")){
      return list3;
    }

    return null;
  }

}

