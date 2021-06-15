import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;
import presentationLayer.View;
import presentationLayer.Controller;

public class Main {

  public static void main(String[] args) {
    new Controller(new View(), new AvailableConfiguration(), new SelectedConfiguration());
  }
}
