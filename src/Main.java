import applicationLayer.GetConfiguration;
import datalayer_test.ReadJson;
import presentationLayer.Controller;
import presentationLayer.Model.AvailableConfiguration;
import presentationLayer.Model.SelectedConfiguration;
import presentationLayer.View;

public class Main {

  public static void main(String[] args) {

    View view = new View();
    AvailableConfiguration availableConfiguration = new AvailableConfiguration();
    SelectedConfiguration selectedConfiguration = new SelectedConfiguration();
    Controller controller = new Controller(view, availableConfiguration, selectedConfiguration);

    ReadJson.load_data(); // loads data from JSON File
    System.out.println(ReadJson.getModels());
    System.out.println(ReadJson.getEngines());
    System.out.println(ReadJson.getTransmissions());
    System.out.println(ReadJson.getSeats());
    GetConfiguration.getConfiguration();
    GetConfiguration.getSubConfiguration("Golf");

  }
}
