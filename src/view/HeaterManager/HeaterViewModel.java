package view.HeaterManager;

import extern.Listener;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mediator.HeaterModel;

public class HeaterViewModel implements Listener
{

  private StringProperty powerStateLabel;

  private HeaterModel heaterModel;
  
  public HeaterViewModel(HeaterModel m){
    this.heaterModel = m;
    powerStateLabel = new SimpleStringProperty();
    heaterModel.addListener(this);
  }

  public void onButtonUpPressed()
  {
    heaterModel.turnUp();
  }

  public void onButtonDownPressed()
  {
    heaterModel.turnDown();
  }

  public StringProperty requestPowerStateProperty()
  {
    return powerStateLabel;
  }


  //listener (observing/subscribing HeaterModelManager) method:
  @Override public void update()
  {
    Platform.runLater(() -> powerStateLabel.setValue(""+heaterModel.getPower()));
  }
}
