package factory;

import view.HeaterManager.HeaterViewModel;
import view.TemperatureOverview.TemperatureViewModel;
import view.chart.TempChartViewModel;

public class ViewModelFactory
{

  private ModelFactory modelFactory;
  public ViewModelFactory(ModelFactory mf)
  {
    this.modelFactory = mf;
  }

  private TemperatureViewModel temperatureViewModel;
  public TemperatureViewModel getTemperatureViewModel()
  {
    if (temperatureViewModel == null){
      temperatureViewModel = new TemperatureViewModel(modelFactory.getTemperatureModel(), modelFactory.getHeaterModel());
    }
    return temperatureViewModel;
  }

  private HeaterViewModel heaterViewModel;
  public HeaterViewModel getHeaterViewModel()
  {
    if (heaterViewModel == null){
      heaterViewModel = new HeaterViewModel(modelFactory.getHeaterModel());
    }
    return heaterViewModel;
  }

  private TempChartViewModel tempChartViewModel;
  public TempChartViewModel getTempChartViewModel()
  {
    if (tempChartViewModel == null){
      tempChartViewModel = new TempChartViewModel(modelFactory.getTemperatureModel());
    }
    return tempChartViewModel;
  }
}
