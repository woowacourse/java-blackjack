package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController(
                new InputView(),
                new OutputView()
        );

        blackjackController.run();
    }
}
