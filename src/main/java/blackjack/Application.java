package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.controller.GameManagerFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();

        BlackjackController controller = new BlackjackController(
                new InputView(),
                outputView,
                new GameManagerFactory()
        );

        try {
            controller.start();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }
}
