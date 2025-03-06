import controller.BlackJackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackController blackJackController = new BlackJackController(new InputView(new Scanner(System.in)), new OutputView());
        blackJackController.start();
    }
}
