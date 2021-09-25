import factory.ModelFactory;
import factory.ViewHandler;
import factory.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import extern.Thermometer;

public class HeatingManager extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);

    //thermometers:
    int min = 15;
    int max = 25;

    Runnable r1 = new Thermometer("t1", 17, 1, (int)(Math.random()*(1000)+4000), min, max, modelFactory.getTemperatureModel(), modelFactory.getHeaterModel());
    Thread t1 = new Thread(r1);
    t1.setDaemon(true);

    Runnable r2 = new Thermometer("t2", 17, 7, (int)(Math.random()*(1000)+4000), min, max, modelFactory.getTemperatureModel(), modelFactory.getHeaterModel());
    Thread t2 = new Thread(r2);
    t2.setDaemon(true);

    Runnable r3 = new Thermometer("t3", 10, (int)(Math.random()*(1000)+4000), min, max, modelFactory.getTemperatureModel());
    Thread t3 = new Thread(r3);
    t3.setDaemon(true);

    t1.start();
    t2.start();
    t3.start();
  }
}
