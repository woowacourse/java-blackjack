import controller.BlackJackController;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final BlackJackController blackJackController = new BlackJackController(
                    new InputView(scanner),
                    new OutputView());

            blackJackController.process();
        }
    }
}
