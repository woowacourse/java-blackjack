package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(final String[] args) {
        final BlackjackController blackjackController = new BlackjackController(new InputView(), new OutputView());
        blackjackController.run();
    }
}
