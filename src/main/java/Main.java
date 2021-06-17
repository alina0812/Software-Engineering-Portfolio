import applicationLayer.ConfigurationService;
import dataAccessLayer.ReadJson;
import java.io.IOException;
import presentationLayer.Controller;
import presentationLayer.View;
import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;

/**
 * Main class, which contains the public static void main method.
 * Called one time to set up the application
 */
public class Main {

  /**
   * Starting point of the application.
   * Creates all necessary classes in this method to ensure that there are only one instance of each class (mixture of dependency injection and singleton)
   * @param args arguments to the public static void main method
   * @throws IOException if the JSON file is not found or in a wrong syntax
   */
  public static void main(String[] args) throws IOException {
    View view = new View();
    AvailableConfiguration availableConfiguration = new AvailableConfiguration();
    SelectedConfiguration selectedConfiguration = new SelectedConfiguration();
    ReadJson readJson = new ReadJson();
    ConfigurationService configurationService = new ConfigurationService(readJson);
    new Controller(view, availableConfiguration, selectedConfiguration, configurationService);
  }
}