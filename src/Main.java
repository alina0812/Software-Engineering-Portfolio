import presentationLayer.Controller;
import presentationLayer.Model;
import presentationLayer.View;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello World");
    System.out.println("Test");
    System.out.println("Hallo");
    System.out.println(add(2, 5));

    View view = new View();
    Model model = new Model();
    Controller controller = new Controller(view, model);
  }

  public static int add(int a, int b) {
    return a + b;
  }

}
