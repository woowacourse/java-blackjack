package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            new BlackJackController().run();
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception);
        }
    }
}
