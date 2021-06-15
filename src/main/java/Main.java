import applicationLayer.ConfigurationService;
import java.io.IOException;
import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;
import presentationLayer.View;
import presentationLayer.Controller;

public class Main {

  public static void main(String[] args) throws IOException {
    new Controller(new View(), new AvailableConfiguration(), new SelectedConfiguration());
  }
}