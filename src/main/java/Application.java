import controller.BlackJackController;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BlackJackController blackJackController = new BlackJackController(new InputView(scanner), new OutputView());
        blackJackController.start();
    }
}
