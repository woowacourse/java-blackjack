import controller.BlackjackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController controller = new BlackjackController(new InputView(new Scanner(System.in)), new OutputView());
        controller.run();
    }
}
