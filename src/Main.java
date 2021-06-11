import datalayer_test.ReadJson;
import presentationLayer.Controller;
import presentationLayer.Model;
import presentationLayer.View;

public class Main {

  public static void main(String[] args) {
    System.out.println(add(2, 5));

    View view = new View();
    Model model = new Model();
    Controller controller = new Controller(view, model);

    ReadJson.load_data(); // loads data from JSON File
    System.out.println(ReadJson.getModels());
    System.out.println(ReadJson.getEngines());
    System.out.println(ReadJson.getTransmissions());
    System.out.println(ReadJson.getSeats());

  }

  public static int add(int a, int b) {
    return a + b;
  }

}
