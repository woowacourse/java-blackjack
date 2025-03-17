package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.GameManagerFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OutputView outputView = new OutputView();
        InputView inputView = new InputView(scanner);
        GameManagerFactory gameManagerFactory = new GameManagerFactory();

        BlackjackController controller = new BlackjackController(
                inputView,
                outputView,
                gameManagerFactory
        );

        try {
            controller.start();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
