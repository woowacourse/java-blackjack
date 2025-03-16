package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.GameManagerFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();
        GameManagerFactory gameManagerFactory = new GameManagerFactory();

        BlackjackController controller = new BlackjackController(
                inputView,
                outputView,
                gameManagerFactory
        );

        try {
            controller.start();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
