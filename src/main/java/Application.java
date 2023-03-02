import controller.BlackJackController;
import view.InputValidator;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(new InputView(new Scanner(System.in), new InputValidator()), new OutputView());
        controller.play();
    }
}
