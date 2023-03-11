package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.run();
    }
}
