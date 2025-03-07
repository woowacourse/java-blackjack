package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackJackController = new BlackjackController(
            new InputView(),
            new OutputView()
        );
        blackJackController.start();
    }
}
