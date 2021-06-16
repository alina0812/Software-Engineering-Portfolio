import applicationLayer.ConfigurationService;
import dataAccessLayer.ReadJson;
import java.io.IOException;
import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;
import presentationLayer.View;
import presentationLayer.Controller;

public class Main {

  public static void main(String[] args) throws IOException {
    View view = new View();
    AvailableConfiguration availableConfiguration = new AvailableConfiguration();
    SelectedConfiguration selectedConfiguration = new SelectedConfiguration();
    ReadJson readJson = new ReadJson();
    ConfigurationService configurationService = new ConfigurationService(readJson);
    new Controller(view, availableConfiguration, selectedConfiguration, configurationService);
  }
}